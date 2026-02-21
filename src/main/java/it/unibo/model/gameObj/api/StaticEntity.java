package it.unibo.model.gameObj.api;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;

/**
 * Represents a static entity in a two-dimensional game environment.
 * A static entity is a specialized game object that does not change its position in response
 * to external forces or events but can still interact with other objects in the game.
 */
public interface StaticEntity extends GameObject {

  /**
   * Handles the interaction when an {@link Alien} collides with this {@link StaticEntity}.
   *
   * @param alien the {@link Alien} that collides with this entity
   * @param physic the {@link AlienPhysic} that influences the Alien's behavior upon collision
   * @param boundary the boundary of the world
   * @param gameWorld the {@link GameWorld} which contains all element
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  void onHitBy(Alien alien, AlienPhysic physic, Boundary boundary, GameWorld gameWorld, ActiveUpgrades activeUpgrades);
}
