package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.StateOfMenu;

public class MenuImpl implements Menu {

    private StateOfMenu currentState;

    public MenuImpl() {
        // Initialize with a default state if necessary
    }

    @Override
    public void setState() {
        // Implementation to set the current state
    }

    @Override
    public StateOfMenu getState() {
        return this.currentState;
    }
    
}
