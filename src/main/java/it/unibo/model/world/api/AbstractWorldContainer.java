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

    private final List<Platform> platforms;
    private final List<Coin> moneys;
    private final List<Enemy> monsters;
    private final List<Gadget> gadgets;

    public AbstractWorldContainer() {
        this.platforms = new LinkedList<>();
        this.moneys = new LinkedList<>();
        this.gadgets = new LinkedList<>();
        this.monsters = new LinkedList<>();
    }

    @Override
    public boolean addGadget(final Gadget gadget) {
        return this.gadgets.add(gadget);
    }

    @Override
    public boolean addMoney(final Coin money) {
        return this.moneys.add(money);
    }

    @Override
    public boolean addMonster(final Enemy monster) {
        return this.monsters.add(monster);
    }

    @Override
    public boolean addPlatform(final Platform platform) {
        return this.platforms.add(platform);
    }

    @Override
    public List<Gadget> getGadgets() {
        return List.copyOf(this.gadgets);
    }

    @Override
    public List<Coin> getMoneys() {
        return List.copyOf(this.moneys);
    }

    @Override
    public List<Enemy> getMonsters() {
        return List.copyOf(this.monsters);
    }

    @Override
    public List<Platform> getPlatforms() {
        return List.copyOf(this.platforms);
    }
    
    // Protected accessors for subclasses to implement removal logic
    protected List<Platform> getPlatformsList() {
        return this.platforms;
    }

    protected List<Coin> getMoneysList() {
        return this.moneys;
    }

    protected List<Enemy> getMonstersList() {
        return this.monsters;
    }

    protected List<Gadget> getGadgetsList() {
        return this.gadgets;
    }
}
