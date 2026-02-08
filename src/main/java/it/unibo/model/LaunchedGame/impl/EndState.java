package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

public class EndState extends BaseLaunchedState {

    public EndState(final LaunchedGameImpl launchedGame) {
        super(launchedGame);
    }

    @Override
    public void onEnter() {
        //operazioni di salvataggio
    }
}
