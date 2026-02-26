package it.unibo.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.api.EndController;
import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.InventoryController;
import it.unibo.controller.api.MainController;
import it.unibo.controller.api.MenuController;
import it.unibo.controller.api.PauseController;
import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.view.GameLaunchedView.impl.GameLaunchedViewPanelImpl;
import it.unibo.view.inventory.impl.InventoryViewImpl;
import it.unibo.view.menu.impl.MenuViewImpl;
import it.unibo.view.shop.api.ShopView;
import it.unibo.view.shop.impl.ShopViewImpl;

public class MainViewImpl implements MainView {

    private final JFrame frame;
    private JPanel currentPanel;

    public MainViewImpl() {
        this.frame = new JFrame("Java Climber");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1000, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void switchPanel(JPanel newPanel) {
        if (currentPanel != null) {
            frame.remove(currentPanel);
        }
        currentPanel = newPanel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void setMenuView(MenuController menuController) {
        MenuViewImpl menuView = new MenuViewImpl(menuController);
        menuController.setView(menuView);
        switchPanel(menuView);    
    }

    @Override
    public void setGameLaunchedView(GameLaunchedController gameLaunchedController) {
        GameLaunchedViewPanelImpl gameLaunchedView = new GameLaunchedViewPanelImpl(gameLaunchedController);
        gameLaunchedController.setView(gameLaunchedView);
        switchPanel(gameLaunchedView);
    }

    @Override
    public void setInventoryView(InventoryController inventoryController) {
        InventoryViewImpl inventoryView = new InventoryViewImpl(inventoryController);
        inventoryController.setView(inventoryView);
        switchPanel(inventoryView);
    }

    @Override
    public void setShopView(ShopController shopController) {
        ShopViewImpl shopView = new ShopViewImpl(shopController);
        shopController.setView(shopView);
        switchPanel(shopView);
    }

    @Override
    public void setEndView(EndController endController) {
        EndViewImpl endView = new EndViewImpl(endController);
        endController.setView(endView);
        switchPanel(endView);
    }

    @Override
    public void setPauseView(PauseController pauseController) {
        PauseViewImpl pauseView = new PauseViewImpl(pauseController);
        pauseController.setView(pauseView);
        switchPanel(pauseView);
    }
    
}
