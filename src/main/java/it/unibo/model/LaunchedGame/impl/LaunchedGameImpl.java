package it.unibo.model.LaunchedGame.impl;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import it.unibo.model.LaunchedGame.api.BaseLaunchedState;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.api.StateOfLaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.world.impl.World;

/**
 * Implementation of the LaunchedGame interface.
 * Manages the transition between different game states and runs the main game
 * loop.
 */
public class LaunchedGameImpl implements LaunchedGame {

    private StateOfLaunchedGame state;
    private Optional<World> world;
    private final Queue<RunningCommand> commands;

    private boolean running;

    public LaunchedGameImpl() {
        this.commands = new ArrayBlockingQueue<>(100);
    }

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

    @Override
    public Optional<World> getWorld() {
        return this.world;
    }

    @Override
    public void setWorld(World world) {
        this.world = Optional.of(world);
    }

    @Override
    public void addCommand(final RunningCommand command) {
        commands.add(command);
    }

    @Override
    public RunningCommand pollCommand() {
        return commands.poll();
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void setRunning(final boolean running) {
        this.running = running;
    }
}
