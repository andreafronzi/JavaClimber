package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractGameWorld;
import it.unibo.model.world.api.AbstractQueueWorld;

public class World {
    
    private final AbstractQueueWorld upperWorld;
    private final AbstractGameWorld realWorld;

    public World(final AbstractQueueWorld upperWorld, final AbstractGameWorld realWorld) {
        this.upperWorld = upperWorld;
        this.realWorld = realWorld;
    }
    
    public AbstractQueueWorld getUpperWorld() {
        return this.upperWorld;
    }

    public AbstractGameWorld getRealWorld() {
        return this.realWorld;
    }
}