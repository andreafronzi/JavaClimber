package it.unibo.model.physics.alienPhysic.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.api.TemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;

public class AlienEliCapPhysic extends TemplatePhysic implements AlienPhysic {

  /**
   * Represents the duration time of the gadget effect.
   */
  private double time_interval;

  /**
   * Represents the vertical speed of the gadget effect.
   */
  private final double verticalSpeed;

  public AlienEliCapPhysic(final double time_interval, final double verticalSpeed) {
      this.time_interval = time_interval;
      this.verticalSpeed = verticalSpeed;
  }

  /**
   * Update alien position and speed using the gadget effect.
   *
   * @param alien the alien to update
   * @param dt the time step
   */
  @Override
  protected void moveAlien(final Alien alien, final double dt) {
    if (this.time_interval > 0) {
      alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), this.verticalSpeed));
      alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt, alien.getPosY() + alien.getSpeedY() * dt));
    }
    this.time_interval -= dt;
    if (this.time_interval <= 0) {
      alien.setPhysic(new AlienNormalPhysic());
    }
  }
}
