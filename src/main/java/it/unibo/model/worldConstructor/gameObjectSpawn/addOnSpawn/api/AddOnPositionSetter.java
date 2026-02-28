package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import it.unibo.model.gameObj.api.GameObject;

/**
 * Interface for generating positions for add-ons (coins, enemies, gadgets).
 * Determines where an add-on should be placed relative to a platform.
 */
public interface AddOnPositionSetter {

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
    public <X extends GameObject> X generatePosition(X addOn, double platformWidth);
}
