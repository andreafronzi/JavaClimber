package it.unibo.model.gameObj.api;

import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * <p>
 * Represents a Platform entity in a two-dimensional game environment.
 * </p>
 */
public interface Platform extends StaticEntity {

  /**
   * <p>
   * Platform's touch reaction.
   * </p>
   *
   * @param boundary  the boundary of the world
   * @param gameWorld the {@link GameWorld} which contains all gameObj
   */
  void onTouch(Boundary boundary, GameWorld gameWorld);

  /**
   * <p>
   * Update Platform's position. Computed position deals with elapsed time between
   * two updates.
   * </p>
   *
   * @param dt       the time interval between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(double dt, Boundary boundary);
}
