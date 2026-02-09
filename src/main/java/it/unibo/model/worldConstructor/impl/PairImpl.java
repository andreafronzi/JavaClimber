package it.unibo.model.worldConstructor.impl;

import java.util.function.Function;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.api.Pair;

/**
 * Implementation of the Pair interface.
 * Associates a probability with a factory function for creating game objects.
 * 
 * @param <X> the type of game object
 */
public class PairImpl<X> implements Pair<X> {

    private final double chance;
    private final Function<Vector2d,X> function;

    /**
     * Constructs a new PairImpl.
     * 
     * @param chance the spawn probability
     * @param function the function to create the object
     */
    public PairImpl(final double chance, Function<Vector2d,X> function){
        this.chance = chance;
        this.function = function;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getChance() {
        return this.chance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X createGameObj(final Vector2d pos) {
        return this.function.apply(pos);    
    }
    
}
