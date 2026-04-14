package javaclimber.worldConstructor.gameObjectSpawn.platformSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformPool;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolEasy;

/**
 * Test for the PlatformPool classes.
 */
public class PlatformPoolTest {

    private static final double X_MIN = 0;
    private static final double X_MAX = 100;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 100;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    /**
     * The PlatformPool instance to be tested.
     */
    private PlatformPool platformPool;

    /**
     * The world instance to be used in the tests.
     */
    private BaseWorld world;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    public void setUp() {
        this.world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        final var spawnPoolCreator = new SpawnPoolCreatorImpl(this.world);
        spawnPoolCreator.setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl()));
        this.platformPool = new PlatformPoolEasy(spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    /**
     * Test for getting the platform pool.
     */
    @Test
    public void testGetPlatformPool() {
        final var pool = this.platformPool.getPlatformPool();
        assertEquals(false, pool.isEmpty());
    }

    /**
     * Test for getting the platform width.
     */
    @Test
    public void testGetWidth() {
        assertEquals(PLATFORM_WIDTH, this.platformPool.getWidth());
    }

    /**
     * Test for getting the platform height.
     */
    @Test
    public void testGetHeight() {
        assertEquals(PLATFORM_HEIGHT, this.platformPool.getHeight());
    }
}
