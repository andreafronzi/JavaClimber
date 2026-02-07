package it.unibo.model.world.api;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Abstract implementation of {@link GameWorld}.
 */
public abstract class AbstractGameWorld extends AbstractWorldContainer implements GameWorld {

    @Override
    public boolean removePlatform(final Platform platform) {
        return getPlatformsList().remove(platform);
    }

    @Override
    public boolean removeMonster(final Enemy monster) {
        return getMonstersList().remove(monster);
    }

    @Override
    public boolean removeGadget(final Gadget gadget) {
        return getGadgetsList().remove(gadget);
    }

    @Override
    public boolean removeMoney(final Coin money) {
        return getMoneysList().remove(money);
    }
}
