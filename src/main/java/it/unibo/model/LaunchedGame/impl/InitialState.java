package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

/**
 * Represents the initial state of a launched game.
 * Used for setup and initialization before the game starts running.
 */
public class InitialState extends BaseLaunchedState {

    /**
     * Constructs a new InitialState.
     * 
     * @param launchedGame the game context
     */
    public InitialState(final LaunchedGameImpl launchedGame) {
        super(launchedGame);
    }
    
    /**
     * {@inheritDoc}
     * Performs initialization tasks.
     */
    @Override
    public void onEnter() {
        //operazioni di inizializzazione
    }
}
