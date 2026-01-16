package it.unibo.model.physics.impl;

import it.unibo.model.physics.api.Vector2d;

public class Vector2dImpl implements Vector2d {
    private double x;
    private double y;

    public Vector2dImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

  @Override
  public void setX(double x) {
    this.x = x;
  }

  @Override
  public void setY(double y) {
    this.y = y;
  }

}
