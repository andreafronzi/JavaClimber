package it.unibo.controller.impl;

import it.unibo.controller.api.MenuController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.MenuImpl;

public class MenuControllerImpl implements MenuController{

    private final Menu menu;
    private 

    public MenuControllerImpl() {
        this.menu = new MenuImpl();
    }

    @Override
    public void start() {
        menu.setState(new LaunchedGameState(menu));
    }

    @Override
    public void shop() {

    }

    @Override
    public void inventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inventory'");
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }
    
}
