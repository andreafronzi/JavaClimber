package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;

/**
 * Base implementation of {@link PlatformPool}.
 */
public abstract class AbstractPlatformPool implements PlatformPool {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule is disabled because this class is a template and classes that extend
    // it need to access the fields to set the pool of platforms to spawn.

    /**
     * The platform pool is a list of pairs, where the first element is a double
     * representing the chance of spawning a platform, and the second element is a
     * BiConsumer.
     */
    protected final List<Pair<Double, BiConsumer<Double, Vector2d>>> platformPool;

    /**
     * The spawn pool creator is used to create the platform pool.
     */
    protected final SpawnPoolCreator spawnPoolCreator;

    // CHECKSTYLE: VisibilityModifier ON

    /**
     * The width of the platform pool.
     */
    private final double width;

    /**
     * The height of the platform pool.
     */
    private final double height;

    /**
     * Constructs a PlatformPoolBase with the given spawn pool creator, width, and
     * height.
     *
     * @param spawnPoolCreator the spawn pool creator to use for creating the
     *                         platform pool
     * @param width            the width of the platform pool
     * @param height           the height of the platform pool
     */
    public AbstractPlatformPool(final SpawnPoolCreator spawnPoolCreator, final double width,
            final double height) {
        this.platformPool = new LinkedList<>();
        this.spawnPoolCreator = spawnPoolCreator;
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, BiConsumer<Double, Vector2d>>> getPlatformPool() {
        return this.platformPool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

}
