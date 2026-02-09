package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractGameWorld;
import it.unibo.model.world.api.AbstractQueueWorld;

/**
 * Class representing the complete game world.
 * It combines an "upper world" (queue of upcoming entities) and a "real world" (active entities).
 */
public class World {
    
    private final AbstractQueueWorld upperWorld;
    private final AbstractGameWorld realWorld;

    /**
     * Constructs a new World.
     * 
     * @param upperWorld the queue-based world for upcoming entities
     * @param realWorld the active game world
     */
    public World(final AbstractQueueWorld upperWorld, final AbstractGameWorld realWorld) {
        this.upperWorld = upperWorld;
        this.realWorld = realWorld;
    }
    
    /**
     * Returns the upper world.
     * 
     * @return the upper world
     */
    public AbstractQueueWorld getUpperWorld() {
        return this.upperWorld;
    }

    /**
     * Returns the real world.
     * 
     * @return the real world
     */
    public AbstractGameWorld getRealWorld() {
        return this.realWorld;
    }
}