package it.unibo.controller.impl;

import it.unibo.controller.api.MainController;
import it.unibo.controller.api.MenuController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.ShoppingState;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.view.menu.api.MenuView;

public class MenuControllerImpl implements MenuController{

    private final Menu menu;
    private final MainController mainController;
    private final ScoreManager scoreManager;
    private MenuView view;

    public MenuControllerImpl(MainController mainController, ScoreManager scoreManager) {
        this.menu = new MenuImpl();
        this.mainController = mainController;
        this.scoreManager = scoreManager;
    }

    @Override
    public void setView(MenuView view) {
        this.view = view;
        refreshView();
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
    
    private void refreshView() {
        if (this.view != null) {
            this.view.updateHighScore(this.getHighScore());
        }
    }
}
