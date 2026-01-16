package it.unibo.model.menu.LaunchedGame.impl;

import it.unibo.model.menu.LaunchedGame.api.BaseLaunchedState;
import it.unibo.model.menu.LaunchedGame.api.LaunchedGame;
import it.unibo.model.menu.LaunchedGame.api.StateOfLaunchedGame;

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
