package it.unibo.model.physics.platformPhysic.api;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.impl.RealWorld;

/**
 * Interface representing Platform's touch reaction.
 */
public interface OnTouchBehaviour {

  /**
   * Platform's touch reaction.
   *
   * @param platform the {@link Platform} touched platform
   * @param boundary the {@link Boundary} boundary of the world
   * @param realWorld the {@link RealWorld} which contains all gameObj
   */
  void onTouch(Platform platform, Boundary boundary, RealWorld realWorld);
}
