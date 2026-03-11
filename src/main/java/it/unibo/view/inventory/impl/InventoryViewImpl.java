package it.unibo.view.inventory.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.controller.api.InventoryController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinRegistry;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinSet;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.impl.SkinRegistryImpl;
import it.unibo.view.inventory.api.InventoryView;

/**
 * Implementation of {@link InventoryView} interface.
 */
public class InventoryViewImpl extends JPanel implements InventoryView {

    private final InventoryController controller;
    private final SpriteManager spriteManager;
    private final SkinRegistry skinRegistry;
    private JLabel coinsLabel;
    private JPanel itemsPanel;
    private JPanel skinsPanel;
    private JPanel permPanel;
    private JPanel tempPanel;

    /**
     * Construct a InventoryViewImpl with specified controller.
     * @param controller the controller for managing user interactions and updating the view
     */
    public InventoryViewImpl(InventoryController controller) {
        super(new BorderLayout());
        this.controller = controller;
        this.spriteManager = new SpriteManager();
        this.skinRegistry = new SkinRegistryImpl();
        initialize();
    }

    /**
     * Initialize the view components.
     */
    private void initialize() {
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("INVENTORY");
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
        
        final JButton inventoryButton = new JButton("SHOP");
        inventoryButton.addActionListener(e -> controller.openShop());

        final JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> controller.exit());

        final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightHeader.add(coinsPanel);
        rightHeader.add(inventoryButton);
        rightHeader.add(exitButton);

        topPanel.add(rightHeader, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        this.itemsPanel = new JPanel(new GridLayout(1, 3, 10, 0));

        this.skinsPanel = createCategoryPanel("SKINS");
        this.permPanel = createCategoryPanel("PERMANENT POWER-UPS");
        this.tempPanel = createCategoryPanel("TEMPORARY POWER-UPS");

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Helper method to create a category panel with a title and add it to the items panel.
     * @param title the title of the category panel
     * @return the created category panel
     */
    private JPanel createCategoryPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        panel.add(content, BorderLayout.CENTER);
        this.itemsPanel.add(panel);
        return content;
    }

    /**
     * Helper method to create a widget for a skin item.
     * @param item the skin item to create the widget for
     * @param index the index of the item in the skins list
     * @param isEquipped whether the skin is currently equipped
     * @return a JPanel representing the skin widget
     */
    private JPanel createSkinWidget(ShopItem item, int index, boolean isEquipped) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
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

        JButton selectBtn = new JButton(isEquipped ? "EQUIPPED" : "SELECT");
        selectBtn.setEnabled(!isEquipped);
        selectBtn.addActionListener(e -> controller.selectSkin(index));
        selectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(30));
        card.add(nameLabel);
        card.add(descLabel);
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(selectBtn);

        return card;
    }

    /**
     * Helper method to create a widget for a permanent power up category.
     * @param title the title of the widget
     * @param prefix the prefix for filtering items
     * @param items the list of shop items
     * @param selectedLevel the currently selected level
     * @param maxOwnedLevel the maximum owned level
     * @return the created permanent power-up widget
     */
    private JPanel createPermanentWidget(String title, String prefix, List<ShopItem> items, int selectedLevel, int maxOwnedLevel) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        container.setMaximumSize(new Dimension(300, 250));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<ShopItem> categoryItems = items.stream()
                .filter(i -> i.getId().startsWith(prefix))
                .sorted(Comparator.comparing(ShopItem::getId))
                .toList();
        int maxLevel = categoryItems.size();

        JProgressBar progressBar = new JProgressBar(0, maxLevel);
        progressBar.setValue(selectedLevel);
        progressBar.setStringPainted(true);
        progressBar.setString("Active/Bought: " + selectedLevel + "/" + maxOwnedLevel + "  TOTAL: " + maxLevel);
        progressBar.setMaximumSize(new Dimension(250, 25));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(titleLabel);
        container.add(Box.createVerticalStrut(20));
        container.add(progressBar);
        container.add(Box.createVerticalStrut(20));

        JLabel name;
        JLabel desc;
        if (selectedLevel > 0) {
            ShopItem actualItem = items.get(selectedLevel - 1);
            name = new JLabel("Actual: " + actualItem.getName());
            desc = new JLabel(actualItem.getDescription());
        } else {
            name = new JLabel("Actual: NESSUNO");
            desc = new JLabel("Nessun potenziamento attivo");
        }
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton minusButton = new JButton("-");
        JButton plusButton = new JButton("+");
        
        if (selectedLevel == 0) {
            minusButton.setEnabled(false);
        } else {
            minusButton.addActionListener(e -> {
                if (prefix.contains("jump")) {
                    controller.minusJump();
                }
                if (prefix.contains("speed")) {
                    controller.minusVelocity();
                }
            });
        }

        if (selectedLevel == maxOwnedLevel) {
            plusButton.setEnabled(false);
        } else {
            plusButton.addActionListener(e -> {
                if (prefix.contains("jump")) {
                    controller.plusJump();
                }
                if (prefix.contains("speed")) {
                    controller.plusVelocity();
                }
            });
        }

        buttonsPanel.add(minusButton);
        buttonsPanel.add(plusButton);
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(name);
        container.add(Box.createVerticalStrut(10));
        container.add(desc);
        container.add(Box.createVerticalGlue());
        container.add(buttonsPanel);
    
        return container;
    }

    /**
     * Helper method to create a widget for a temporary power up item.
     * @param item the shop item to create the widget for
     * @param index the index of the item in the temporary items list
     * @param isActive whether the item is currently active
     * @param duration the remaining duration of the item in matches
     * @return the created temporary power-up widget
     */
    private JPanel createTemporaryWidget(ShopItem item, int index, boolean isActive, int duration) {
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

        JLabel durationLabel = new JLabel("Remaining: " + duration + " matches");
        durationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton selectButton = new JButton(isActive ? "DESELECT" : "SELECT");
        selectButton.addActionListener(e -> controller.toggleTemporaryItem(index));
        selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(durationLabel);
        card.add(Box.createVerticalGlue());
        card.add(selectButton);

        return card;
    }

    /**
     * Helper method to create a subheader label for category sections.
     * @param text the text for the subheader
     * @return the created subheader label
     */
    private JLabel createSubHeader(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        return label;
    }

    /**
     * Helper method to create a temporary power-up category section.
     * @param title the title of the category
     * @param prefix the prefix for filtering items
     * @param items the list of items in the category
     * @param tempStatus the list of active statuses for the temporary items
     * @param tempDuration the list of remaining durations for the temporary items
     */
    private void addTempCategory(String title, String prefix, List<ShopItem> items, List<Boolean> tempStatus, List<Integer> tempDuration) {
        JPanel categoryContainer = new JPanel();
        categoryContainer.setLayout(new BoxLayout(categoryContainer, BoxLayout.Y_AXIS));
        categoryContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1), 
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        categoryContainer.setMaximumSize(new Dimension(340, 1000));
        categoryContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryContainer.add(createSubHeader(title));
        categoryContainer.add(Box.createVerticalStrut(10));
        
        boolean hasCategoryItems = false;
        for (int i = 0; i < items.size(); i++) {
            ShopItem item = items.get(i);
            if (item.getId().startsWith(prefix)) {
                categoryContainer.add(createTemporaryWidget(item, i, tempStatus.get(i), tempDuration.get(i)));
                categoryContainer.add(Box.createVerticalStrut(10));
                hasCategoryItems = true;
            }
        }

        if (!hasCategoryItems) {
            JLabel emtyLabel = new JLabel("Nessun potenziamento temporaneo acquistato");
            emtyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoryContainer.add(emtyLabel);
            categoryContainer.add(Box.createVerticalStrut(50));
        }
        tempPanel.add(categoryContainer);
        tempPanel.add(Box.createVerticalStrut(20));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInventory(List<ShopItem> ownedSkins, String equippedSkinId, List<ShopItem> allPermItems, int selectedJump, int maxJump,
            int selectedSpeed, int maxSpeed, List<ShopItem> ownedTemp, List<Boolean> tempStatus, List<Integer> tempDuration) {
        skinsPanel.removeAll();
        permPanel.removeAll();
        tempPanel.removeAll();

        for(int i = 0; i < ownedSkins.size(); i++) {
            skinsPanel.add(Box.createVerticalStrut(10));
            skinsPanel.add(createSkinWidget(ownedSkins.get(i), i, ownedSkins.get(i).getId().equals(equippedSkinId)));
        }

        permPanel.add(Box.createVerticalStrut(10));
        permPanel.add(createPermanentWidget("JUMP HEIGHT", "pp_jump", allPermItems, selectedJump, maxJump));
        permPanel.add(Box.createVerticalStrut(30));
        permPanel.add(createPermanentWidget("SPEED BOOST", "pp_speed", allPermItems, selectedSpeed, maxSpeed));

        if (ownedTemp != null && tempStatus != null) {
            addTempCategory("JUMP HEIGHT", "pt_jump", ownedTemp, tempStatus, tempDuration);
            addTempCategory("SPEED BOOST", "pt_speed", ownedTemp, tempStatus, tempDuration);
            addTempCategory("COIN MULTIPLIER", "pt_coin", ownedTemp, tempStatus, tempDuration);
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCoins(int coins) {
        this.coinsLabel.setText("Coins: " + coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.setVisible(false);
    }

    public static void main(String[] args) {

        final ShopItemFactory factory = new ShopItemFactoryImpl();
        List<ShopItem> ownedSkins = factory.getSkins().subList(0, 3);
        List<ShopItem> ownedTemp = new ArrayList<>();
        ownedTemp.addAll(factory.getPowerUpsTemporary().subList(0, 2));
        ownedTemp.addAll(factory.getPowerUpsTemporary().subList(4, 8));
        List<Boolean> tempStatus = List.of(true, false, false, true, false, true);
        
        //Mock del Controller
        InventoryController mockController = new InventoryController() {
            @Override public void selectSkin(int index) { System.out.println("Skin selezionata: " + index); }
            @Override public void plusJump() { System.out.println("Aumentato livello Salto"); }
            @Override public void minusJump() { System.out.println("Diminuito livello Salto"); }
            @Override public void plusVelocity() { System.out.println("Aumentato livello Velocità"); }
            @Override public void minusVelocity() { System.out.println("Diminuito livello Velocità"); }
            @Override public void toggleTemporaryItem(int index) { System.out.println("Toggle oggetto temporaneo: " + index); }
            @Override public void openShop() { System.out.println("Opening shop view"); }
            @Override public void openInventory() { System.out.println("Opening inventory view"); }
            @Override public void exit() { System.exit(0); }
            @Override public int getSelectedJumpLevel() { return 2; }
            @Override public int getSelectedSpeedLevel() { return 1; }
            @Override public int getMaxJumpLevelOwned() { return 4; }
            @Override public int getMaxSpeedLevelOwned() { return 3; }
            @Override public String getEquippedSkin() { return ownedSkins.get(1).getId(); }
            @Override public List<ShopItem> getOwnedSkins() { return ownedSkins; }
            @Override public List<ShopItem> getOwnedTempItems() { return ownedTemp; }
            @Override public List<Boolean> getTempItemsStatus() { return tempStatus; }
            @Override public List<Integer> getTempItemsDuration() { return List.of(10, 20, 30, 40, 50, 60); }
            @Override public void setView(InventoryView view) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setView'");
            }
        };

        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Test Inventory Panel");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(1000, 700);

            InventoryViewImpl inventoryPanel = new InventoryViewImpl(mockController);
            inventoryPanel.updateCoins(150);
            inventoryPanel.updateInventory(mockController.getOwnedSkins(), mockController.getEquippedSkin(), factory.getPowerUpsPermanent(), mockController.getSelectedJumpLevel(), mockController.getMaxJumpLevelOwned(), mockController.getSelectedSpeedLevel(), mockController.getMaxSpeedLevelOwned(), mockController.getOwnedTempItems(), mockController.getTempItemsStatus(), mockController.getTempItemsDuration());
            
            testFrame.add(inventoryPanel);
            testFrame.setLocationRelativeTo(null);
            testFrame.setVisible(true);
        });
    }

}
