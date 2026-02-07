package it.unibo.model.world.api;

import java.util.Optional;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Abstract implementation of {@link QueueWorld}.
 */
public abstract class AbstractQueueWorld extends AbstractWorldContainer implements QueueWorld {

    @Override
    public Optional<Platform> removeFirstPlatform() {
        if (!getPlatformsList().isEmpty()) {
            return Optional.of(getPlatformsList().remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Enemy> removeFirstMonster() {
        if (!getMonstersList().isEmpty()) {
            return Optional.of(getMonstersList().remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Gadget> removeFirstGadget() {
        if (!getGadgetsList().isEmpty()) {
            return Optional.of(getGadgetsList().remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Coin> removeFirstMoney() {
        if (!getMoneysList().isEmpty()) {
            return Optional.of(getMoneysList().remove(0));
        }
        return Optional.empty();
    }
}
