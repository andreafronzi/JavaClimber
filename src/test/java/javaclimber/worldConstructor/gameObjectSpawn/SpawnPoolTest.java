package javaclimber.worldConstructor.gameObjectSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

public class SpawnPoolTest {

    private SpawnPool platformPool;

    private static final double PLATFORM_WIDTH = 30;
    private static final double PLATFORM_HEIGHT = 20;

    @BeforeEach
    public void setUp() {
        this.platformPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
    }

    @Test
    public void getPlatformTest() {
        var platforms = this.platformPool.getStaticPlatformPool();
        assertEquals(1, platforms.size());
    }

    @Test
    public void getMovingPlatformTest() {
        var movingPlatforms = this.platformPool.getMovingPlatformPool();
        assertEquals(1, movingPlatforms.size());
    }

    @Test
    public void getOnTouchPlatformTest() {
        var onTouchPlatforms = this.platformPool.getOnTouchPlatformPool();
        assertEquals(1, onTouchPlatforms.size());
    }

        @Test
    public void getMonsterTest() {
        var monsters = this.platformPool.getMonsterPool();
        assertEquals(1, monsters.size());
    }

        @Test
    public void getGadgetTest() {
        var gadgets = this.platformPool.getGadgetPool();
        assertEquals(1, gadgets.size());
    }

        @Test
    public void getMoneyTest() {
        var money = this.platformPool.getMoneyPool();
        assertEquals(1, money.size());
    }
}
