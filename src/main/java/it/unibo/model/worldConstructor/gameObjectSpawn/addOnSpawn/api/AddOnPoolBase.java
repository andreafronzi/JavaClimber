package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

public abstract class AddOnPoolBase implements AddOnPool {

    protected final List<Pair<Double, BiConsumer<Double, Platform>>> addOnPool;
    protected final SpawnPoolCreator spawnPoolCreator;
    private final double chanceAddOn;

    public AddOnPoolBase(final SpawnPoolCreator spawnPoolCreator, final double chanceAddOn) {
        this.addOnPool = new LinkedList<>();
        this.chanceAddOn = chanceAddOn;
        this.spawnPoolCreator = spawnPoolCreator;
    }

    @Override
    public List<Pair<Double, BiConsumer<Double, Platform>>> getAddOnPool() {
        return this.addOnPool;
    }

    @Override
    public double getChanceAddOn() {
        return this.chanceAddOn;
    }

}
