package it.unibo.model.world.api;

import java.util.Optional;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Abstract implementation of {@link QueueWorld}.
 * Provides basic logic for retrieving and removing the first element of each entity type.
 */
public abstract class AbstractQueueWorld extends AbstractWorldContainer implements QueueWorld {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstPlatform() {
        if (!platforms.isEmpty()) {
            return Optional.of(platforms.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Enemy> removeFirstMonster() {
        if (!monsters.isEmpty()) {
            return Optional.of(monsters.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Gadget> removeFirstGadget() {
        if (!gadgets.isEmpty()) {
            return Optional.of(gadgets.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Coin> removeFirstMoney() {
        if (!moneys.isEmpty()) {
            return Optional.of(moneys.removeFirst());
        }
        return Optional.empty();
    }
}
