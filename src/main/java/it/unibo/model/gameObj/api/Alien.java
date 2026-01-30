package it.unibo.model.gameObj.api;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;

/**
 * Represents an Alien entity in a two-dimensional game environment.
 * This interface provides methods to manage the position and speed
 * of the Alien.
 */
public interface Alien {

  /**
   *
   * @return x-coordinate of the position of the Alien
   */
  double getPosX();

  /**
   *
   * @return y-coordinate of the position of the Alien
   */
  double getPosY();

  /**
   *
   * @return width of the Alien
   */
  double getWidth();

  /**
   *
   * @return height of the Alien
   */
  double getHeigth();

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

  /**
   * Set Alien's physic
   *
   * @param physic the new physic of the Alien
   */
  public void setPhysic(AlienPhysic physic);

  /**
   * Set Alien's position with the one provided.
   *
   * @param position the new position of the Alien
   */
  void setPosition(Vector2d position);

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
