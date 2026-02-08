package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.worldConstructor.api.PlatformControlDistance;

public class PlatformControlDistanceImpl implements PlatformControlDistance {

    private Difficult difficult;
    private PlatformImpl previousPlatform;
    private final Random randomNumber;

    PlatformControlDistanceImpl() {
        this.randomNumber = new Random();
    }

    @Override
    public Vector2d generatePosition() {
        Vector2d pos = new Vector2dImpl(800, 800);
        while (pos.getX() > previousPlatform.getPosition().getX() + difficult.maxDistanceX()) {
            pos.setX(randomNumber.nextDouble(difficult.maxDistanceX()));
        }
        while (pos.getY() > previousPlatform.getPosition().getY() + difficult.maxDistanceY()
                && pos.getY() < previousPlatform.getPosition().getY() + difficult.maxDistanceY()) {
            pos.setY(randomNumber.nextDouble(difficult.minDistanceY(), difficult.maxDistanceY()));
        }
        return pos;
    }

    @Override
    public void setDifficult(final Difficult difficult) {
        this.difficult = difficult;
    }

}
