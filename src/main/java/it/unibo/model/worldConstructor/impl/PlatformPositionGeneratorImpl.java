package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
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
    private final Boundary boundX;

    /**
     * Constructs a new PlatformPositionGeneratorImpl.
     */
    public PlatformPositionGeneratorImpl(final Boundary boundX, final BoundY boundY, final Platform platform) {
        this.randomNumber = new Random();
        // previousPlatformPosition.setX(platform.getPosX());
        // previousPlatformPosition.setY(platform.getPosY());
        previousPlatformPosition = new Vector2dImpl(boundX.x1() / 2, boundY.maxY() - 20);
        this.boundX = boundX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d generatePosition(final double width, final double height) {
        this.newPlatformPosition = new Vector2dImpl(0, 0);
        genPosX(width);
        genPosY(height);
        setPreviousPosition(newPlatformPosition);
        return previousPlatformPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDifficult(final Difficult difficult) {
        this.difficult = difficult;
    }

    private void genPosX(final double width) {
        double xMin;
        double xMax;

        if (previousPlatformPosition.getX() - difficult.maxDistanceX() < boundX.x0()) {
            xMin = boundX.x0();
        } else {
            xMin = previousPlatformPosition.getX() - difficult.maxDistanceX();
        }
        if (previousPlatformPosition.getX() + difficult.maxDistanceX() + width > boundX.x1()) {
            xMax = boundX.x1() - width;
        } else {
            xMax = previousPlatformPosition.getX() + difficult.maxDistanceX() - width;
        }

        newPlatformPosition.setX(randomNumber.nextDouble(xMin, xMax));

    }

    private void genPosY(final double height) {
        newPlatformPosition.setY(randomNumber.nextDouble(previousPlatformPosition.getY() - difficult.maxDistanceY(),
                previousPlatformPosition.getY() - difficult.minDistanceY() - height));
    }

    private void setPreviousPosition(final Vector2d pos) {
        this.previousPlatformPosition = pos;
    }

}
