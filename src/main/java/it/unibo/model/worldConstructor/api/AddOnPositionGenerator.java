package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Interface for generating positions for add-ons (coins, enemies, gadgets).
 * Determines where an add-on should be placed relative to a platform.
 */
public interface AddOnPositionGenerator {
    
    /**
     * Generates a position for an add-on based on the platform's position.
     * 
     * @param posPlatform the position of the platform hosting the add-on
     * @return a Vector2d representing the add-on's coordinates
     */
    public Vector2d generatePosition(final Vector2d posPlatform);
}
