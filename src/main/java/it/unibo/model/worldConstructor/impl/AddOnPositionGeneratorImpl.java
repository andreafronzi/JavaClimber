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
     * @return the calculated position for the add-on
     */
    public Vector2d generatePosition(final Vector2d posPlatform) {
        Vector2d pos = new Vector2dImpl(generatePosX(posPlatform), generatePosY(posPlatform));
        return pos;
    }

    private double generatePosX(final Vector2d posPlatform) {
        return posPlatform.getX(); 
    }

    private double generatePosY(final Vector2d posPlatform) {
        return posPlatform.getY(); //meta altezza piattaforma + meta altezza add-on
    }
    
}
