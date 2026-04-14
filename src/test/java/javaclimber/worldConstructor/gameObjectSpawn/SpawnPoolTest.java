package javaclimber.worldConstructor.gameObjectSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

/**
 * Test for the SpawnPool classes.
 */
public class SpawnPoolTest {

    private SpawnPool platformPool;

    private static final double PLATFORM_WIDTH = 30;
    private static final double PLATFORM_HEIGHT = 20;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    public void setUp() {
        this.platformPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
    }

    /**
     * Test for getting the static platform pool.
     */
    @Test
    public void getPlatformTest() {
        var platforms = this.platformPool.getStaticPlatformPool();
        assertEquals(1, platforms.size());
    }

    /**
     * Test for getting the moving platform pool.
     */
    @Test
    public void getMovingPlatformTest() {
        var movingPlatforms = this.platformPool.getMovingPlatformPool();
        assertEquals(1, movingPlatforms.size());
    }

    /**
     * Test for getting the on-touch platform pool.
     */
    @Test
    public void getOnTouchPlatformTest() {
        var onTouchPlatforms = this.platformPool.getOnTouchPlatformPool();
        assertEquals(1, onTouchPlatforms.size());
    }

    /**
     * Test for getting the monster pool.
     */
    @Test
    public void getMonsterTest() {
        var monsters = this.platformPool.getMonsterPool();
        assertEquals(1, monsters.size());
    }

    /**
     * Test for getting the gadget pool.
     */
    @Test
    public void getGadgetTest() {
        var gadgets = this.platformPool.getGadgetPool();
        assertEquals(1, gadgets.size());
    }

    /**
     * Test for getting the money pool.
     */
    @Test
    public void getMoneyTest() {
        var money = this.platformPool.getMoneyPool();
        assertEquals(1, money.size());
    }
}
