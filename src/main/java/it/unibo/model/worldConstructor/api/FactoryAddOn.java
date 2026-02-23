package it.unibo.model.worldConstructor.api;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.physics.api.Vector2d;

public interface FactoryAddOn {

    public Coin createCoin(Vector2d position);

    public Enemy createEnemy(Vector2d position);

    public Gadget createElycap(Vector2d position);

}
