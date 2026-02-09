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
    private final Director director;

    /**
     * Constructs the easy platform pool and initializes the object lists.
     */
    public PlatformPoolEasy() {

        this.platformPool = new LinkedList<>();
        this.monsterPool = new LinkedList<>();
        this.gadgetPool = new LinkedList<>();
        this.moneyPool = new LinkedList<>();
        // this.trapPool = new LinkedList<>();
        this.director = new DirectorImpl(0, 0);

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
        // qui devo mettere le varie piattaforme con le loro chance
    }

    private void initializeMonsterPool() {
        // qui devo mettere i vari mostri con le loro chance
    }

    private void initializeGadgetPool() {
        // qui devo mettere i vari gadget con le loro chance
    }

    private void initializeMoneyPool() {
        // qui devo mettere i vari soldi con le loro chance
    }

    /*
     * private void initializeTrapPool() {
     * // qui devo mettere le varie trappole con le loro chance
     * }
     */
}
