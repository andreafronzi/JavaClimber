package it.unibo.model.gameObj.api;

import it.unibo.model.gameObj.impl.Boundary;

/**
 * Represents an Alien entity in a two-dimensional game environment.
 */
public interface Platform {

  /**
   *
   * @return { @code True } whether the platform contains a gadget or not.
   */
  boolean containsGadget();

  /**
   * Platform's touch reaction.
   */
  void onTouch();

  /**
   * Update Platform's position. Computed position deals with elapsed time between two updates.
   * @param dt  the time interval between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(double dt, Boundary boundary);
}
