package it.unibo.model.gameobj.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * <p>
 * Represents a game object in a two-dimensional {@link GameWorld}.
 * </p>
 */
public interface GameObject {

  /**
   *
   * @return GameObject's height
   */
  double getHeight();

  /**
   *
   * @return x-coordinate of the position of the object
   */
  double getPosX();

  /**
   *
   * @return y-coordinate of the position of the object
   */
  double getPosY();

  /**
   *
   * @return GameObject's width
   */
  double getWidth();

  /**
   * <p>
   * Set GameObject's position with the one provided.
   * </p>
   *
   * @param position the new position of the object
   */
  void setPosition(Vector2d position);
}
