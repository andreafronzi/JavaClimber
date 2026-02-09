package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Interface for creating game objects from a spawn pool.
 * Selects specific objects to create based on random chance and the configured pool.
 */
public interface PlatformPoolCreator {

    /**
     * Sets the pool of entities to be used for creation.
     * 
     * @param platformPool the pool containing entity factories and probabilities
     */
    public void setSpawnPool(PlatformPool platformPool);
    
    /**
     * Creates a platform based on the given chance.
     * 
     * @param chance the random value used to select the platform type
     * @param pos the position where the platform should be created
     * @return the created Platform, or null if no match found
     */
    public Platform createPlatform(double chance, Vector2d pos);

    /**
     * Creates a monster (enemy) based on the given chance.
     * 
     * @param chance the random value used to select the monster type
     * @param pos the position where the monster should be created
     * @return the created Enemy, or null if no match found
     */
    public Enemy createMonster(double chance, Vector2d pos);

    /**
     * Creates a gadget based on the given chance.
     * 
     * @param chance the random value used to select the gadget type
     * @param pos the position where the gadget should be created
     * @return the created Gadget, or null if no match found
     */
    public Gadget createGadget(double chance, Vector2d pos);

    /**
     * Creates a coin (money) based on the given chance.
     * 
     * @param chance the random value used to select the coin type
     * @param pos the position where the coin should be created
     * @return the created Coin, or null if no match found
     */
    public Coin createMoney(double chance, Vector2d pos);

    // public Trap createTrap(double chance, Vector2d pos);

}
