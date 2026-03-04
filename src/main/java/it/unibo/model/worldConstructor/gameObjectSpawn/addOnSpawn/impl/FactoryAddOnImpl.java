package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.impl.CoinImpl;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.gameObj.impl.EnemyImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.FactoryAddOn;

public class FactoryAddOnImpl implements FactoryAddOn {

    private final double coinWidth;
    private final double coinHeight;
    private final double enemyWidth;
    private final double enemyHeight;
    private final double elycapWidth;
    private final double elycapHeight;
    private final ScoreManager scoreManager;

    public FactoryAddOnImpl(final ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        this.coinWidth = GameObjDimension.COIN_WIDTH;
        this.coinHeight = GameObjDimension.COIN_HEIGHT;
        this.enemyWidth = GameObjDimension.ENEMY_WIDTH;
        this.enemyHeight = GameObjDimension.ENEMY_HEIGHT;
        this.elycapWidth = GameObjDimension.ELYCAP_WIDTH;
        this.elycapHeight = GameObjDimension.ELYCAP_HEIGHT;

    }

    @Override
    public Coin createCoin(final Vector2d position) {
        return new CoinImpl(coinWidth, coinHeight, position, this.scoreManager);
    }

    @Override
    public Enemy createEnemy(final Vector2d position) {
        return new EnemyImpl(enemyWidth, enemyHeight, position);
    }

    @Override
    public Gadget createElycap(final Vector2d position) {
        return new EliCap(elycapWidth, elycapHeight, position);
    }

}