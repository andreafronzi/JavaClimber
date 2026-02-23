package it.unibo.model.worldConstructor.impl;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.impl.CoinImpl;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.gameObj.impl.EnemyImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.api.FactoryAddOn;

public class FactoryAddOnImpl implements FactoryAddOn {

    private final double coinWidth;
    private final double coinHeight;
    private final double enemyWidth;
    private final double enemyHeight;
    private final double elycapWidth;
    private final double elycapHeight;

    public FactoryAddOnImpl() {
        this.coinWidth = GameObjDimension.COIN_WIDTH;
        this.coinHeight = GameObjDimension.COIN_HEIGHT;
        this.enemyWidth = GameObjDimension.ENEMY_WIDTH;
        this.enemyHeight = GameObjDimension.ENEMY_HEIGHT;
        this.elycapWidth = GameObjDimension.ELYCAP_WIDTH;
        this.elycapHeight = GameObjDimension.ELYCAP_HEIGHT;

    }

    @Override
    public Coin createCoin(final Vector2d position) {
        return new CoinImpl(coinWidth, coinHeight, position, null);
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