package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;


public class AlienImpl extends GameObj implements Alien {

  private static final double INITIAL_SPEED_COORDINATE = 0;

  /**
   * The two-dimensional speed of the Alien.
   */
  private final Vector2d speed;

  /**
   * Constructs a new Alien with the specified two-dimensional position, and null speed.
   */
  public AlienImpl(final Vector2d position) {
    super(position);
    this.speed = new Vector2dImpl(INITIAL_SPEED_COORDINATE, INITIAL_SPEED_COORDINATE);
  }

  public double getSpeedX() {
    return this.speed.getX();
  }

  public double getSpeedY() {
    return this.speed.getY();
  }

  @Override
  public void setSpeed(final Vector2d speed) {
    this.speed.setX(speed.getX());
    this.speed.setY(speed.getY());
  }
}
