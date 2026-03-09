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

    public AbstractQueueWorld(BoundWorld boundWorld) {
        super(boundWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstStaticPlatform() {
        if (!staticPlatforms.isEmpty()) {
            return Optional.of(staticPlatforms.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstMovingPlatform() {
        if (!movingPlatforms.isEmpty()) {
            return Optional.of(movingPlatforms.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstOnTouchPlatform() {
        if (!onTouchPlatforms.isEmpty()) {
            return Optional.of(onTouchPlatforms.removeFirst());
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
