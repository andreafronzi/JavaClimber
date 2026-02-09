package it.unibo.model.worldConstructor.api;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;


/**
 * Interface for the Director in the Builder pattern.
 * Defines methods to construct specific types of platforms.
 */
public interface Director {

    /**
     * Creates a normal static platform.
     * 
     * @param position the position where the platform will be created
     * @return the created Platform
     */
    public Platform normalPlatform(final Vector2d position);
    
    /**
     * Creates a platform that starts moving when touched by the player.
     * 
     * @param position the position where the platform will be created
     * @return the created Platform
     */
    public Platform movingOnTouchPlatform(final Vector2d position);

    /**
     * Creates a platform that is constantly moving.
     * 
     * @param position the position where the platform will be created
     * @return the created Platform
     */
    public Platform movingPlatform(final Vector2d position);
}
