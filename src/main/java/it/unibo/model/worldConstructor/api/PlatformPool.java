package it.unibo.model.worldConstructor.api;

import java.util.List;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Interface representing a pool of game entities available for spawning.
 * Defines different pools for platforms, enemies, gadgets, and money,
 * associating them with their spawn probabilities.
 */
public interface PlatformPool {

    /**
     * Retrieves the pool of available platforms.
     * 
     * @return a list of pairs containing platform factories and their spawn chances
     */
    public List<Pair<Platform>> getPlatformPool();

    /**
     * Retrieves the pool of available enemies (monsters).
     * 
     * @return a list of pairs containing enemy factories and their spawn chances
     */
    public List<Pair<Enemy>> getMonsterPool();

    /**
     * Retrieves the pool of available gadgets.
     * 
     * @return a list of pairs containing gadget factories and their spawn chances
     */
    public List<Pair<Gadget>> getGadgetPool();

    /**
     * Retrieves the pool of available coins (money).
     * 
     * @return a list of pairs containing coin factories and their spawn chances
     */
    public List<Pair<Coin>> getMoneyPool();

    // public List<Pair<Trap>> getTrapPool();

    /**
     * Retrieves the width of the platform for this pool.
     * 
     * @return the width of the platform pool
     */
    public double getWidth();

    /**
     * Retrieves the height of the platform for this pool.
     * 
     * @return the height of the platform pool
     */
    public double getHeight();

}