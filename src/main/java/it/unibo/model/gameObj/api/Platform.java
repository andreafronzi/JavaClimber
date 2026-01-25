package it.unibo.model.gameObj.api;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.api.Vector2d;


/**
 * Represents an Alien entity in a two-dimensional game environment.
 */
public interface Platform {

  /**
   * This method adds a Movement behaviour to the Platform.
   */
  void addMovementBehaviour();

  /**
   * This method adds the behavior the platform has when touched
   */
  void addOnTouchBehaviour();

  /**
   *
   * @return whether the platform contains a gadget or not.
   */
  boolean containsGadget();

  /**
   * Platform's touch reaction.
   */
  void onTouch();

  /**
   * Update Platform's position. Computed position deals with elapsed time between two updates.
   * @param position Platform's position to update
   * @param dt  the time interval between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(Vector2d position, double dt, Boundary boundary);
}
