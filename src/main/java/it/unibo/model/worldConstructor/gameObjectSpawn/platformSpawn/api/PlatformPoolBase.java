package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;

public abstract class PlatformPoolBase implements PlatformPool {

    protected final List<Pair<Double, BiConsumer<Double, Vector2d>>> platformPool;

    protected final SpawnPoolCreator spawnPoolCreator;
    private final double width;
    private final double height;

    public PlatformPoolBase(final SpawnPoolCreator spawnPoolCreator, final double width,
            final double height) {
        this.platformPool = new LinkedList<>();
        this.spawnPoolCreator = spawnPoolCreator;
        this.width = width;
        this.height = height;
    }

    @Override
    public List<Pair<Double, BiConsumer<Double, Vector2d>>> getPlatformPool() {
        return this.platformPool;
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
