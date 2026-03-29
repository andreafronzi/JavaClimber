package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl;

import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPoolBase;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PairImpl;


public class AddOnPoolMedium extends AddOnPoolBase {

     public AddOnPoolMedium(final SpawnPoolCreator spawnPoolCreator, final double chanceAddOn) {
        super(spawnPoolCreator, chanceAddOn);
        this.createAddOn();
    }

    private void createAddOn() {
        this.addOnPool.add(new PairImpl<Double,BiConsumer<Double,Platform>>(0.2, this.spawnPoolCreator::createGadget));
        this.addOnPool.add(new PairImpl<Double,BiConsumer<Double,Platform>>(0.4, this.spawnPoolCreator::createMonster));
        this.addOnPool.add(new PairImpl<Double,BiConsumer<Double,Platform>>(1.0, this.spawnPoolCreator::createMoney));
    }
    
}
