package it.unibo.view.impl;

import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.view.api.ShopView;

public class ShopViewImpl implements ShopView {

    private final JFrame frame;
    private final ShopController controller;
    private JLabel coinsLabel;
    private JPanel itemsPanel;
    private JPanel skinsPanel;
    private JPanel permPanel;
    private JPanel tempPanel;

    public ShopViewImpl(ShopController controller) {
        this.controller = controller;
        this.frame = new JFrame("Shop");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1000, 700);

        initialize();
    }

    private void initialize() {
        this.frame.setLayout(new BorderLayout());

        //parte superiore COINS+INVENTORY+EXIT
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.coinsLabel = new JLabel("Coins: 0");
        this.coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        final JButton inventoryButton = new JButton("INVENTORY");
        inventoryButton.addActionListener(e -> controller.openInventory());

        final JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> controller.exit());
        
        final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightHeader.add(coinsLabel);
        rightHeader.add(inventoryButton);
        rightHeader.add(exitButton);
        
        topPanel.add(rightHeader, BorderLayout.EAST);
        this.frame.add(topPanel, BorderLayout.NORTH);
        
        //parte centrale ITEMS
        this.itemsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        
        this.skinsPanel = createCategoryPanel("SKINS");
        this.permPanel = createCategoryPanel("PERMANENT POWER-UPS");
        this.tempPanel = createCategoryPanel("TEMPORARY POWER-UPS");
        
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.frame.add(scrollPane, BorderLayout.CENTER);
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

        JLabel imgLabel = new JLabel("IMG");
        imgLabel.setPreferredSize(new Dimension(100, 100));
        imgLabel.setMaximumSize(new Dimension(100, 100));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
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
            buyButton.addActionListener(e -> controller.buySkin(index));
        }
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(5));
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
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        return label;
    }

    private void addTempCategory(String title, String prefix, List<ShopItem> items) {
        tempPanel.add(createSubHeader(title));
        for (int i = 0; i < items.size(); i++) {
            ShopItem item = items.get(i);
            if (item.getId().startsWith(prefix)) {
                tempPanel.add(createTemporaryWidget(item, i));
                tempPanel.add(Box.createVerticalStrut(10));
            }
        }
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }

    @Override
    public void updateCoins(int coins) {
        this.coinsLabel.setText("Coins: " + coins);
    }

    @Override
    public void updateItems(List<ShopItem> skins, List<ShopItem> permUpgrades, List<ShopItem> tempUpgrades) {
        skinsPanel.removeAll();

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
        JOptionPane.showMessageDialog(frame, message);
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

    public static void main(String[] args) {
        ShopItemFactory factory = new ShopItemFactoryImpl();

        //Mock del controller
        ShopController mockController = new ShopController() {
            @Override public void upgradeJump() {}
            @Override public void upgradeSpeed() {}
            @Override public void buyTemporaryItem(int i) {}
            @Override public void buySkin(int i) {}
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
        
        };

        ShopViewImpl view = new ShopViewImpl(mockController);
        view.display();
        view.updateCoins(mockController.getCoins());
        view.updateItems(mockController.getSkins(), mockController.getPermanetUpgrades(), mockController.getTemporaryUpgrades());
    }

}
