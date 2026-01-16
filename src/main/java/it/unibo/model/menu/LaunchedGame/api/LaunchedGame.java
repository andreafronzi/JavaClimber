package it.unibo.model.menu.LaunchedGame.api;

public interface LaunchedGame {
    
    public void setState(final BaseLaunchedState state);
    
    public StateOfLaunchedGame getState();

    public void gameLoop();
}
