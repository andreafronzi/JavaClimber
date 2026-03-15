package JavaClimber.worldConstructor.gameObjectSpawn.platformSpawn;

 import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPoolEasy;

public class PlatformPoolTest {

    private PlatformPool platformPool;
    private BaseWorld world;

    private static final double X_MIN = 0;
    private static final double X_MAX = 100;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 100;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    @BeforeEach
    public void setUp() {
        this.world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        var spawnPoolCreator = new SpawnPoolCreatorImpl(this.world);
        spawnPoolCreator.setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl()));
        this.platformPool = new PlatformPoolEasy(spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    @Test
    public void testGetPlatformPool() {
        var pool = this.platformPool.getPlatformPool();
        assertEquals(false, pool.isEmpty());
    }

    @Test
    public void testGetWidth() {
        assertEquals(PLATFORM_WIDTH, this.platformPool.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(PLATFORM_HEIGHT, this.platformPool.getHeight());
    }
}
