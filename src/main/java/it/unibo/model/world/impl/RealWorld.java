package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractGameWorld;
import it.unibo.model.gameObj.impl.AlienImpl;

public class RealWorld extends AbstractGameWorld {

    private final AlienImpl alien;

    public RealWorld(final AlienImpl alien) {
        super();
        this.alien = alien;
    }

    public AlienImpl getAlien() {
        return this.alien;
    }

}
