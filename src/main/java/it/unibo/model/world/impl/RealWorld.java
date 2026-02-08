package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractBaseWorld;
import it.unibo.model.world.Player;

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