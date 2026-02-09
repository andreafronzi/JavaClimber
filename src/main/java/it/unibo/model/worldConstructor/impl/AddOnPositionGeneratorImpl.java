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
     * @param platformPosX   the X position of the platform
     * @param platformPosY   the Y position of the platform
     * @param platformWidth  the width of the platform
     * @param addOnHeight    the height of the add-on
     * @param addOnWidth     the width of the add-on
     * @return the calculated position for the add-on
     */
    public Vector2d generatePosition(final double platformPosX, final double platformPosY, final double platformWidth,
            final double addOnHeight, final double addOnWidth) {
        Vector2d pos = new Vector2dImpl(generatePosX(platformPosX, platformWidth, addOnWidth),
                generatePosY(platformPosY, addOnHeight));
        return pos;
    }

    private double generatePosX(final double platformPosX, final double platformWidth, final double addOnWidth) {
        return platformPosX + (platformWidth / 2) - (addOnWidth / 2); // posizione iniziale della piattaforma + meta larghezza della piattaforma - meta larghezza dell'add-on
    }

    private double generatePosY(final double platformPosY, final double addOnHeight) {
        return platformPosY + addOnHeight; // posizione iniziale della piattaforma + altezza dell'add-on
    }

}
