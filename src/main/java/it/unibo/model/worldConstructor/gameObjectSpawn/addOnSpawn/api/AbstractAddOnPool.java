package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

/**
 * Base implementation of {@link AddOnPool}.
 */
public abstract class AbstractAddOnPool implements AddOnPool {

    /**
     * List of pairs where the first element is the probability of spawning the
     * add-on and the second element is a BiConsumer that takes the time and the
     * platform on which to spawn the add-on.
     */
    protected final List<Pair<Double, BiConsumer<Double, Platform>>> addOnPool;

    /**
     * The spawn pool creator used to create the spawn pool for the add-ons.
     */
    protected final SpawnPoolCreator spawnPoolCreator;

    /**
     * The probability of spawning an add-on.
     */
    private final double chanceAddOn;

    /**
     * Constructor for AddOnPoolBase.
     *
     * @param spawnPoolCreator the spawn pool creator used to create the spawn pool for the add-ons
     * @param chanceAddOn      the probability of spawning an add-on
     */
    public AbstractAddOnPool(final SpawnPoolCreator spawnPoolCreator, final double chanceAddOn) {
        this.addOnPool = new LinkedList<>();
        this.chanceAddOn = chanceAddOn;
        this.spawnPoolCreator = spawnPoolCreator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, BiConsumer<Double, Platform>>> getAddOnPool() {
        return this.addOnPool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getChanceAddOn() {
        return this.chanceAddOn;
    }

}
