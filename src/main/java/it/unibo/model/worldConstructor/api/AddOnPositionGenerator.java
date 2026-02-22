package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Interface for generating positions for add-ons (coins, enemies, gadgets).
 * Determines where an add-on should be placed relative to a platform.
 */
public interface AddOnPositionGenerator {

    /**
     * Generates a position for an add-on.
     * 
     * @param platformPosX  the X position of the platform
     * @param platformPosY  the Y position of the platform
     * @param platformWidth the width of the platform
     * @param addOnHeight   the height of the add-on
     * @param addOnWidth    the width of the add-on
     * @return the calculated position for the add-on
     */
    public Vector2d generatePosition(double platformPosX, double platformPosY, double platformWidth,
            double addOnHeight, double addOnWidth);
}
