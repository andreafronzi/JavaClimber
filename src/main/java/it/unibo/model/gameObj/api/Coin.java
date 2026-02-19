package it.unibo.model.gameObj.api;

/**
 * Represents a Coin entity in a two-dimensional game environment which can be collected by the {@link Alien}.
 */
public interface Coin extends StaticEntity {

  /**
   * Collects the coin within the game environment.
   * This action involves removing the coin from the game world and updating the number of collected coins
   *
   * @param multiplier the multiplier to apply to the coin's value
   */
  void collectCoin(int multiplier);
}
