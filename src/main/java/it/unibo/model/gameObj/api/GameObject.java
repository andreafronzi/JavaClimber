package it.unibo.model.gameObj.api;

import it.unibo.model.physics.api.Vector2d;

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
   * Set GameObject's position with the one provided.
   *
   * @param position the new position of the object
   */
  void setPosition(Vector2d position);
}
