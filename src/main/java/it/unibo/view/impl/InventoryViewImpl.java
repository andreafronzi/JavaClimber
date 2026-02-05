package it.unibo.view.impl;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.api.InventoryController;
import it.unibo.model.shop.api.ShopItem;
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
        this.frame = new JFrame("Shop");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1000, 700);

        initialize();
    }

    private void initialize() {

    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }

    @Override
    public void updateInventory(List<ShopItem> ownedSkins, String equippedSkinId, int selectedJump, int maxJump,
            int selectedSpeed, int maxSpeed, List<ShopItem> ownedTemp, List<Boolean> tempStatus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInventory'");
    }

    @Override
    public void updateCoins(int coins) {
        this.coinsLabel.setText("Coins: " + coins);
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

}
