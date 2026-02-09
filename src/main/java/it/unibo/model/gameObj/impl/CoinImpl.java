package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.ActiveUpgrades;

/**
 * Represents a Coin entity in a two-dimensional game environment which can be collected by the {@link Alien}.
 */
public class CoinImpl extends GameObj implements Coin {

    private static final int COIN_POINTS = 1;

  /**
   * The score manager which updates the {@link Coin} collected number.
   */
  private final ScoreManager scoreManager;

  /**
   * Constructs a new CoinImpl.
   *
   * @param height Coin's height
   * @param width Coin's width
   * @param position Coin's position
   * @param scoreManager the score manager to update the Coins collected number
   */
    public CoinImpl(final double height, final double width, final Vector2d position, final ScoreManager scoreManager) {
        super(height, width, position);
        this.scoreManager = scoreManager;
    }

    @Override
    public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final ActiveUpgrades activeUpgrades) {
        physic.hitCoin(this, activeUpgrades);
    }

    @Override
    public void collectCoin(final int multiplier) {
        this.scoreManager.addCoins(COIN_POINTS * multiplier);
    }

}
