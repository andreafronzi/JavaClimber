package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

public class AddOnCreatorImpl implements AddOnCreator {

    private List<Pair<Double, BiConsumer<Double, Platform>>> addOnPool;
    private final Random random;

    public AddOnCreatorImpl(final AddOnPool addOnPool) {
        this.addOnPool = addOnPool.getAddOnPool();
        this.random = new Random();
    }

    @Override
    public void selectAddOn(final Double chance, final Platform platform) {
        for (var addOn : addOnPool) {
            if (chance <= addOn.getX()) {
                addOn.getY().accept(random.nextDouble(1.0), platform);
                break;
            }
        }
    }

    @Override
    public void setAddOnPool(final AddOnPool addOnPool) {
        this.addOnPool = addOnPool.getAddOnPool();
    }
   
}
