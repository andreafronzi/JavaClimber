package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.Distance;

/**
 * Interface for generating positions for new platforms.
 * Calculates valid coordinates based on difficulty parameters and previous platform positions.
 */
public interface PlatformPositionGenerator {
    
    /**
     * Generates a new position for a platform.
     * 
     * @return a Vector2d representing the new coordinates
     */
    public Vector2d generatePosition(double width, double height);

    /**
     * Sets the difficulty context, which influences position generation parameters (e.g., distance).
     * 
     * @param distance the current distance settings
     */
    public void setDistance(Distance distance);

}
