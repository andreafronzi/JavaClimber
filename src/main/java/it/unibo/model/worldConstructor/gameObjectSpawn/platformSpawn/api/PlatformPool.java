package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;

public interface PlatformPool {
    
    /**
     * Gets the pool of platforms, which is a list of pairs containing a probability and a platform creation function.
     * 
     * @return the list of pairs representing the platform pool
     */
    public List<Pair<Double, BiConsumer<Double, Vector2d>>> getPlatformPool();
    
    double getWidth();

    double getHeight();
}
