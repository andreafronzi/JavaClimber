package it.unibo.model.world.api;

import java.util.Optional;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Interface for a world that acts as a queue (FIFO).
 * Allows removing the first element of each entity type.
 */
public interface QueueWorld extends BaseWorld {

    Optional<Platform> removeFirstPlatform();

    Optional<Enemy> removeFirstMonster();

    Optional<Gadget> removeFirstGadget();

    Optional<Coin> removeFirstMoney();
}
