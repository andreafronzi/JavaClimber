package it.unibo.model.physics.alienPhysic.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.api.TemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * Represents the alien physic when the alien collects the EliCap gadget. The alien will have a vertical speed for a certain time interval, then it will return to normal physic.
 */
public class AlienEliCapPhysic extends TemplatePhysic implements AlienPhysic {
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
   */
  @Override
  protected void moveAlien(final Alien alien, final double dt,  final Boundary boundary) {
    //se sei qui vuol dire che timeInterval > 0
    alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), this.verticalSpeed));
    if (this.timeInterval - dt >= 0) {
      alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt, alien.getPosY() + alien.getSpeedY() * dt));
      this.timeInterval -= dt;
    } else {
      final double remainingDt = dt - this.timeInterval;
      alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * this.timeInterval, alien.getPosY() + alien.getSpeedY() * this.timeInterval));
      alien.setPhysic(new AlienNormalPhysic());
      alien.updatePosition(remainingDt, boundary);
    }
  }

  @Override
  public void hitPlatform(final Alien alien, final Platform p, final Boundary boundary) {

  }

  @Override
  public void hitEnemy(final Alien alien, final Enemy e) {

  }

  @Override
  public void hitGadget(final Alien alien, final Gadget g) {

  }
}
