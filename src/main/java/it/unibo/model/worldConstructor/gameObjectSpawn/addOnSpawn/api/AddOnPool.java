package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

public interface AddOnPool {
    
    public List<Pair<Double,BiConsumer<Double,Platform>>> getAddOnPool();

    public double getChanceAddOn();

    public void setSpawnPoolCreator(SpawnPoolCreator spawnPoolCreator);

}
