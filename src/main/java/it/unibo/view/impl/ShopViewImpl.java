package it.unibo.view.impl;

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

        //parte superiore COINS+EXIT
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.coinsLabel = new JLabel("Coins: 0");
        this.coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        final JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> controller.exit());
        
        final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightHeader.add(coinsLabel);
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
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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

        for (int i = 0; i < skins.size(); i++) {
            skinsPanel.add(Box.createVerticalStrut(10));
            skinsPanel.add(createSkinWidget(skins.get(i), i));
        }

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
        //Mock del controller
        ShopController mockController = new ShopController() {
            public void upgradeJump() {}
            public void upgradeSpeed() {}
            public void buyTemporaryItem(int i) {}
            public void buySkin(int i) {}
            public boolean isOwned(ShopItem item) { return item.getName().contains("Sportive"); }
            public void exit() { System.exit(0); }
        };

        ShopViewImpl view = new ShopViewImpl(mockController);

        ShopItemFactory factory = new ShopItemFactoryImpl();
        view.display();
        view.updateCoins(150);
        view.updateItems(factory.getSkins(), factory.getPowerUpsPermanent(), factory.getPowerUpsTemporary());
    }

}
