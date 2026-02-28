package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Interface representing a pair of a spawn probability and a game object factory.
 * Used in the PlatformPool to define which objects can spawn and how likely they are.
 * 
 * @param <X> the type of game object this pair creates
 * @param <Y> the type of probability value
 */
public interface Pair<X,Y> {
    
    /**
     * Gets the spawn probability associated with this object.
     * 
     * @return the probability value
     */
    public Y getY();

    /**
     * Creates a new instance of the game object at the specified position.
     * 
     * @param pos the position where the object should be created
     * @return the created game object
     */
    public X getX();
    
}
