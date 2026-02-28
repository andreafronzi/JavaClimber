package it.unibo.model.LaunchedGame.api;

import java.util.Optional;

import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.world.impl.World;

/**
 * Interface representing a running game session.
 * It manages the lifecycle and states (running, paused, ended) of an active game.
 */
public interface LaunchedGame extends CommandState<RunningCommand> {
    
    /**
     * Sets the current state of the launched game.
     * 
     * @param state the new state to set
     */
    public void setState(final StateOfLaunchedGame state);
    
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

    public Optional<World> getWorld();

    public void setWorld(final World world);
}
