package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.api.Vector2d;


public class AlienImpl extends GameObj implements Alien {

  /**
   * The two-dimensional speed of the Alien.
   */
  private final Vector2d speed;

  private final double width;
  private final double heigth;

  /**
   * Constructs a new Alien with the specified two-dimensional position, null speed, and specified width and heigth.
   */
  public AlienImpl(final Vector2d position, final Vector2d speed, final double width, final double heigth) {
    super(position);
    this.speed = speed;
    this.width = width;
    this.heigth = heigth;

  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeigth() {
    return this.heigth;
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
