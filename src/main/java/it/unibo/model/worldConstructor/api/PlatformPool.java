package it.unibo.model.worldConstructor.api;

import java.util.List;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

public interface PlatformPool {

    public List<Pair<Platform>> getPlatformPool();

    public List<Pair<Enemy>> getMonsterPool();

    public List<Pair<Gadget>> getGadgetPool();

    public List<Pair<Coin>> getMoneyPool();

    // public List<Pair<Trap>> getTrapPool();

}