package it.unibo.model.LaunchedGame.impl;

import java.util.Optional;

import it.unibo.model.LaunchedGame.api.BaseLaunchedState;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.api.StateOfLaunchedGame;
import it.unibo.model.world.impl.World;

/**
 * Implementation of the LaunchedGame interface.
 * Manages the transition between different game states and runs the main game
 * loop.
 */
public class LaunchedGameImpl implements LaunchedGame {

    private StateOfLaunchedGame state;
    private Optional<World> world;

    private boolean running;

    /**
     * {@inheritDoc}
     * Also triggers the {@link BaseLaunchedState#onEnter()} method for the new
     * state.
     */
    @Override
    public void setState(final StateOfLaunchedGame state) {
        this.state = state;
        state.onEnter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateOfLaunchedGame getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameLoop() {
        while (running) {
            // prendi evento
            this.state.execute();
            // renderizza
        }
    }

    @Override
    public Optional<World> getWorld() {
        return this.world;
    }

    @Override
    public void setWorld(World world) {
        this.world = Optional.of(world);
    }
}
