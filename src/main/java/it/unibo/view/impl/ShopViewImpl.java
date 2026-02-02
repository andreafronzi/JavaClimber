package it.unibo.view.impl;

import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.view.api.ShopView;

public class ShopViewImpl implements ShopView {

    private final JFrame frame;
    private final ShopController controller;
    private JLabel coinsLabel;
    private JPanel skinsPanel;
    private JPanel permPanel;
    private JPanel tempPanel;

    public ShopViewImpl(ShopController controller) {
        this.controller = controller;
        this.frame = new JFrame("Shop");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);

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
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        
        this.skinsPanel = createCategoryPanel("SKINS");
        this.permPanel = createCategoryPanel("PERMANENT POWER-UPS");
        this.tempPanel = createCategoryPanel("TEMPORARY POWER-UPS");
        itemsPanel.add(skinsPanel);
        itemsPanel.add(permPanel);
        itemsPanel.add(tempPanel);

        this.frame.add(itemsPanel, BorderLayout.CENTER);
    }

    private JPanel createCategoryPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(content, BorderLayout.CENTER);
        return panel;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItems'");
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
            public void exit() { System.exit(0); }
        };

        ShopViewImpl view = new ShopViewImpl(mockController);
        view.display();
        view.updateCoins(150);
    }

}
