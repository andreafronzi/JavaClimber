package it.unibo.model.gameobj.api;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * <p>
 * Represents a static entity in a two-dimensional game environment.
 * A static entity is a specialized game object that does not change its
 * position in response
 * to external forces or events but can still interact with other objects in the
 * game.
 * </p>
 */
public interface StaticEntity extends GameObject {

  /**
   * <p>
   * Handles the interaction when an {@link Alien} collides with this
   * {@link StaticEntity}.
   * </p>
   *
   * @param alien          the {@link Alien} that collides with this entity
   * @param physic         the {@link AlienPhysic} that influences the Alien's
   *                       behavior upon collision
   * @param boundary       the {@link Boundary} of the world
   * @param gameWorld      the {@link GameWorld} which contains all element
   * @param launchedGame   the {@link LaunchedGame} instance which manage game
   *                       state changes
   * @param activeUpgrades the {@link ActiveUpgrades} affecting the Alien
   */
  void onHitBy(Alien alien, AlienPhysic physic, Boundary boundary, GameWorld gameWorld, LaunchedGame launchedGame,
      ActiveUpgrades activeUpgrades);
}
