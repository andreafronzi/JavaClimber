package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

public interface PlatformPoolCreator {

    public void setSpawnPool(PlatformPool platformPool);
    
    public Platform createPlatform(double chance, Vector2d pos);

    public Enemy createMonster(double chance, Vector2d pos);

    public Gadget createGadget(double chance, Vector2d pos);

    public Coin createMoney(double chance, Vector2d pos);

    // public Trap createTrap(double chance, Vector2d pos);

}
