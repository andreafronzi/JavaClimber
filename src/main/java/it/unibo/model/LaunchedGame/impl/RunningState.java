package it.unibo.model.LaunchedGame.impl;


import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import it.unibo.model.LaunchedGame.api.*;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.world.impl.World;


/**
 * Represents the state where the game is actively being played.
 * Handles the core gameplay logic.
 */
public class RunningState extends BaseLaunchedState implements CommandState<RunningCommand> {

    private final World world;
    private final CollisionManager collisionManager;
    private final Queue<RunningCommand> commands;

    /**
     * Constructs a new RunningState.
     * 
     * @param launchedGame the game context
     */
    public RunningState(final LaunchedGame launchedGame, final World world, final CollisionManager collisionManager) {
        super(launchedGame);
        this.world = world;
        this.collisionManager = collisionManager;
        this.commands = new ArrayBlockingQueue<>(100);
    }

    /**
     * {@inheritDoc}
     * Executes the gameplay logic.
     */
    @Override
    public void execute(final double dt) {
        world.getRealWorld().getAlien().updatePosition(dt, null);
        collisionManager.detectCollisions(world.getRealWorld());
        //controlliamo se il personaggio è morto se è morto, passiamo allo stato di endstate 
        launchedGame.setState(new EndState(launchedGame));
    }

    @Override
    public void addCommand(final RunningCommand command) {
        commands.add(command);
    }
 
    private RunningCommand pollCommand() {
        return commands.poll();
    }

}