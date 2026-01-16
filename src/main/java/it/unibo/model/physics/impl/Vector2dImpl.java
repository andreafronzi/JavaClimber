package it.unibo.model.physics.impl;

import it.unibo.model.physics.api.Vector2d;

/**
 * Implementation of a two-dimensional vector (Cartesian Coordinates) which can be used to rapresent either Position or Speed.
 */
public class Vector2dImpl implements Vector2d {

  /**
   * The x-coordinate of the vector.
   */
  private double x;
  /**
   * The y-coordinate of the vector.
   */
  private double y;

  /**
   * Constructs a two-dimensional vector with the specified x and y coordinates.
   *
   * @param x the x-coordinate of the vector
   * @param y the y-coordinate of the vector
   */
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
