package it.unibo.model.gameObj.api;

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
   * @return x-coordinate of the speed of the Alien
   */
  double getSpeedX();

  /**
   *
   * @return y-coordinate of the speed of the Alien
   */
  double getSpeedY();

  /**
   * Set Alien's position with the one provided.
   *
   * @param position the new position of the Alien
   */
  void setPosition(Vector2d position);

  /**
   * Set Alien's speed with the one provided.
   *
   * @param speed
   */
  void setSpeed(Vector2d speed);
}
