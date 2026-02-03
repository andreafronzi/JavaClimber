package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.api.Vector2d;

/**
 * A concrete implementation of the {@link Alien} interface.
 * This class represents an alien entity within the game, providing
 * logic for its dimensions, speed, physic behavior, and position updates.
 */
public class AlienImpl extends GameObj implements Alien {

  /**
   * The two-dimensional speed of the Alien.
   */
  private final Vector2d speed;

  /**
   * The Alien's physic.
   */
  private AlienPhysic physic;

  /**
   * Constructs a new Alien with the specified two-dimensional position, null speed, and specified width and height.
   */
  public AlienImpl(final Vector2d position, final Vector2d speed, final double width, final double height) {
    super(height, width, position);
    this.speed = speed;
    this.physic = new AlienNormalPhysic();

  }
  @Override
  public double getSpeedX() {
    return this.speed.getX();
  }

  @Override
  public double getSpeedY() {
    return this.speed.getY();
  }

  @Override
  public void setPhysic(final AlienPhysic physic) {
    this.physic = physic;
  }

  @Override
  public void setSpeed(final Vector2d speed) {
    this.speed.setX(speed.getX());
    this.speed.setY(speed.getY());
  }

  @Override
  public void updatePosition(final double dt, final Boundary boundary) {
    this.physic.update(this, dt, boundary);
  }
}
