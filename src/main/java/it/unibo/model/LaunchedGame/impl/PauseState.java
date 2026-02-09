package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

/**
 * Represents the state where the game is paused.
 * Suspends normal gameplay logic.
 */
public class PauseState extends BaseLaunchedState {

    /**
     * Constructs a new PauseState.
     * 
     * @param launchedGame the game context
     */
    public PauseState(final LaunchedGameImpl launchedGame) {
        super(launchedGame);
    }
    
    /**
     * {@inheritDoc}
     * Performs pause operations.
     */
    @Override
    public void onEnter() {
        //operazioni di pausa
    }
}