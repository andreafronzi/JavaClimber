package it.unibo.model.physics.impl;

import it.unibo.model.physics.api.Position;

public class PositionImpl implements Position {
    private double x;
    private double y;

    public PositionImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

}
