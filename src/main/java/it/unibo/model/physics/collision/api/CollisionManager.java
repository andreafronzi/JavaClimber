package it.unibo.model.physics.collision.api;

import it.unibo.model.gameobj.api.*;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.world.api.GameWorld;

/**
 * <p>
 * Handles collision detection between game objects in a two-dimensional game
 * environment.
 * </p>
 * <p>
 * The CollisionManager is responsible for determining when an Alien entity
 * interacts with
 * {@link Platform} entities or {@link Gadget} entities and managing the
 * resulting behavior.
 * </p>
 */
@FunctionalInterface
public interface CollisionManager {

  /**
   * <p>
   * Detects collisions between the Alien and other game objects, including
   * platforms, coins, enemies, and gadgets.
   * </p>
   *
   * @param gameWorld    world's elements container#
   * @param launchedGame the {@link LaunchedGame} instance which manage game state
   *                     changes
   */
  void detectCollisions(GameWorld gameWorld, LaunchedGame launchedGame);
}
