package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PairImpl;

public class AddOnPoolEasy implements AddOnPool {

    private final List<Pair<Double,BiConsumer<Double,Vector2dImpl>>> addOnPool;
    private SpawnPoolCreator spawnPoolCreator;
    private final double chanceAddOn;

    public AddOnPoolEasy() {
        this.addOnPool = new LinkedList<>();
        this.chanceAddOn = 0.3;
        createAddOn();
    }

    @Override
    public List<Pair<Double,BiConsumer<Double,Vector2dImpl>>> getAddOnPool() {
        return this.addOnPool;
    }
    
    @Override
    public double getChanceAddOn() {
        return this.chanceAddOn;
    }

    private void createAddOn() {
        this.addOnPool.add(new PairImpl<Double,BiConsumer<Double,Vector2dImpl>>(0.2, this.spawnPoolCreator::createGadget));
        this.addOnPool.add(new PairImpl<Double,BiConsumer<Double,Vector2dImpl>>(0.4, this.spawnPoolCreator::createMonster));
        this.addOnPool.add(new PairImpl<Double,BiConsumer<Double,Vector2dImpl>>(1.0, this.spawnPoolCreator::createMoney));
    }

    
}
