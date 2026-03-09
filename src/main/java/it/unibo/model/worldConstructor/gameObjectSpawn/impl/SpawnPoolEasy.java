package it.unibo.model.worldConstructor.gameObjectSpawn.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.FactoryAddOn;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.FactoryAddOnImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Director;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.DirectorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PairImpl;

/**
 * Implementation of PlatformPool for the "Easy" difficulty level.
 * Defines the probabilities and types of objects that spawn in easy mode.
 */
public class SpawnPoolEasy implements SpawnPool {

    private final List<Pair<Double,Function<Vector2d,Platform>>> staticPlatformPool;
    private final List<Pair<Double,Function<Vector2d,Platform>>> movingPlatformPool;
    private final List<Pair<Double,Function<Vector2d,Platform>>> onTouchPlatformPool;
    private final List<Pair<Double,Function<Vector2d,Enemy>>> monsterPool;
    private final List<Pair<Double,Function<Vector2d,Gadget>>> gadgetPool;
    private final List<Pair<Double,Function<Vector2d,Coin>>> moneyPool;
    // private final List<Pair<Double,Function<Vector2d,Trap>>> trapPool;
    private final Director director;
    private final FactoryAddOn factoryAddOn;

    /**
     * Constructs the easy platform pool and initializes the object lists.
     */
    public SpawnPoolEasy(final double width, final double height, final ScoreManager scoreManager) {
        this.staticPlatformPool = new LinkedList<>();
        this.movingPlatformPool = new LinkedList<>();
        this.onTouchPlatformPool = new LinkedList<>();
        this.monsterPool = new LinkedList<>();
        this.gadgetPool = new LinkedList<>();
        this.moneyPool = new LinkedList<>();
        // this.trapPool = new LinkedList<>();
        this.director = new DirectorImpl(width, height);
        this.factoryAddOn = new FactoryAddOnImpl(scoreManager);

        initializeStaticPlatformPool();
        initializeMovingPlatformPool();
        initializeOnTouchPlatformPool();
        initializeMonsterPool();
        initializeGadgetPool();
        initializeMoneyPool();
        // initializeTrapPool();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double,Function<Vector2d,Platform>>> getStaticPlatformPool() {
        return List.copyOf(this.staticPlatformPool);
    }

    @Override
    public List<Pair<Double,Function<Vector2d,Platform>>> getMovingPlatformPool() {
        return List.copyOf(this.movingPlatformPool);
    }

    @Override
    public List<Pair<Double,Function<Vector2d,Platform>>> getOnTouchPlatformPool() {
        return List.copyOf(this.onTouchPlatformPool);
    }   

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double,Function<Vector2d,Enemy>>> getMonsterPool() {
        return List.copyOf(this.monsterPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double,Function<Vector2d,Gadget>>> getGadgetPool() {
        return List.copyOf(this.gadgetPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double,Function<Vector2d,Coin>>> getMoneyPool() {
        return List.copyOf(this.moneyPool);
    }

    /*
     * @Override
     * public List<Pair<Trap>> getTrapPool() {
     * return List.copyOf(this.trapPool);
     * }
     */

    private void initializeStaticPlatformPool() {
        this.staticPlatformPool.add(new PairImpl<>(1.0, this.director::normalPlatform));
    }

    private void initializeMovingPlatformPool() {
        this.movingPlatformPool.add(new PairImpl<>(1.0, this.director::movingPlatform));
    }

    private void initializeOnTouchPlatformPool() {
        this.onTouchPlatformPool.add(new PairImpl<>(1.0, this.director::movingOnTouchPlatform));
    }

    private void initializeMonsterPool() {
        this.monsterPool.add(new PairImpl<>(1.0, this.factoryAddOn::createEnemy));
    }

    private void initializeGadgetPool() {
        this.gadgetPool.add(new PairImpl<>(1.0, this.factoryAddOn::createElycap));
    }

    private void initializeMoneyPool() {
        this.moneyPool.add(new PairImpl<>(1.0, this.factoryAddOn::createCoin));
    }

    /*
     * private void initializeTrapPool() {
     * // qui devo mettere le varie trappole con le loro chance
     * }
     */
}
