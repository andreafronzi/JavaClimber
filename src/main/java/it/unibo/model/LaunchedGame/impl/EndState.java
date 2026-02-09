package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

/**
 * Represents the final state of a game session.
 * Handles end-game logic like saving scores or returning to menu.
 */
public class EndState extends BaseLaunchedState {

    /**
     * Constructs a new EndState.
     * 
     * @param launchedGame the game context
     */
    public EndState(final LaunchedGameImpl launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     * Performs end-game operations.
     */
    @Override
    public void onEnter() {
        //operazioni di salvataggio
    }
}
