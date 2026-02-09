package it.unibo.model.world.api;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.AlienImpl;

/**
 * Interface for a world where specific entities can be removed.
 * This interface extends {@link BaseWorld} and adds capabilities to remove
 * specific game objects from the world.
 */
public interface GameWorld extends BaseWorld {

    /**
     * Removes a specific platform from the world.
     *
     * @param platform the platform to be removed.
     * @return true if the platform was found and removed, false otherwise.
     */
    boolean removePlatform(Platform platform);

    /**
     * Removes a specific enemy (monster) from the world.
     *
     * @param monster the enemy to be removed.
     * @return true if the enemy was found and removed, false otherwise.
     */
    boolean removeMonster(Enemy monster);

    /**
     * Removes a specific gadget from the world.
     *
     * @param gadget the gadget to be removed.
     * @return true if the gadget was found and removed, false otherwise.
     */
    boolean removeGadget(Gadget gadget);

    /**
     * Removes a specific coin (money) from the world.
     *
     * @param money the coin to be removed.
     * @return true if the coin was found and removed, false otherwise.
     */
    boolean removeMoney(Coin money);

    /**
     * Returns the player character.
     * 
     * @return the alien instance
     */
    AlienImpl getAlien();

}
