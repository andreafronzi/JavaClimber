package it.unibo.model.physics.alienPhysic.api;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.shop.api.ActiveUpgrades;

/**
 * Represents the application of the chosen physic over the Alien.
 */
public interface AlienPhysic {

  /**
   * Handles the interaction between an {@link Alien} and a {@link Coin}.
   * 
   * @param coin the {@link Coin} involved in the interaction
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  void hitCoin(Coin coin, ActiveUpgrades activeUpgrades);

  /**
   * Handles the interaction between an {@link Alien} and an {@link Enemy}.
   * This method applies the appropriate physics effect when the Alien collides with an Enemy.
   *
   * @param alien the {@link Alien} involved in the collision
   * @param e the {@link Enemy} involved in the collision
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  void hitEnemy(Alien alien, Enemy e, ActiveUpgrades activeUpgrades);

  /**
   * Handles the interaction between an {@link Alien} and a {@link Gadget}.
   * This method applies the appropriate physics effect when the Alien interacts with the Gadget.
   *
   * @param alien the {@link Alien} involved in the interaction
   * @param g the {@link Gadget} being interacted with
   */
  void hitGadget(Alien alien, Gadget g);

  /**
   * Handles the collision between an {@link Alien} and a {@link Platform}.
   * This method applies the appropriate physics effect when the Alien interacts with the Platform.
   *
   * @param alien the {@link Alien} involved in the collision
   * @param p the {@link Platform} involved in the collision
   * @param boundary the boundary of the world
   */
  void hitPlatform(Alien alien, Platform p, Boundary boundary);

  /**
   * Apply chosen physical effect to alien's speed
   *
   * @param alien the alien to update
   * @param dt the time step
   * @param boundary the boundary
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  void update(Alien alien, double dt, Boundary boundary, ActiveUpgrades activeUpgrades);
}
