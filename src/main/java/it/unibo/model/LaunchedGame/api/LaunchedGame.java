package it.unibo.model.LaunchedGame.api;

/**
 * Interface representing a running game session.
 * It manages the lifecycle and states (running, paused, ended) of an active game.
 */
public interface LaunchedGame {
    
    /**
     * Sets the current state of the launched game.
     * 
     * @param state the new state to set
     */
    public void setState(final BaseLaunchedState state);
    
    /**
     * Gets the current state of the launched game.
     * 
     * @return the current state
     */
    public StateOfLaunchedGame getState();

    /**
     * Starts the main game loop.
     */
    public void gameLoop();
}
