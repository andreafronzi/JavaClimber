package it.unibo.model.worldConstructor.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.worldConstructor.api.Pair;
import it.unibo.model.worldConstructor.api.PlatformPool;
import it.unibo.model.worldConstructor.api.Director;
import it.unibo.model.worldConstructor.api.FactoryAddOn;

/**
 * Implementation of PlatformPool for the "Easy" difficulty level.
 * Defines the probabilities and types of objects that spawn in easy mode.
 */
public class PlatformPoolEasy implements PlatformPool {

    private final List<Pair<Platform>> platformPool;
    private final List<Pair<Enemy>> monsterPool;
    private final List<Pair<Gadget>> gadgetPool;
    private final List<Pair<Coin>> moneyPool;
    // private final List<Pair<Trap>> trapPool;
    private final double width;
    private final double height;
    private final Director director;
    private final FactoryAddOn factoryAddOn;

    /**
     * Constructs the easy platform pool and initializes the object lists.
     */
    public PlatformPoolEasy(final double width, final double height, final double coinWidth,
            final double coinHeight,
            final double enemyWidth,
            final double enemyHeight,
            final double elycapWidth,
            final double elycapHeight) {

        this.width = width;
        this.height = height;
        this.platformPool = new LinkedList<>();
        this.monsterPool = new LinkedList<>();
        this.gadgetPool = new LinkedList<>();
        this.moneyPool = new LinkedList<>();
        // this.trapPool = new LinkedList<>();
        this.director = new DirectorImpl(width, height);
        this.factoryAddOn = new FactoryAddOnImpl(coinWidth,
                coinHeight,
                enemyWidth,
                enemyHeight,
                elycapWidth,
                elycapHeight);

        initializePlatformPool();
        initializeMonsterPool();
        initializeGadgetPool();
        initializeMoneyPool();
        // initializeTrapPool();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Platform>> getPlatformPool() {
        return List.copyOf(this.platformPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Enemy>> getMonsterPool() {
        return List.copyOf(this.monsterPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Gadget>> getGadgetPool() {
        return List.copyOf(this.gadgetPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Coin>> getMoneyPool() {
        return List.copyOf(this.moneyPool);
    }

    /*
     * @Override
     * public List<Pair<Trap>> getTrapPool() {
     * return List.copyOf(this.trapPool);
     * }
     */

    private void initializePlatformPool() {
        this.platformPool.add(new PairImpl<>(0.5, this.director::normalPlatform));
        this.platformPool.add(new PairImpl<>(0.75, this.director::movingOnTouchPlatform));
        this.platformPool.add(new PairImpl<>(1, this.director::movingPlatform));
    }

    private void initializeMonsterPool() {
        this.monsterPool.add(new PairImpl<>(1, this.factoryAddOn::createEnemy));
    }

    private void initializeGadgetPool() {
        this.gadgetPool.add(new PairImpl<>(1, this.factoryAddOn::createElycap));
    }

    private void initializeMoneyPool() {
        this.moneyPool.add(new PairImpl<>(1, this.factoryAddOn::createCoin));
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    /*
     * private void initializeTrapPool() {
     * // qui devo mettere le varie trappole con le loro chance
     * }
     */
}
