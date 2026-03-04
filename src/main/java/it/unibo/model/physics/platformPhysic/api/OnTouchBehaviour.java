package it.unibo.model.physics.platformPhysic.api;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * <p>
 * Interface representing Platform's touch reaction.
 * </p>
 */
public interface OnTouchBehaviour {

  /**
   * <p>
   * Platform's touch reaction.
   * </p>
   *
   * @param platform  the {@link Platform} touched platform
   * @param boundary  the {@link Boundary} boundary of the world
   * @param realWorld the {@link GameWorld} which contains all gameObjects
   */
  void onTouch(Platform platform, Boundary boundary, GameWorld realWorld);
}
