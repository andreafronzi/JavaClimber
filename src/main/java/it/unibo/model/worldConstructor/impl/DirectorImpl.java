package it.unibo.model.worldConstructor.impl;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformPhysic.impl.HorizontalMovementBehavior;
import it.unibo.model.worldConstructor.api.Director;
import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;


/**
 * Implementation of the Director interface.
 * Uses the PlatformBuilder to construct specific platform variants (Normal, Moving, etc.).
 */
public class DirectorImpl implements Director {

    private final double width;
    private final double height;

    /**
     * Constructs a new DirectorImpl.
     * 
     * @param width the default width for platforms
     * @param height the default height for platforms
     */
    public DirectorImpl(final double width, final double height) {
        this.width = width;
        this.height = height;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Platform normalPlatform(Vector2d position) {
        return new PlatformBuilderImpl()
                .at(position)
                .size(width, height)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform movingOnTouchPlatform(Vector2d position) {
                return new PlatformBuilderImpl()
                .at(position)
                .size(width, height)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform movingPlatform(Vector2d position) {
        return new PlatformBuilderImpl()
                .at(position)
                .size(width, height)
                .addMovementBehaviour(new HorizontalMovementBehavior(0))
                .build();
    }
    
}
