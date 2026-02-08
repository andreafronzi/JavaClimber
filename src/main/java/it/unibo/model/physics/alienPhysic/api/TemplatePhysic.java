package it.unibo.model.physics.alienPhysic.api;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * Represents a template for the alien physic. It implements the update method, which is common for all the alien physic, and it defines an abstract method moveAlien, which is implemented by the different alien physic.
 */
public abstract class TemplatePhysic implements AlienPhysic {

  @Override
  public void update(final Alien alien, final double dt, final Boundary boundary) {
    moveAlien(alien, dt, boundary);
    verifyBoundaryTouch(alien, boundary);
  }

  /**
   * Template Methods which update alien position and speed.
   *
   * @param alien the alien to update
   * @param dt the time elapsed since the last update
   */
  protected abstract void moveAlien(final Alien alien, final double dt, final Boundary boundary);

  /**
   * Verify if the alien go beyond the boundary. Apply pacman effect if it happens.
   *
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
