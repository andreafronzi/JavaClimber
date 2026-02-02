package it.unibo.model.World.impl;

import it.unibo.model.World.api.AbstractBaseWorld;

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