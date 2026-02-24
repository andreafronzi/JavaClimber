package it.unibo.controller.impl;

import it.unibo.controller.api.MainController;
import it.unibo.controller.api.MenuController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.ShoppingState;

public class MenuControllerImpl implements MenuController{

    private final Menu menu;
    private final MainController mainController;

    public MenuControllerImpl(MainController mainController) {
        this.menu = new MenuImpl();
        this.mainController = mainController;
    }

    @Override
    public void start() {
        menu.setState(new LaunchedGameState(menu));
    }

    @Override
    public void shop() {
        menu.setState(new ShoppingState(menu));
    }

    @Override
    public void inventory() {
        menu.setState(new InventoryState(menu));
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }

    @Override
    public void openViewMenu() {
        mainController.openMenuView();
    }

    @Override
    public int getHighScore() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHighScore'");
    }
    
}
