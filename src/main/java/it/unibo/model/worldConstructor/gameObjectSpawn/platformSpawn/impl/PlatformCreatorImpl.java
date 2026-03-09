package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPool;

public class PlatformCreatorImpl implements PlatformCreator {

    private List<Pair<Double, BiConsumer<Double, Vector2d>>> platformPool;
    private final Random random;

    public PlatformCreatorImpl(final PlatformPool platformPool) {
        this.platformPool = platformPool.getPlatformPool();
        this.random = new Random();
    }


    @Override
    public void createPlatform(double chance, Vector2d pos) {
        for (var platform : platformPool) {
            if (chance <= platform.getX()) {
                platform.getY().accept(random.nextDouble(1.0), new Vector2dImpl(pos.getX(), pos.getY()));
                break;
            }
        }
    }


    @Override
    public void setPlatformPool(PlatformPool platformPool) {
        this.platformPool = platformPool.getPlatformPool();
    }
    
}
