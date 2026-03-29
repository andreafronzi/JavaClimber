package it.unibo.model.gameobj.api;

import it.unibo.model.world.api.GameWorld;

/**
 * <p>Represents a Coin entity in a two-dimensional game environment which can be collected by the {@link Alien}.</p>
 */
public interface Coin extends StaticEntity {

  /**
   * <p>Collects the coin within the game environment.
   * This action involves removing the coin from the game world and updating the number of collected coins</p>
   *
   * @param gameWorld the game world in which the coin stands, and from is removed when collected
   * @param multiplier the multiplier to apply to the coin's value
   */
  void collectCoin(GameWorld gameWorld, int multiplier);
}
