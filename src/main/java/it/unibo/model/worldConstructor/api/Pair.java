package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Interface representing a pair of a spawn probability and a game object factory.
 * Used in the PlatformPool to define which objects can spawn and how likely they are.
 * 
 * @param <X> the type of game object this pair creates
 */
public interface Pair<X> {
    
    /**
     * Gets the spawn probability associated with this object.
     * 
     * @return the probability value
     */
    public double getChance();

    /**
     * Creates a new instance of the game object at the specified position.
     * 
     * @param pos the position where the object should be created
     * @return the created game object
     */
    public X createGameObj(final Vector2d pos);
    
}
