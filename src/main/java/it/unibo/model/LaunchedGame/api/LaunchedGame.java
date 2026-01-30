package it.unibo.model.LaunchedGame.api;

public interface LaunchedGame {
    
    public void setState(final BaseLaunchedState state);
    
    public StateOfLaunchedGame getState();

    public void gameLoop();
}
