package it.unibo.model.world.impl;

import it.unibo.model.world.api.AbstractGameWorld;
import it.unibo.model.world.api.BoundWorld;
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
     * @param boundWorld the boundary world
     */
    public RealWorld(final Alien alien, final BoundWorld boundWorld) {
        super(boundWorld);
        this.alien = alien;
    }

    /**
     * {@inheritDoc}
     */
    public Alien getAlien() {
        return this.alien;
    }

}
