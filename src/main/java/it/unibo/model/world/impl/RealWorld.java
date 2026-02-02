package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractBaseWorld;

public class RealWorld extends AbstractBaseWorld {

    private final Player alien;

    public RealWorld(final Player alien) {
        super();
        this.alien = alien;
    }

    public Player getAlien() {
        return this.alien;
    }

}