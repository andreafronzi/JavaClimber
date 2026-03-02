package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl;

import java.util.Random;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPositionGenerator;

/**
 * Implementation of PlatformPositionGenerator.
 * Generates random positions for new platforms within a specified range
 * (defined by difficulty)
 * relative to the previous platform.
 */
public class PlatformPositionGeneratorImpl implements PlatformPositionGenerator {

    private Distance distance;
    private Vector2d previousPlatformPosition;
    private Vector2d newPlatformPosition;
    private final Random randomNumber;
    private final Boundary boundX;

    /**
     * Constructs a new PlatformPositionGeneratorImpl.
     */
    public PlatformPositionGeneratorImpl(final BoundWorld boundWorld, final Vector2d platformPos, final Distance distance) {
        this.randomNumber = new Random();
        // previousPlatformPosition.setX(platform.getPosX());
        // previousPlatformPosition.setY(platform.getPosY());
        previousPlatformPosition = platformPos;
        this.distance = distance;
        this.boundX = boundWorld.getBoundX();
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
    public void setDistance(final Distance distance) {
        this.distance = distance;
    }

    private void genPosX(final double width) {
        double xMin;
        double xMax;

        if (previousPlatformPosition.getX() - distance.maxDistanceX() < boundX.x0()) {
            xMin = boundX.x0();
        } else {
            xMin = previousPlatformPosition.getX() - distance.maxDistanceX();
        }
        if (previousPlatformPosition.getX() + distance.maxDistanceX() + width > boundX.x1()) {
            xMax = boundX.x1() - width;
        } else {
            xMax = previousPlatformPosition.getX() + distance.maxDistanceX() - width;
        }

        newPlatformPosition.setX(randomNumber.nextDouble(xMin, xMax));

    }

    private void genPosY(final double height) {
        newPlatformPosition.setY(randomNumber.nextDouble(previousPlatformPosition.getY() - distance.maxDistanceY(),
                previousPlatformPosition.getY() - distance.minDistanceY() - height));
    }

    private void setPreviousPosition(final Vector2d pos) {
        this.previousPlatformPosition = pos;
    }

}
