package it.unibo.view.impl;

import javax.swing.*;
import java.awt.*;

import it.unibo.controller.api.MenuController;
import it.unibo.controller.impl.MenuControllerImpl;
import it.unibo.view.api.MenuView;

public class MenuViewImpl implements MenuView{
    
    private final JFrame frame;
    private final MenuController controller;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private static final int SPACING = 20;

    public MenuViewImpl(MenuController controller) {
        this.controller = controller;
        this.frame = new JFrame("Java Climber - Main Menu");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1000, 700);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null); 

        initialize();
    }

    private void initialize() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("JAVA CLIMBER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnStart = createMenuButton("START GAME");
        JButton btnShop = createMenuButton("SHOP");
        JButton btnInventory = createMenuButton("INVENTORY");
        JButton btnExit = createMenuButton("EXIT");

        JButton highHighScoreLabel = new JButton("HIGH SCORE: " + controller.getHighScore());
        highHighScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        highHighScoreLabel.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        highHighScoreLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        highHighScoreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        highHighScoreLabel.setFocusable(false);
        highHighScoreLabel.setRolloverEnabled(false);
        highHighScoreLabel.setRequestFocusEnabled(false);
        highHighScoreLabel.setFocusPainted(false);

        btnStart.addActionListener(e -> start());
        btnShop.addActionListener(e -> shop());
        btnInventory.addActionListener(e -> inventory());
        btnExit.addActionListener(e -> exit());

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(titleLabel);
        buttonPanel.add(Box.createVerticalStrut(60));
        
        buttonPanel.add(btnStart);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(highHighScoreLabel);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(btnShop);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(btnInventory);
        buttonPanel.add(Box.createVerticalStrut(SPACING));
        buttonPanel.add(btnExit);
        buttonPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        this.frame.add(mainPanel);
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
        this.frame.setVisible(true);
    }

    @Override
    public void start() {
        this.frame.dispose();
        this.controller.start();
    }

    @Override
    public void shop() {
        this.frame.dispose();
        this.controller.shop();
    }

    @Override
    public void inventory() {
        this.frame.dispose();
        this.controller.inventory();
    }

    @Override
    public void exit() {
        this.frame.dispose();
        this.controller.exit();
    }

    public static void main(String[] args) {
        // Mock del controller
        MenuController mockController = new MenuControllerImpl( null) {
            @Override public void openViewMenu() {}
            @Override public void start() { System.out.println("Controller: Start Game triggered"); }
            @Override public void shop() { System.out.println("Controller: Shop triggered"); }
            @Override public void inventory() { System.out.println("Controller: Inventory triggered"); }
            @Override public void exit() { System.out.println("Controller: Exit triggered"); System.exit(0); }
            @Override public int getHighScore() { return 12500; };
        };

        SwingUtilities.invokeLater(() -> {
            MenuViewImpl menuView = new MenuViewImpl(mockController);
            menuView.display();
        });
    }
}
