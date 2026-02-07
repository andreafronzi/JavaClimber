package it.unibo.model.worldConstructor.api;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;


public interface Director {

    public Platform normalPlatform(final Vector2d position);
    
    public Platform movingOnTouchPlatform(final Vector2d position);

    public Platform movingPlatform(final Vector2d position);
}
