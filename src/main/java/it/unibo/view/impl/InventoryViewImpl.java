package it.unibo.view.impl;

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

        //parte superiore COINS+SHOP+EXIT
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

        view.display();
        view.updateCoins(150);
    }

}
