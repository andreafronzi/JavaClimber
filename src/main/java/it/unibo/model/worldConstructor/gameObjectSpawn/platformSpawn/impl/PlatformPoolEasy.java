package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPool;

public class PlatformPoolEasy implements PlatformPool {

    private final List<Pair<Double, BiConsumer<Double, Vector2d>>> platformPool;

    private SpawnPoolCreator spawnPoolCreator;
    private final double chancePlatform;
    private final double width;
    private final double height;

    public PlatformPoolEasy(final SpawnPoolCreator spawnPoolCreator, final double chancePlatform, final double width,
            final double height) {
        this.platformPool = new LinkedList<>();
        this.chancePlatform = chancePlatform;
        this.width = width;
        this.height = height;
    }

    @Override
    public List<Pair<Double, BiConsumer<Double, Vector2d>>> getPlatformPool() {
        return this.platformPool;
    }

    @Override
    public double getChancePlatform() {
        return this.chancePlatform;
    }

    private void createPlatform() {
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(0.2, this.spawnPoolCreator::createStaticPlatform));
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(0.4, this.spawnPoolCreator::createMovingPlatform));
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(1.0, this.spawnPoolCreator::createOnTouchPlatform));
    }

    @Override
    public void setSpawnPoolCreator(final SpawnPoolCreator spawnPoolCreator) {
        this.spawnPoolCreator = spawnPoolCreator;
        this.createPlatform();
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

}
