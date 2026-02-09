package it.unibo.model.gameObj.api;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;

/**
 * Represents an Alien entity in a two-dimensional game environment.
 * This interface provides methods to manage the Alien's physic movement.
 */
public interface Alien extends GameObject{

  /**
   *
   * @return x-coordinate of the speed of the Alien
   */
  double getSpeedX();

  /**
   *
   * @return y-coordinate of the speed of the Alien
   */
  double getSpeedY();

  void notifyAltitude();

  /**
   * Notifies the Alien of a collision with a {@link StaticEntity}.
   * This method is called when the Alien comes into contact with a StaticEntity
   *
   * @param gObj the {@link StaticEntity} that the Alien has collided with
   * @param boundary the boundary of the world
   */
  void notifyCollision(StaticEntity gObj, Boundary boundary);

  /**
   * Set Alien's physic
   *
   * @param physic the new physic of the Alien
   */
  public void setPhysic(AlienPhysic physic);

  /**
   * Set Alien's speed with the one provided.
   *
   * @param speed the new speed of the Alien
   */
  void setSpeed(Vector2d speed);

  /**
   * update Alien's position.
   *
   * @param dt elapsed time between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(double dt, Boundary boundary);
}
