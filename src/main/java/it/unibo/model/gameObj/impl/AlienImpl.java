package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.gameObj.api.StaticEntity;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;

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
   * The active upgrades affecting the Alien.
   */
  private final ActiveUpgrades activeUpgrades;

  /**
   * Constructs a new Alien with the specified two-dimensional position, null speed, and specified width and height.
   * 
   * @param position the initial position of the Alien
   * @param speed the initial speed of the Alien
   * @param width the width of the Alien
   * @param height the height of the Alien
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  public AlienImpl(final Vector2d position, final Vector2d speed, final double width, final double height, final ActiveUpgrades activeUpgrades) {
    super(height, width, position);
    this.speed = speed;
    this.physic = new AlienNormalPhysic();
    this.activeUpgrades = activeUpgrades;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSpeedX() {
    return this.speed.getX();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSpeedY() {
    return this.speed.getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyAltitude() {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyCollision(final StaticEntity gObj, final Boundary boundary) {
    gObj.onHitBy(this, this.physic, boundary);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPhysic(final AlienPhysic physic) {
    this.physic = physic;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSpeed(final Vector2d speed) {
    this.speed.setX(speed.getX());
    this.speed.setY(speed.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePosition(final double dt, final Boundary boundary) {
    this.physic.update(this, dt, boundary, activeUpgrades);
  }
}
