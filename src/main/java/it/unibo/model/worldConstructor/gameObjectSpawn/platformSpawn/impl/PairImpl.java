package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl;

import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

/**
 * Implementation of the Pair interface.
 * Associates a probability with a factory function for creating game objects.
 * 
 * @param <X> the type of game object
 * @param <Y> the type of probability value
 */
public class PairImpl<X,Y> implements Pair<X,Y> {

    private final X x;
    private final Y y;

    /**
     * Constructs a new PairImpl.
     * 
     * @param chance the spawn probability
     * @param function the function to create the object
     */
    public PairImpl(final X x, final Y y){
        this.x = x;
        this.y = y;
    }

    
    /**
     * {@inheritDoc}
    */
   @Override
   public X getX() {
       return this.x;   
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Y getY() {
        return this.y;
    }
}
