package it.unibo.model.world.api;

import java.util.List;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Interface representing a container for game entities.
 * Allows adding entities and retrieving lists of entities.
 */
public interface BaseWorld {

    /**
     * Retrieves the list of platforms.
     * @return a List of {@link Platform} objects.
     */
    List<Platform> getPlatforms();

    /**
     * Retrieves the list of enemies.
     * @return a List of {@link Enemy} objects.
     */
    List<Enemy> getMonsters();

    /**
     * Retrieves the list of gadgets.
     * @return a List of {@link Gadget} objects.
     */
    List<Gadget> getGadgets();

    /**
     * Retrieves the list of coins.
     * @return a List of {@link Coin} objects.
     */
    List<Coin> getMoneys();

    /**
     * Adds a platform.
     * @param platform the platform to add.
     * @return true if added successfully.
     */
    boolean addPlatform(Platform platform);

    /**
     * Adds an enemy.
     * @param monster the enemy to add.
     * @return true if added successfully.
     */
    boolean addMonster(Enemy monster);

    /**
     * Adds a gadget.
     * @param gadget the gadget to add.
     * @return true if added successfully.
     */
    boolean addGadget(Gadget gadget);

    /**
     * Adds a coin.
     * @param money the coin to add.
     * @return true if added successfully.
     */
    boolean addMoney(Coin money);
}
