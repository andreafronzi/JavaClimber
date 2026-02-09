package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.StateOfMenu;

/**
 * Concrete implementation of the Menu interface.
 * Represents the context of the initial game menu
 * where you can start the various functions of the game.
 */
public class MenuImpl implements Menu {

    private StateOfMenu currentState;

    /**
     * Creates a new MenuImpl instance.
     */
    public MenuImpl() {
        // Initialize with a default state if necessary
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(StateOfMenu state) {
            this.currentState = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateOfMenu getState() {
        return this.currentState;
    }
    
}
