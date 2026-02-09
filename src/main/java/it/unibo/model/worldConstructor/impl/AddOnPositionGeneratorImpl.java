package it.unibo.model.worldConstructor.impl;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * Implementation of AddOnPositionGenerator.
 * Calculates position for add-ons relative to their host platform.
 */
public class AddOnPositionGeneratorImpl {
    
    /**
     * Generates a position for an add-on.
     * 
     * @param posPlatform the position of the platform
     * @param platformHeight the height of the platform
     * @param addOnHeight the height of the add-on
     * @return the calculated position for the add-on
     */
    public Vector2d generatePosition(final double posX, final double posY, final double platformHeight, final double addOnHeight) {
        Vector2d pos = new Vector2dImpl(generatePosX(posX), generatePosY(posY, platformHeight, addOnHeight));
        return pos;
    }

    private double generatePosX(final double posX) {
        return posX; 
    }

    private double generatePosY(final double posY, final double platformHeight, final double addOnHeight) {
        return posY + platformHeight / 2 + addOnHeight / 2;
    }
    
}
