package it.unibo.view.menu.impl;

import javax.swing.*;
import java.awt.*;

import it.unibo.controller.api.MenuController;
import it.unibo.view.menu.api.MenuView;

public class MenuViewImpl extends JPanel implements MenuView{
    
    private final MenuController controller;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private static final int SPACING = 20;
    private JButton highScoreLabel;

    public MenuViewImpl(MenuController controller) {
        super(new BorderLayout());
        this.controller = controller;

        initialize();
    }

    private void initialize() {        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("JAVA CLIMBER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnStart = createMenuButton("START GAME");
        JButton btnShop = createMenuButton("SHOP");
        JButton btnInventory = createMenuButton("INVENTORY");
        JButton btnExit = createMenuButton("EXIT");

        this.highScoreLabel = new JButton("HIGH SCORE: " + controller.getHighScore());
        this.highScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.highScoreLabel.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.highScoreLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        this.highScoreLabel.setFocusable(false);
        this.highScoreLabel.setRolloverEnabled(false);
        this.highScoreLabel.setRequestFocusEnabled(false);
        this.highScoreLabel.setFocusPainted(false);

        btnStart.addActionListener(e -> start());
        btnShop.addActionListener(e -> shop());
        btnInventory.addActionListener(e -> inventory());
        btnExit.addActionListener(e -> exit());

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(titleLabel);
        buttonPanel.add(Box.createVerticalStrut(60));
        
        buttonPanel.add(btnStart);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(this.highScoreLabel);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(btnShop);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(btnInventory);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(btnExit);
        buttonPanel.add(Box.createVerticalGlue());

        this.add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setFocusPainted(false);
        return button;
    }

    public void display() {
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void updateHighScore(int score) {
        if (this.highScoreLabel != null) {
            this.highScoreLabel.setText("HIGH SCORE: " + score);
        }
    }

    @Override
    public void start() {
        this.controller.start();
    }

    @Override
    public void shop() {
        this.controller.shop();
    }

    @Override
    public void inventory() {
        this.controller.inventory();
    }

    @Override
    public void exit() {
        this.controller.exit();
    }

    public static void main(String[] args) {
        // Mock del controller
        MenuController mockController = new MenuController() {
            @Override public void openViewMenu() {}
            @Override public void start() { System.out.println("Controller: Start Game triggered"); }
            @Override public void shop() { System.out.println("Controller: Shop triggered"); }
            @Override public void inventory() { System.out.println("Controller: Inventory triggered"); }
            @Override public void exit() { System.out.println("Controller: Exit triggered"); System.exit(0); }
            @Override public int getHighScore() { return 12500; }
            @Override
            public void setView(MenuView view) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setView'");
            };
        };

        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Test Menu Panel");
            MenuViewImpl menuPanel = new MenuViewImpl(mockController);
            
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(1000, 700);
            testFrame.add(menuPanel);
            testFrame.setLocationRelativeTo(null);
            testFrame.setVisible(true);
        });
    }
}
