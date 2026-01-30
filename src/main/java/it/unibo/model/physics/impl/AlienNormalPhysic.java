package it.unibo.model.physics.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.api.AlienPhysic;


/**
 * A concrete implementation of the {@link AlienPhysic} interface.
 * This class applies gravitational force on the vertical axis and
 * manages boundary behavior with a Pacman effect.
 */
public class AlienNormalPhysic implements AlienPhysic {

  private static final double GRAVITY = 50.0;

  /**
   * Constructs an instance of {@code AlienNormalPhysic}.
   */
  public AlienNormalPhysic() {}

  @Override
  public void update(final Alien alien, final double dt, final Boundary boundary) {
    moveAlien(alien, dt);
    verifyBoundaryTouch(alien, boundary);
  }

  /**
   * Update alien position and speed.
   * @param alien the alien to update
   * @param dt the time step
   */
  private void moveAlien(final Alien alien, double dt) {
    double speedY = alien.getSpeedY();

    final double newVelY = speedY + (GRAVITY * dt);
    alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), newVelY));
    alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt, alien.getPosY() + alien.getSpeedY() * dt));
  }

  /**
   * Verify if the alien go beyond the boundary. Apply pacman effect if it happens.
   * @param alien the alien which can go beyond the boundary
   * @param boundary the boundaries, which can be crossed by the alien
   */
  private void verifyBoundaryTouch(final Alien alien, final Boundary boundary) {
    if(alien.getPosX() + alien.getWidth() < boundary.x0()) {
      alien.setPosition(new Vector2dImpl(boundary.x1() - alien.getWidth(), alien.getPosY()));
    }
    if(alien.getPosX() > boundary.x1()) {
      alien.setPosition(new Vector2dImpl(boundary.x0(), alien.getPosY()));
    }
  }
}
