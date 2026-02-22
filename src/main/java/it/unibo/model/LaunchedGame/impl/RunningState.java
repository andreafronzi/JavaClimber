package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.world.impl.World;


/**
 * Represents the state where the game is actively being played.
 * Handles the core gameplay logic.
 */
public class RunningState extends BaseLaunchedState {

    private final World world;
    private final CollisionManager collisionManager;

    /**
     * Constructs a new RunningState.
     * 
     * @param launchedGame the game context
     */
    public RunningState(final LaunchedGameImpl launchedGame, final World world, final CollisionManager collisionManager) {
        super(launchedGame);
        this.world = world;
        this.collisionManager = collisionManager;
    }

    /**
     * {@inheritDoc}
     * Executes the gameplay logic.
     */
    @Override
    public void execute(final double dt) {
        world.getRealWorld().getAlien().updatePosition(0, null);
        collisionManager.detectCollisions(world.getRealWorld());
    }
    
}