package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.*;

/**
 * Represents the state where the game has been launched from the menu.
 * Acts as a transition state between the menu system and the active gameplay.
 */
public class LaunchedGameState extends BaseMenuState {

    /**
     * Constructs a new LaunchedGameState.
     * 
     * @param menu the Menu context
     */
    public LaunchedGameState(final Menu menu) {
        super(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        // Implementation of the execute method for LaunchedGameState
    }
    
}
