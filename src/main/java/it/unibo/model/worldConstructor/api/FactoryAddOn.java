package it.unibo.model.worldConstructor.api;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;

public interface FactoryAddOn {
    
    public Coin createCoin();

    public Enemy createEnemy();

    public Gadget createElycap();

}
