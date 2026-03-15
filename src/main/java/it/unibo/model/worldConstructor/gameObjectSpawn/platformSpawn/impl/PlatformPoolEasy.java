package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPoolBase;

public class PlatformPoolEasy extends PlatformPoolBase {

    public PlatformPoolEasy(SpawnPoolCreator spawnPoolCreator, double width, double height) {
        super(spawnPoolCreator, width, height);
        this.createPlatform();
    }

    @Override
    public List<Pair<Double, BiConsumer<Double, Vector2d>>> getPlatformPool() {
        return this.platformPool;
    }

    private void createPlatform() {
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(1.0, this.spawnPoolCreator::createStaticPlatform));
    }

}
