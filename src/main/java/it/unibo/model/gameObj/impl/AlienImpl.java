package it.unibo.model.gameObj.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.gameObj.api.StaticEntity;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * A concrete implementation of the {@link Alien} interface.
 * This class represents an alien entity within the game, providing
 * logic for its dimensions, speed, physic behavior, and position updates.
 */
public class AlienImpl extends GameObj implements Alien {

  /**
   * The active upgrades affecting the Alien.
   */
  private final ActiveUpgrades activeUpgrades;

  /**
   * The Alien's physic.
   */
  private AlienPhysic physic;

  /**
   * This flag is set to {@code true} when the Alien is currently moving to the left,
   * and {@code false} otherwise.
   */
  private boolean movingLeft;

  /**
   * This flag is set to {@code true} when the Alien is currently moving to the right,
   * and {@code false} otherwise.
   */
  private boolean movingRight;

  /**
   * The two-dimensional speed of the Alien.
   */
  private final Vector2d speed;

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
    this.movingLeft = false;
    this.movingRight = false;
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
  public boolean isMovingLeft() {
    return this.movingLeft;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMovingRight() {
    return this.movingRight;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveLeft() {
    this.movingLeft = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveRight() {
    this.movingRight = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyCollision(final StaticEntity gObj, final Boundary boundary, final GameWorld gameWorld) {
    gObj.onHitBy(this, this.physic, boundary, gameWorld, this.activeUpgrades);
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
  public void updatePosition(final double dt, final BoundWorld boundWorld, final LaunchedGame launchedGame) {
    this.physic.update(this, dt, boundWorld, activeUpgrades, launchedGame);
  }
}
