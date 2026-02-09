package it.unibo.model.world.api;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Abstract implementation of {@link GameWorld}.
 * Provides basic removal logic for game entities.
 */
public abstract class AbstractGameWorld extends AbstractQueueWorld implements GameWorld {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removePlatform(final Platform platform) {
        return platforms.remove(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeMonster(final Enemy monster) {
        return monsters.remove(monster);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeGadget(final Gadget gadget) {
        return gadgets.remove(gadget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeMoney(final Coin money) {
        return moneys.remove(money);
    }
}
