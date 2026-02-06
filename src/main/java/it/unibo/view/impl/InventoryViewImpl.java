package it.unibo.view.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import it.unibo.controller.api.InventoryController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.view.api.InventoryView;

public class InventoryViewImpl implements InventoryView {

    private final JFrame frame;
    private final InventoryController controller;
    private JLabel coinsLabel;
    private JPanel itemsPanel;
    private JPanel skinsPanel;
    private JPanel permPanel;
    private JPanel tempPanel;

    public InventoryViewImpl(InventoryController controller) {
        this.controller = controller;
        this.frame = new JFrame("Inventory");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1000, 700);

        initialize();
    }

    private void initialize() {
        this.frame.setLayout(new BorderLayout());

        // parte superiore COINS+SHOP+EXIT
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.coinsLabel = new JLabel("Coins: 0");
        this.coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        final JButton inventoryButton = new JButton("SHOP");
        inventoryButton.addActionListener(e -> controller.openShop());

        final JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> controller.exit());

        final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightHeader.add(coinsLabel);
        rightHeader.add(inventoryButton);
        rightHeader.add(exitButton);

        topPanel.add(rightHeader, BorderLayout.EAST);
        this.frame.add(topPanel, BorderLayout.NORTH);

        // parte centrale ITEMS
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

    private JPanel createSkinWidget(ShopItem item, int index, boolean isEquipped) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
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

        JButton selectBtn = new JButton(isEquipped ? "EQUIPPED" : "SELECT");
        selectBtn.setEnabled(!isEquipped);
        selectBtn.addActionListener(e -> controller.selectSkin(index));
        selectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(nameLabel);
        card.add(descLabel);
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(selectBtn);

        return card;
    }

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

        ShopItem actualItem = items.get(selectedLevel - 1);
        JLabel name = new JLabel("Actual: " + actualItem.getName());
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel desc = new JLabel(actualItem.getDescription());
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
                    controller.plusJump();
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

    private JPanel createTemporaryWidget(ShopItem item, int index, boolean isActive) {
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

        JButton selectButton = new JButton(isActive ? "DESELECT" : "SELECT");
        selectButton.addActionListener(e -> controller.toggleTemporaryItem(index));
        selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(selectButton);

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

    private void addTempCategory(String title, String prefix, List<ShopItem> items, List<Boolean> tempStatus) {
        tempPanel.add(createSubHeader(title));
        for (int i = 0; i < items.size(); i++) {
            ShopItem item = items.get(i);
            if (item.getId().startsWith(prefix)) {
                tempPanel.add(createTemporaryWidget(item, i, tempStatus.get(i)));
                tempPanel.add(Box.createVerticalStrut(10));
            }
        }
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }

    @Override
    public void updateInventory(List<ShopItem> ownedSkins, String equippedSkinId, List<ShopItem> allPermItems, int selectedJump, int maxJump,
            int selectedSpeed, int maxSpeed, List<ShopItem> ownedTemp, List<Boolean> tempStatus) {
        skinsPanel.removeAll();
        permPanel.removeAll();
        tempPanel.removeAll();

        //Skins
        for(int i = 0; i < ownedSkins.size(); i++) {
            skinsPanel.add(Box.createVerticalStrut(10));
            skinsPanel.add(createSkinWidget(ownedSkins.get(i), i, ownedSkins.get(i).getId().equals(equippedSkinId)));
        }

        //permanent upgrades
        permPanel.add(Box.createVerticalStrut(10));
        permPanel.add(createPermanentWidget("JUMP HEIGHT", "pp_jump", allPermItems, selectedJump, maxJump));
        permPanel.add(Box.createVerticalStrut(30));
        permPanel.add(createPermanentWidget("SPEED BOOST", "pp_speed", allPermItems, selectedSpeed, maxSpeed));

        //temporary upgrades
        if (ownedTemp != null && tempStatus != null) {
            addTempCategory("JUMP HEIGHT", "pt_jump", ownedTemp, tempStatus);
            addTempCategory("SPEED BOOST", "pt_speed", ownedTemp, tempStatus);
            addTempCategory("COIN MULTIPLIER", "pt_coin", ownedTemp, tempStatus);
        }

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void updateCoins(int coins) {
        this.coinsLabel.setText("Coins: " + coins);
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

    public static void main(String[] args) {

        //Mock del Controller
        InventoryController mockController = new InventoryController() {
            @Override public void selectSkin(int index) { System.out.println("Skin selezionata: " + index); }
            @Override public void plusJump() { System.out.println("Aumentato livello Salto"); }
            @Override public void minusJump() { System.out.println("Diminuito livello Salto"); }
            @Override public void plusVelocity() { System.out.println("Aumentato livello Velocità"); }
            @Override public void minusVelocity() { System.out.println("Diminuito livello Velocità"); }
            @Override public void toggleTemporaryItem(int index) { System.out.println("Toggle oggetto temporaneo: " + index); }
            @Override public void openShop() { System.out.println("Opening shop view");; }
            @Override public void exit() { System.exit(0); }
        };

        InventoryViewImpl view = new InventoryViewImpl(mockController);
        
        final ShopItemFactory factory = new ShopItemFactoryImpl();

        //simuliamo che abbiamo le prime 3 skin della lista con la seconda che è selezionata
        List<ShopItem> ownedSkins = factory.getSkins().subList(0, 3);
        String equippedSkinId = ownedSkins.get(1).getId();

        //simulazione per permanent power up
        int selectedJump = 2;
        int maxJumpOwned = 4;
        int selectedSpeed = 1;
        int maxSpeedOwned = 3;

        //simulazione per temporary power ups --> presenti due jump(1,2); due speed(2,3); due coins(1,2)
        List<ShopItem> ownedTemp = new ArrayList<>();
        ownedTemp.addAll(factory.getPowerUpsTemporary().subList(0, 2));
        ownedTemp.addAll(factory.getPowerUpsTemporary().subList(4, 8));
        List<Boolean> tempStatus = List.of(true, false, false, true, false, true);

        view.display();
        view.updateCoins(150);

        view.updateInventory(ownedSkins, equippedSkinId, factory.getPowerUpsPermanent(), selectedJump, maxJumpOwned, selectedSpeed, maxSpeedOwned, ownedTemp, tempStatus);
    }

}
