package it.unibo.model.physics.alienPhysic.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.api.TemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;


/**
 * A concrete implementation of the {@link AlienPhysic} interface.
 * This class applies gravitational force on the vertical axis and
 * manages boundary behavior with a Pacman effect.
 */
public class AlienNormalPhysic extends TemplatePhysic implements AlienPhysic {

  private static final double GRAVITY = 50.0;

  /**
   * Constructs an instance of {@code AlienNormalPhysic}.
   */
  public AlienNormalPhysic() {}

  /**
   * Update alien position and speed.
   * @param alien the alien to update
   * @param dt the time step
   */
  @Override
  protected void moveAlien(final Alien alien, final double dt) {
    double speedY = alien.getSpeedY();

    final double newVelY = speedY + (GRAVITY * dt);
    alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), newVelY));
    alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt, alien.getPosY() + alien.getSpeedY() * dt));
  }
}
