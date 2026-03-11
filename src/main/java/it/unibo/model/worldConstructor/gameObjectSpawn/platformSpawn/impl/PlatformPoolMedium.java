package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl;

import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPoolBase;

public class PlatformPoolMedium extends PlatformPoolBase {

    public PlatformPoolMedium(SpawnPoolCreator spawnPoolCreator, double width, double height) {
        super(spawnPoolCreator, width, height);
        this.createPlatform();
    }

    private void createPlatform() {
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(0.2, this.spawnPoolCreator::createStaticPlatform));
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(0.4, this.spawnPoolCreator::createMovingPlatform));
        this.platformPool.add(
                new PairImpl<Double, BiConsumer<Double, Vector2d>>(1.0, this.spawnPoolCreator::createOnTouchPlatform));
    }

}
