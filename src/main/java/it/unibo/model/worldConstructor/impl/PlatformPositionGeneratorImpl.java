package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.api.PlatformPositionGenerator;

public class PlatformControlDistanceImpl implements PlatformPositionGenerator {

    private Difficult difficult;
    private Vector2d previousPlatformPosition;
    private final Random randomNumber;

    PlatformControlDistanceImpl() {
        this.randomNumber = new Random();
    }

    @Override
    public Vector2d generatePosition() {
        Vector2d pos = new Vector2dImpl(800, 800);
        genPosX(pos);
        genPosY(pos);
        setPreviousPosition(pos);
        return pos;
    }

    @Override
    public void setDifficult(final Difficult difficult) {
        this.difficult = difficult;
    }

    private void genPosX(final Vector2d pos) {
        while (pos.getX() > previousPlatformPosition.getX() + difficult.maxDistanceX()) {
            pos.setX(randomNumber.nextDouble(difficult.maxDistanceX()));
        }
    }

    private void genPosY(final Vector2d pos) {
        while (pos.getY() > previousPlatformPosition.getY() + difficult.maxDistanceY()
                && pos.getY() < previousPlatformPosition.getY() + difficult.maxDistanceY()) {
            pos.setY(randomNumber.nextDouble(difficult.minDistanceY(), difficult.maxDistanceY()));
        }
    }

    private void setPreviousPosition(final Vector2d pos){
        this.previousPlatformPosition = pos;
    }
}
