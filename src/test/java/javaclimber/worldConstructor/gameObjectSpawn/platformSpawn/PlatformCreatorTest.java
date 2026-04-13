package javaclimber.worldConstructor.gameObjectSpawn.platformSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPoolMedium;

/**
 * Test for the PlatformCreatorImpl class.
 */
public class PlatformCreatorTest {

    private PlatformCreator platformCreator;
    private BaseWorld world;
    private SpawnPoolCreatorImpl spawnPoolCreator;

    private static final double X_MIN = 0;
    private static final double X_MAX = 100;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 100;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double CHANCE = 0.5;

    private static final double POS_X = 50;
    private static final double POS_Y = 50;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    public void setUp() {
        this.world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        this.spawnPoolCreator = new SpawnPoolCreatorImpl(this.world);
        spawnPoolCreator.setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl()));
        this.platformCreator = new PlatformCreatorImpl(
                new PlatformPoolEasy(spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT));
    }

    /**
     * Test for creating a platform.
     */
    @Test
    public void testCreatePlatform() {
        this.platformCreator.createPlatform(CHANCE, new Vector2dImpl(POS_X, POS_Y));
        if (!this.world.getStaticPlatforms().isEmpty()) {
            assertEquals(this.world.getStaticPlatforms().getLast().getPosX(), POS_X);
            assertEquals(this.world.getStaticPlatforms().getLast().getPosY(), POS_Y);
        } else if (!this.world.getMovingPlatforms().isEmpty()) {
            assertEquals(this.world.getMovingPlatforms().getLast().getPosX(), POS_X);
            assertEquals(this.world.getMovingPlatforms().getLast().getPosY(), POS_Y);
        } else {
            assertEquals(this.world.getOnTouchPlatforms().getLast().getPosX(), POS_X);
            assertEquals(this.world.getOnTouchPlatforms().getLast().getPosY(), POS_Y);
        }
    }

    /**
     * Test for setting the platform pool.
     */
    @Test
    public void testSetPlatformPool() {
        PlatformPool oldPool = new PlatformPoolEasy(spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        PlatformPool newPool = new PlatformPoolMedium(this.spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.platformCreator.setPlatformPool(new PlatformPoolEasy(this.spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT));
        this.platformCreator.setPlatformPool(new PlatformPoolMedium(this.spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT));
        assertEquals(false, oldPool == newPool);
        //fare come su spawn pool creator creare un add on e vedere se ce
    }

}
