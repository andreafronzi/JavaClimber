package it.unibo.model.gameObj.api;

public interface Coin extends GameObject {

  /**
   * Collects the coin within the game environment.
   * This action involves removing the coin from the game world and updating the number of collected coins
   */
  void collect();
}
