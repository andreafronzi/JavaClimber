package it.unibo.model.gameObj.api;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.api.Vector2d;

/**
 * Represents a Platform entity in a two-dimensional game environment.
 */
public interface Platform {

  /**
   *
   * @return True whether the platform contains a gadget or not.
   */
  boolean containsGadget();

  /**
   *
   * @return x-coordinate of the position of the platform
   */
  double getPosX();

  /**
   *
   * @return y-coordinate of the position of the platform
   */
  double getPosY();

  /**
   *
   * @return height of the platform
   */
  double getHeight();

  /**
   *
   * @return width of the platform
   */
  double getWidth();

  /**
   * Platform's touch reaction.
   *
   * @param boundary the boundary of the world
   */
  void onTouch(final Boundary boundary);

  /**
   * Set the Position of the object with the one provided.
   *
   * @param position the new position of the object
   */
  public void setPosition(Vector2d position);

  /**
   * Update Platform's position. Computed position deals with elapsed time between two updates.
   *
   * @param dt  the time interval between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(double dt, Boundary boundary);
}
