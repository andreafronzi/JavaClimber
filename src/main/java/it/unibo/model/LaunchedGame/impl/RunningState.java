package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;
import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.world.impl.World;

/**
 * Represents the state where the game is actively being played.
 * Handles the core gameplay logic.
 */
public class RunningState extends BaseLaunchedState {

    private final World world;
    private final CollisionManager collisionManager;
    private final AltitudeManager altitudeManager;
    private final ScoreManager scoreManager;   

    /**
     * Constructs a new RunningState.
     * 
     * @param launchedGame the game context
     */
    public RunningState(final LaunchedGame launchedGame, final World world, final CollisionManager collisionManager,
            final AltitudeManager altitudeManager, final ScoreManager scoreManager) {
        super(launchedGame);
        this.world = world;
        this.collisionManager = collisionManager;
        this.altitudeManager = altitudeManager;
        this.scoreManager = scoreManager;
    }

    /**
     * {@inheritDoc}
     * Executes the gameplay logic.
     */
    @Override
    public void execute(final double dt) {
        world.getRealWorld().getAlien().updatePosition(dt, world.getRealWorld().getBoundWorld(), this.launchedGame);
        collisionManager.detectCollisions(world.getRealWorld());
        scoreManager.updateScore(world.getRealWorld().getAlien().getPosY());
        altitudeManager.verifiedAltitude();
    }

}