package it.unibo.model.physics.alienPhysic.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.alienPhysic.api.TemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents the alien physic when the alien collects the EliCap gadget. The alien will have a vertical speed for a certain time interval, then it will return to normal physic.
 */
public class AlienEliCapPhysic extends TemplatePhysic {
  /**
   * Represents the duration time of the gadget effect.
   */
  private double timeInterval;

  /**
   * Represents the vertical speed of the gadget effect.
   */
  private final double verticalSpeed;

  /**
   * Constructor of AlienEliCapPhysic.
   *
   * @param timeInterval the duration time of the gadget effect
   * @param verticalSpeed the vertical speed of the gadget effect
   */
  public AlienEliCapPhysic(final double timeInterval, final double verticalSpeed) {
      this.timeInterval = timeInterval;
      this.verticalSpeed = verticalSpeed;
  }

  /**
   * Update alien position and speed using the gadget effect.
   *
   * @param alien the alien to update
   * @param dt the time step
   * @param boundary the boundary of the world
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  @Override
  protected void moveAlien(final Alien alien, final double dt,  final Boundary boundary, final ActiveUpgrades activeUpgrades) {
    alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), this.verticalSpeed));
    if (this.timeInterval - dt >= 0) {
      alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt * activeUpgrades.getSpeedMultiplier(), alien.getPosY() + alien.getSpeedY() * dt * activeUpgrades.getSpeedMultiplier()));
      this.timeInterval -= dt;
    } else {
      final double remainingDt = dt - this.timeInterval;
      alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * this.timeInterval * activeUpgrades.getSpeedMultiplier(), alien.getPosY() + alien.getSpeedY() * this.timeInterval * activeUpgrades.getSpeedMultiplier()));
      alien.setPhysic(new AlienNormalPhysic());
      alien.updatePosition(remainingDt, boundary);
    }
  }

  @Override
  public void hitPlatform(final Alien alien, final Platform p, final Boundary boundary, final GameWorld gameWorld, final ActiveUpgrades activeUpgrades) {

  }

  @Override
  public void hitEnemy(final Alien alien, final Enemy e, final GameWorld gameWorld, final ActiveUpgrades activeUpgrades) {

  }

  @Override
  public void hitGadget(final Alien alien, final Gadget g, final GameWorld gameWorld) {

  }

  @Override
  public void hitCoin(final Coin coin, final ActiveUpgrades activeUpgrades, final GameWorld gameWorld) {
    coin.collectCoin(gameWorld, activeUpgrades.getCoinMultiplier());
  }
}
