package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

/**
 * Represents the state where the game is actively being played.
 * Handles the core gameplay logic.
 */
public class RunningState extends BaseLaunchedState {

    /**
     * Constructs a new RunningState.
     * 
     * @param launchedGame the game context
     */
    public RunningState(final LaunchedGameImpl launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     * Executes the gameplay logic.
     */
    @Override
    public void execute() {
        //logica di gioco in esecuzione
    }
    
}