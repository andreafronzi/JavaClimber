package it.unibo.controller.impl;

import it.unibo.controller.api.MainController;
import it.unibo.controller.api.MenuController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.ShoppingState;
import it.unibo.model.score.api.ScoreManager;

public class MenuControllerImpl implements MenuController{

    private final Menu menu;
    private final MainController mainController;
    private final ScoreManager scoreManager;

    public MenuControllerImpl(MainController mainController, ScoreManager scoreManager) {
        this.menu = new MenuImpl();
        this.mainController = mainController;
        this.scoreManager = scoreManager;
    }

    @Override
    public void start() {
        menu.setState(new LaunchedGameState(menu));
        mainController.openGameLaunchedView();
    }

    @Override
    public void shop() {
        menu.setState(new ShoppingState(menu));
        mainController.openShopView();
    }

    @Override
    public void inventory() {
        menu.setState(new InventoryState(menu));
        mainController.openInventoryView();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void openViewMenu() {
        mainController.openMenuView();
    }

    @Override
    public int getHighScore() {
        return this.scoreManager.getHighScore();
    }
    
}
