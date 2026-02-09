package it.unibo.model.world.api;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Abstract implementation of {@link BaseWorld}.
 * Provides storage and management for game entities using Lists.
 */
public abstract class AbstractWorldContainer implements BaseWorld {

    protected final List<Platform> platforms;
    protected final List<Coin> moneys;
    protected final List<Enemy> monsters;
    protected final List<Gadget> gadgets;

    /**
     * Constructs a new AbstractWorldContainer with empty entity lists.
     */
    public AbstractWorldContainer() {
        this.platforms = new LinkedList<>();
        this.moneys = new LinkedList<>();
        this.gadgets = new LinkedList<>();
        this.monsters = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addGadget(final Gadget gadget) {
        return this.gadgets.add(gadget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addMoney(final Coin money) {
        return this.moneys.add(money);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addMonster(final Enemy monster) {
        return this.monsters.add(monster);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addPlatform(final Platform platform) {
        return this.platforms.add(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Gadget> getGadgets() {
        return this.gadgets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Coin> getMoneys() {
        return this.moneys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Enemy> getMonsters() {
        return this.monsters;
    }

        @Override
    public List<Platform> getPlatforms() {
        return this.platforms;
    }

}
