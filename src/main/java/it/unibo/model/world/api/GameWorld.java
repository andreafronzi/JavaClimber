package it.unibo.model.world.api;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Interface for a world where specific entities can be removed.
 */
public interface GameWorld extends BaseWorld {

    boolean removePlatform(Platform platform);

    boolean removeMonster(Enemy monster);

    boolean removeGadget(Gadget gadget);

    boolean removeMoney(Coin money);
}
