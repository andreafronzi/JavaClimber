package it.unibo.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.api.*;
import it.unibo.view.GameLaunchedView.impl.GameLaunchedViewPanelImpl;
import it.unibo.view.inventory.impl.InventoryViewImpl;
import it.unibo.view.menu.impl.MenuViewImpl;
import it.unibo.view.shop.impl.ShopViewImpl;

public class MainViewImpl implements MainView {

    public static final int DECREASE_SCREEN_SIZE = 2;

    private final JFrame frame;
    private JPanel currentPanel;

    public MainViewImpl() {
        this.frame = new JFrame("Java Climber");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600, 800);
        // this.setWindowSize();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void switchPanel(final JPanel newPanel) {
        if (currentPanel != null) {
            frame.remove(currentPanel);
        }
        currentPanel = newPanel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void setWindowSize() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int width = (int) screenSize.getWidth();
        final int height = (int) screenSize.getHeight();
        this.frame.setSize(width, height);
    }

    @Override
    public void setMenuView(final MenuController menuController) {
        final MenuViewImpl menuView = new MenuViewImpl(menuController);
        menuController.setView(menuView);
        switchPanel(menuView);
    }

    @Override
    public void setGameLaunchedView(final GameLaunchedController gameLaunchedController,
            final GameLaunchedInputController gameLaunchedInputController) {
        final GameLaunchedViewPanelImpl gameLaunchedView = new GameLaunchedViewPanelImpl(gameLaunchedController,
                gameLaunchedInputController);
        gameLaunchedController.setPanel(gameLaunchedView);
        switchPanel(gameLaunchedView);

        gameLaunchedView.setFocusable(true);
        gameLaunchedView.requestFocusInWindow();

    }

    @Override
    public void setInventoryView(final InventoryController inventoryController) {
        final InventoryViewImpl inventoryView = new InventoryViewImpl(inventoryController);
        inventoryController.setView(inventoryView);
        switchPanel(inventoryView);
    }

    @Override
    public void setShopView(final ShopController shopController) {
        final ShopViewImpl shopView = new ShopViewImpl(shopController);
        shopController.setView(shopView);
        switchPanel(shopView);
    }

    @Override
    public void setEndView(final EndController endController) {
        final EndViewImpl endView = new EndViewImpl(endController);
        switchPanel(endView);
    }

    @Override
    public void setPauseView(final PauseController pauseController) {
        final PauseViewImpl pauseView = new PauseViewImpl(pauseController);
        switchPanel(pauseView);
    }

}
