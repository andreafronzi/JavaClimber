package it.unibo.view.menu.impl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import it.unibo.controller.api.MenuController;
import it.unibo.view.menu.api.MenuView;

/**
 * Implementation of {@link MenuView} interface.
 */
public class MenuViewImpl extends JPanel implements MenuView{
    
    private final MenuController controller;
    private BufferedImage backgroundImage;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private static final int SPACING = 20;
    private JButton highScoreLabel;

    /**
     * Construct a MenuViewImpl with specified controller.
     * @param controller the controller for managing user interactions and updating the view
     */
    public MenuViewImpl(MenuController controller) {
        super(new BorderLayout());
        this.controller = controller;
        try {
            this.backgroundImage = ImageIO.read(new File("src/main/resources/background.jpeg"));
        } catch (IOException | NullPointerException e) {
            System.err.println("Errore: Immagine sfondo non trovata");
        }
        initialize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    /**
     * Initialize the view components.
     */
    private void initialize() {        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("JAVA CLIMBER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 70));
        titleLabel.setForeground(Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnStart = createMenuButton("START GAME");
        btnStart.setBackground(Color.WHITE);
        JButton btnShop = createMenuButton("SHOP");
        btnShop.setBackground(Color.WHITE);
        JButton btnInventory = createMenuButton("INVENTORY");
        btnInventory.setBackground(Color.WHITE);
        JButton btnExit = createMenuButton("EXIT");
        btnExit.setBackground(Color.WHITE);

        this.highScoreLabel = new JButton("HIGH SCORE: " + controller.getHighScore());
        this.highScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.highScoreLabel.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.highScoreLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        this.highScoreLabel.setBackground(Color.WHITE);
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
        buttonPanel.add(Box.createVerticalStrut(30));
        
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

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Helper method to create a styled menu button.
     * @param text the text to display on the button
     * @return the created button
     */
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Display the menu view.
     */
    public void display() {
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHighScore(int score) {
        if (this.highScoreLabel != null) {
            this.highScoreLabel.setText("HIGH SCORE: " + score);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shop() {
        this.controller.shop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inventory() {
        this.controller.inventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        this.controller.exit();
    }

}
