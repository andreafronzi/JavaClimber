package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.PlatformImpl;

public interface Director {

    public PlatformImpl normalPlatform(final Vector2d position);
    
    public PlatformImpl movingOnTouchPlatform(final Vector2d position);

    public PlatformImpl movingPlatform(final Vector2d position);

}
