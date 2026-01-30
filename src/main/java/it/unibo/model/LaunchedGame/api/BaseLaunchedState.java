package it.unibo.model.LaunchedGame.api;

public abstract class BaseLaunchedState implements StateOfLaunchedGame {

    private LaunchedGame launchedGame;

    public BaseLaunchedState(final LaunchedGame launchedGame) {
        this.launchedGame = launchedGame;
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void execute() {
    }
                        
}