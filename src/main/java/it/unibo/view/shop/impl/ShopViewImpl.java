package it.unibo.view.shop.impl;

import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinRegistry;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinSet;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.impl.SkinRegistryImpl;
import it.unibo.view.shop.api.ShopView;

public class ShopViewImpl extends JPanel implements ShopView {

    private final ShopController controller;
    private final SpriteManager spriteManager;
    private final SkinRegistry skinRegistry;
    private JLabel coinsLabel;
    private JPanel itemsPanel;
    private JPanel skinsPanel;
    private JPanel permPanel;
    private JPanel tempPanel;

    public ShopViewImpl(ShopController controller) {
        super(new BorderLayout());
        this.controller = controller;
        this.spriteManager = new SpriteManager();
        this.skinRegistry = new SkinRegistryImpl();
        initialize();
    }

    private void initialize() {
        //parte superiore COINS+INVENTORY+EXIT
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("SHOP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        topPanel.add(titleLabel, BorderLayout.WEST);

        final JPanel coinsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        this.coinsLabel = new JLabel("Coins: 0");
        this.coinsLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel coinIconLabel = new JLabel();
        BufferedImage coinImg = spriteManager.get(SpriteEnum.COIN);
        if (coinImg != null) {
            Image scaledCoin = coinImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            coinIconLabel.setIcon(new ImageIcon(scaledCoin));
        }
        coinsPanel.add(coinsLabel);
        coinsPanel.add(coinIconLabel);
        
        final JButton inventoryButton = new JButton("INVENTORY");
        inventoryButton.addActionListener(e -> controller.openInventory());

        final JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> controller.exit());
        
        final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightHeader.add(coinsPanel);
        rightHeader.add(inventoryButton);
        rightHeader.add(exitButton);
        
        topPanel.add(rightHeader, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);
        
        //parte centrale ITEMS
        this.itemsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        
        this.skinsPanel = createCategoryPanel("SKINS");
        this.permPanel = createCategoryPanel("PERMANENT POWER-UPS");
        this.tempPanel = createCategoryPanel("TEMPORARY POWER-UPS");
        
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createCategoryPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        panel.add(content, BorderLayout.CENTER);
        this.itemsPanel.add(panel);
        return content;
    }

    private JPanel createSkinWidget(ShopItem item, int index) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(280, 250));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imgLabel;
        SkinSet skinSet = skinRegistry.getSkinSet(item.getId());
        BufferedImage img = spriteManager.get(skinSet.right());
        if (img != null) {
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(scaledImg));
        } else {
            imgLabel = new JLabel("Image not found");
            imgLabel.setPreferredSize(new Dimension(100, 100));
        }
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel descLabel = new JLabel("<html><div style='text-align: center; width: 180px;'>" + item.getDescription() + "</div></html>");
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel priceLabel = new JLabel(item.getPrice() + "$");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buyButton = new JButton();
        if (controller.isOwned(item)) {
            buyButton.setText("OWNED");
            buyButton.setEnabled(false);
        } else {
            buyButton.setText("BUY");
            buyButton.addActionListener(e -> controller.buySkin(index));
        }
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(nameLabel);
        card.add(descLabel);
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(buyButton);

        return card;  
    }

    private JPanel createPermanentWidget(String title, String prefix, List<ShopItem> items) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    
        container.setMaximumSize(new Dimension(300, 250));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        int currentLevel = controller.getCurrentLevel(prefix);

        List<ShopItem> categoryItems = items.stream()
                .filter(i -> i.getId().startsWith(prefix))
                .sorted(Comparator.comparing(ShopItem::getId))
                .toList();
        int maxLevel = categoryItems.size();

        JProgressBar progressBar = new JProgressBar(0, maxLevel);
        progressBar.setValue(currentLevel);
        progressBar.setStringPainted(true);
        progressBar.setString("Level " + currentLevel + "/" + maxLevel);
        progressBar.setMaximumSize(new Dimension(250, 25));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        container.add(titleLabel);
        container.add(Box.createVerticalStrut(20));
        container.add(progressBar);
        container.add(Box.createVerticalStrut(20));

        ShopItem nextItem = categoryItems.stream()
                .filter(i -> !controller.isOwned(i))
                .findFirst()
                .orElse(null);

        if (nextItem != null) {
            JLabel nextname = new JLabel("Next: " + nextItem.getName());
            nextname.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel nextDesc = new JLabel(nextItem.getDescription());
            nextDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel price = new JLabel("Cost: " + nextItem.getPrice() + "$");
            price.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton upgradeButton = new JButton("UPGRADE");
            upgradeButton.addActionListener(e -> {
                if (prefix.contains("jump")) {
                    controller.upgradeJump();
                } else {
                    controller.upgradeSpeed();
                }
            });
            upgradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            container.add(nextname);
            container.add(Box.createVerticalStrut(10));
            container.add(nextDesc);
            container.add(Box.createVerticalStrut(10));
            container.add(price);
            container.add(Box.createVerticalGlue());
            container.add(upgradeButton);
        } else {
            JLabel maxReachedLabel = new JLabel("MAX LEVEL REACHED");
            maxReachedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(maxReachedLabel);
        }
        return container;
    }

    private JPanel createTemporaryWidget(ShopItem item, int index) {
        final JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        card.setMaximumSize(new Dimension(280, 150));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel(item.getDescription());
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel(item.getPrice() + "$");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buyButton = new JButton();
        if (controller.isOwned(item)) {
            buyButton.setText("OWNED");
            buyButton.setEnabled(false);
        } else {
            buyButton.setText("BUY");
            buyButton.addActionListener(e -> controller.buyTemporaryItem(index));
        }
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(buyButton);

        return card;
    }

    private JLabel createSubHeader(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        return label;
    }

    private void addTempCategory(String title, String prefix, List<ShopItem> items) {
        JPanel categoryContainer = new JPanel();
        categoryContainer.setLayout(new BoxLayout(categoryContainer, BoxLayout.Y_AXIS));
        categoryContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1), 
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        categoryContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryContainer.add(createSubHeader(title));
        categoryContainer.add(Box.createVerticalStrut(10));
        
        for (int i = 0; i < items.size(); i++) {
            ShopItem item = items.get(i);
            if (item.getId().startsWith(prefix)) {
                categoryContainer.add(createTemporaryWidget(item, i));
                categoryContainer.add(Box.createVerticalStrut(10));
            }
        }

        Dimension maxSize = categoryContainer.getPreferredSize();
        categoryContainer.setMaximumSize(new Dimension(340, maxSize.height));
        tempPanel.add(categoryContainer);
        tempPanel.add(Box.createVerticalStrut(20));
    }

    @Override
    public void display() {
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void updateCoins(int coins) {
        this.coinsLabel.setText("Coins: " + coins);
    }

    @Override
    public void updateItems(List<ShopItem> skins, List<ShopItem> permUpgrades, List<ShopItem> tempUpgrades) {
        skinsPanel.removeAll();
        permPanel.removeAll();
        tempPanel.removeAll();

        /* skins */
        for (int i = 0; i < skins.size(); i++) {
            skinsPanel.add(Box.createVerticalStrut(10));
            skinsPanel.add(createSkinWidget(skins.get(i), i));
        }

        /* perm power ups */
        permPanel.add(Box.createVerticalStrut(10));
        permPanel.add(createPermanentWidget("JUMP HEIGHT", "pp_jump", permUpgrades));
        permPanel.add(Box.createVerticalStrut(30));
        permPanel.add(createPermanentWidget("SPEED BOOST", "pp_speed", permUpgrades));

        /* temp power ups */
        addTempCategory("JUMP HEIGHT", "pt_jump", tempUpgrades);
        addTempCategory("SPEED BOOST", "pt_speed", tempUpgrades);
        addTempCategory("COIN MULTIPLIER", "pt_coin", tempUpgrades);

        skinsPanel.revalidate();
        skinsPanel.repaint();
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void close() {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        ShopItemFactory factory = new ShopItemFactoryImpl();

        //Mock del controller
        ShopController mockController = new ShopController() {
            @Override public void upgradeJump() {}
            @Override public void upgradeSpeed() {}
            @Override public void buyTemporaryItem(int i) {}
            @Override public void buySkin(int i) {}
            @Override public void openShop() { System.out.println("Opening shop view");}
            @Override public void openInventory() { System.out.println("Opening inventory view");}
            @Override public void exit() { System.exit(0); }
            @Override public int getCoins() { return 150; }
            @Override public List<ShopItem> getPermanetUpgrades( ) { return factory.getPowerUpsPermanent(); }
            @Override public List<ShopItem> getSkins() { return factory.getSkins(); }
            @Override public List<ShopItem> getTemporaryUpgrades() { return factory.getPowerUpsTemporary(); }

            @Override
            public boolean isOwned(ShopItem item) {
                String itemId = item.getId();
                //TEST: per jump simuliamo di averli tutti
                if (itemId.startsWith("pp_jump")) {
                    return true; 
                }

                //per la velocità posseduti solo livello 1 e 2
                if (itemId.startsWith("pp_speed")) {
                    return itemId.endsWith("_1") || itemId.endsWith("_2");
                }

                //per temp jump 1 posseduto
                if (itemId.equals("pt_jump1")) return true;
                
                //Per le skin contenuta solo quella sportive
                return item.getName().contains("Sportive");
            }

            @Override
            public int getCurrentLevel(String prefix) {
            // TEST: Se è salto, restituiamo 5(massimo); se è speed restituiamo 2 altrimenti 0
                if (prefix.equals("pp_jump")) {
                    return 5; 
                }
                if (prefix.equals("pp_speed")) {
                    return 2; 
                }
                return 0;
            }
            @Override
            public void setView(ShopView view) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setView'");
            }
        
        };

       SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Shop Panel");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(1000, 700);
            
            ShopViewImpl shopPanel = new ShopViewImpl(mockController); 
            shopPanel.updateCoins(mockController.getCoins());
            shopPanel.updateItems(mockController.getSkins(), mockController.getPermanetUpgrades(), mockController.getTemporaryUpgrades());

            testFrame.add(shopPanel);
            testFrame.setLocationRelativeTo(null);
            testFrame.setVisible(true);
        });
    }

}
