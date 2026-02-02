package it.unibo.model.world.impl;

public class World {
    
    private final UpperWorld upperWorld;
    private final RealWorld realWorld;

    public World(final UpperWorld upperWorld, final RealWorld realWorld) {
        this.upperWorld = upperWorld;
        this.realWorld = realWorld;
    }
    
    public UpperWorld getUpperWorld() {
        return this.upperWorld;
    }

    public RealWorld getRealWorld() {
        return this.realWorld;
    }
}