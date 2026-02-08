package it.unibo.model.physics.Collision.api;

import java.util.List;

import it.unibo.model.gameObj.api.*;

/**
 * Handles collision detection between game objects in a two-dimensional game environment.
 * The CollisionManager is responsible for determining when an Alien entity interacts with
 * {@link Platform} entities or {@link Gadget} entities and managing the resulting behavior.
 */
public interface CollisionManager {

  /**
   * Detects collisions between the Alien and other game objects, including platforms, coins, enemies, and gadgets.
   *
   * @param platforms the list of platforms in the world
   * @param coins the list of coins in the world
   * @param enemies the list of enemies in the world
   * @param gadgets the list of gadgets in the world
   * @param alien the Alien entity in the world
   */
  void detectCollisions(List<Platform> platforms, List<Coin> coins, List<Enemy> enemies, List<Gadget> gadgets, Alien alien);
}
