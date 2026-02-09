package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractGameWorld;

import it.unibo.model.gameObj.impl.AlienImpl;

/**
 * Concrete implementation of the active game world.
 * Contains the main character (Alien) and other active game objects.
 */
public class RealWorld extends AbstractGameWorld {

    private final AlienImpl alien;

    /**
     * Constructs a new RealWorld.
     * 
     * @param alien the player character
     */
    public RealWorld(final AlienImpl alien) {
        super();
        this.alien = alien;
    }

    /**
     * {@inheritDoc}
     */
    public AlienImpl getAlien() {
        return this.alien;
    }

}
