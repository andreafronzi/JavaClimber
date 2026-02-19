package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractGameWorld;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.AlienImpl;

/**
 * Concrete implementation of the active game world.
 * Contains the main character (Alien) and other active game objects.
 */
public class RealWorld extends AbstractGameWorld {

    private final Alien alien;

    /**
     * Constructs a new RealWorld.
     * 
     * @param alien the player character
     */
    public RealWorld(final Alien alien) {
        super();
        this.alien = alien;
    }

    /**
     * {@inheritDoc}
     */
    public Alien getAlien() {
        return this.alien;
    }

}
