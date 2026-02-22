package it.unibo.model.LaunchedGame.api;

/**
 * Interface representing a specific state of a launched game.
 * States handle specific logic for different phases like initialization, running, pause, and end.
 */
public interface StateOfLaunchedGame {
    
    /**
     * Executes the logic specific to this state during the game loop.
     */
    public void execute(double dt);
    
    /**
     * Logic to be executed when the game enters this state.
     */
    public void onEnter();
}
