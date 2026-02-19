package it.unibo.model.physics.Collision.api;

import it.unibo.model.gameObj.api.*;
import it.unibo.model.world.impl.RealWorld;

/**
 * Handles collision detection between game objects in a two-dimensional game environment.
 * The CollisionManager is responsible for determining when an Alien entity interacts with
 * {@link Platform} entities or {@link Gadget} entities and managing the resulting behavior.
 */
public interface CollisionManager {

  /**
   * Detects collisions between the Alien and other game objects, including platforms, coins, enemies, and gadgets.
   *
   * @param realWorld world's elements cointer
   */
  void detectCollisions(RealWorld realWorld);
}
