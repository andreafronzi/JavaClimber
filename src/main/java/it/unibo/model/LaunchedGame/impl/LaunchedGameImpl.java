package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.BaseLaunchedState;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.api.StateOfLaunchedGame;

public class LaunchedGameImpl implements LaunchedGame{

    private BaseLaunchedState state;

    private boolean running;

    @Override
    public void setState(final BaseLaunchedState state) {
        this.state = state;
        state.onEnter();
    } 

    @Override
    public StateOfLaunchedGame getState() {
        return this.state;
    }

    @Override
    public void gameLoop() {
        while (running) {
            //prendi evento
            this.state.execute();
            //renderizza           
        }
    }
}
