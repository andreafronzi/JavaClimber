package it.unibo.model.world.api;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

/**
 * Abstract implementation of {@link BaseWorld}.
 * Provides storage and management for game entities using Lists.
 */
public abstract class AbstractWorldContainer implements BaseWorld {

    protected final List<Platform> staticPlatforms;
    protected final List<Platform> movingPlatforms;
    protected final List<Platform> onTouchPlatforms;
    protected final List<Coin> moneys;
    protected final List<Enemy> monsters;
    protected final List<Gadget> gadgets;
    protected final BoundWorld boundWorld;

    /**
     * Constructs a new AbstractWorldContainer with empty entity lists.
     */
    public AbstractWorldContainer(final BoundWorld boundWorld) {
        this.staticPlatforms = new LinkedList<>();
        this.movingPlatforms = new LinkedList<>();
        this.onTouchPlatforms = new LinkedList<>();
        this.moneys = new LinkedList<>();
        this.gadgets = new LinkedList<>();
        this.monsters = new LinkedList<>();
        this.boundWorld = boundWorld;
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
    public boolean addStaticPlatform(final Platform platform) {
        return this.staticPlatforms.add(platform);
    }

    @Override
    public boolean addMovingPlatform(Platform platform) {
        return this.movingPlatforms.add(platform);
    }

    @Override
    public boolean addOnTouchPlatform(Platform platform) {
        return this.onTouchPlatforms.add(platform);
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
    public List<Platform> getStaticPlatforms() {
        return this.staticPlatforms;
    }

    @Override
    public List<Platform> getMovingPlatforms() {
        return this.movingPlatforms;
    }

    @Override
    public List<Platform> getOnTouchPlatforms() {
        return this.onTouchPlatforms;
    }

    public BoundWorld getBoundWorld() {
        return this.boundWorld;
    }

}
