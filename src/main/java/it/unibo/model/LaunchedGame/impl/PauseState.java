package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

public class PauseState extends BaseLaunchedState {

    public PauseState(final LaunchedGameImpl launchedGame) {
        super(launchedGame);
    }
    
    @Override
    public void onEnter() {
        //operazioni di pausa
    }
}