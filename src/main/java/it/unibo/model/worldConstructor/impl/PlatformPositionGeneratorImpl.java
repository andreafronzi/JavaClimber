package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.worldConstructor.api.PlatformPositionGenerator;

/**
 * Implementation of PlatformPositionGenerator.
 * Generates random positions for new platforms within a specified range
 * (defined by difficulty)
 * relative to the previous platform.
 */
public class PlatformPositionGeneratorImpl implements PlatformPositionGenerator {

    private Difficult difficult;
    private Vector2d previousPlatformPosition;
    private Vector2d newPlatformPosition;
    private final Random randomNumber;

    /**
     * Constructs a new PlatformPositionGeneratorImpl.
     */
    PlatformPositionGeneratorImpl() {
        this.randomNumber = new Random();
        previousPlatformPosition = new Vector2dImpl(400, 400);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d generatePosition(final double width, final double height) {
        genPosX(previousPlatformPosition, width);
        genPosY(previousPlatformPosition, height);
        setPreviousPosition(newPlatformPosition);
        return newPlatformPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDifficult(final Difficult difficult) {
        this.difficult = difficult;
    }

    private void genPosX(final Vector2d pos, final double width) {
        while (pos.getX() > previousPlatformPosition.getX() + difficult.maxDistanceX()
                && pos.getX() < previousPlatformPosition.getX() - difficult.maxDistanceX() 
            && pos.getX() + width < //boundx ) {
            newPlatformPosition.setX(randomNumber.nextDouble(previousPlatformPosition.getX() - difficult.maxDistanceX(),
                    previousPlatformPosition.getX() + difficult.maxDistanceX()));
        }
    }

    private void genPosY(final Vector2d pos, final double height) {
        while (pos.getY() > previousPlatformPosition.getY() + difficult.maxDistanceY()
                && pos.getY() < previousPlatformPosition.getY() + difficult.minDistanceY()) {
            newPlatformPosition.setY(randomNumber.nextDouble(previousPlatformPosition.getY() + difficult.minDistanceY(),
                    previousPlatformPosition.getY() + difficult.maxDistanceY()));
        }
    }

    private void setPreviousPosition(final Vector2d pos) {
        this.previousPlatformPosition = pos;
    }
}
