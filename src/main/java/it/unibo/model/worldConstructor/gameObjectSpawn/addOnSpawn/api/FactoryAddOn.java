package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.physics.api.Vector2d;

public interface FactoryAddOn {

    public Coin createCoin(Vector2d position);

    public Enemy createEnemy(Vector2d position);

    public Gadget createElycap(Vector2d position);

}
