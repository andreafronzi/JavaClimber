package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

public interface AddOnPool {
    
    public List<Pair<Double,BiConsumer<Double,Vector2dImpl>>> getAddOnPool();

    public double getChanceAddOn();

}
