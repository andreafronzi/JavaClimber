package it.unibo.model.worldConstructor.gameObjectSpawn.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.FactoryAddOn;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.FactoryAddOnImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Director;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.DirectorImpl;

public abstract class SpawnPoolBase implements SpawnPool {
 
    protected final List<Pair<Double,Function<Vector2d,Platform>>> staticPlatformPool;
    protected final List<Pair<Double,Function<Vector2d,Platform>>> movingPlatformPool;
    protected final List<Pair<Double,Function<Vector2d,Platform>>> onTouchPlatformPool;
    protected final List<Pair<Double,Function<Vector2d,Enemy>>> monsterPool;
    protected final List<Pair<Double,Function<Vector2d,Gadget>>> gadgetPool;
    protected final List<Pair<Double,Function<Vector2d,Coin>>> moneyPool;
    protected final Director director;
    protected final FactoryAddOn factoryAddOn;

    /**
     * Constructs the easy platform pool and initializes the object lists.
     */
    public SpawnPoolBase(final double width, final double height, final ScoreManager scoreManager) {
        this.staticPlatformPool = new LinkedList<>();
        this.movingPlatformPool = new LinkedList<>();
        this.onTouchPlatformPool = new LinkedList<>();
        this.monsterPool = new LinkedList<>();
        this.gadgetPool = new LinkedList<>();
        this.moneyPool = new LinkedList<>();
        this.director = new DirectorImpl(width, height);
        this.factoryAddOn = new FactoryAddOnImpl(scoreManager);
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

}
