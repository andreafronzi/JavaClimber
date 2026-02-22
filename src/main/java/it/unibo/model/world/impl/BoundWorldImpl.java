package it.unibo.model.world.impl;

import it.unibo.model.world.api.BoundWorld;

public class BoundWorldImpl implements BoundWorld {

    private final BoundY boundY;
    private final Boundary boundX;

    public BoundWorldImpl(final BoundY boundY, final Boundary boundX) {
        this.boundY = boundY;
        this.boundX = boundX;
    }

    @Override
    public BoundY getBoundY() {
        return this.boundY;
    }

    @Override
    public Boundary getBoundX() {
        return this.boundX;
    }

}
