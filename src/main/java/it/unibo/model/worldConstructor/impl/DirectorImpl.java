package it.unibo.model.worldConstructor.impl;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.worldConstructor.api.Director;

public class DirectorImpl implements Director {

    private final double width;
    private final double height;

    public DirectorImpl(final double width, final double height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public PlatformImpl normalPlatform(Vector2d position) {
        return new BuilderPlatformImpl()
                .at(position)
                .size(width, height)
                .build();
    }

    @Override
    public PlatformImpl movingOnTouchPlatform(Vector2d position) {
                return new BuilderPlatformImpl()
                .at(position)
                .size(width, height)
                .addJumpBehaviour()
                .build();
    }

    @Override
    public PlatformImpl movingPlatform(Vector2d position) {
        return new BuilderPlatformImpl()
                .at(position)
                .size(width, height)
                .addMovementBehaviour()
                .build();
    }
    
}
