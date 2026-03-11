package JavaClimber.worldConstructor.gameObjectSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.impl.PlatformImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

public class SpawnPoolCreatorTest {

    private SpawnPoolCreatorImpl platformPoolCreator;
    private QueueWorld world;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 600;

    private static final double X_MIN = 0;
    private static final double X_MAX = 800;

    private static final double PLATFORM_WIDTH = 30;
    private static final double PLATFORM_HEIGHT = 30;

    private static final double POS_X = 100;
    private static final double POS_Y = 100;

    private final static double CHANCE = 0.5;

    private static final int EXPECTED_SIZE = 1;

    @BeforeEach
    public void setUp() {
        this.world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        this.platformPoolCreator = new SpawnPoolCreatorImpl(world);
        this.platformPoolCreator
                .setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl()));
    }

    @Test
    public void setSpawnPoolTest() {
        SpawnPool newPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
        this.platformPoolCreator.setSpawnPool(newPool);
        this.platformPoolCreator.createStaticPlatform(CHANCE, new Vector2dImpl(POS_X, POS_Y));
        assertEquals(EXPECTED_SIZE, world.getStaticPlatforms().size());
    }

    @Test
    public void createStaticPlatformTest() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        this.platformPoolCreator.createStaticPlatform(CHANCE, pos);
        assertEquals(EXPECTED_SIZE, world.getStaticPlatforms().size());
    }

    @Test
    public void createMovingPlatformTest() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        this.platformPoolCreator.createMovingPlatform(CHANCE, pos);
        assertEquals(EXPECTED_SIZE, world.getMovingPlatforms().size());
    }

    @Test
    public void createOnTouchPlatformTest() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        this.platformPoolCreator.createOnTouchPlatform(CHANCE, pos);
        assertEquals(EXPECTED_SIZE, world.getOnTouchPlatforms().size());
    }

    @Test
    public void createMonsterTest() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        var platform = new PlatformImpl(pos, PLATFORM_WIDTH, PLATFORM_HEIGHT, null, null);
        this.platformPoolCreator.createMonster(CHANCE, platform);
        assertEquals(EXPECTED_SIZE, world.getMonsters().size());
    }

    @Test
    public void createGadgetTest() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        var platform = new PlatformImpl(pos, PLATFORM_WIDTH, PLATFORM_HEIGHT, null, null);
        this.platformPoolCreator.createGadget(CHANCE, platform);
        assertEquals(EXPECTED_SIZE, world.getGadgets().size());
    }

    @Test
    public void createMoneyTest() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        var platform = new PlatformImpl(pos, PLATFORM_WIDTH, PLATFORM_HEIGHT, null, null);
        this.platformPoolCreator.createMoney(CHANCE, platform);
        assertEquals(EXPECTED_SIZE, world.getMoneys().size());
    }

}
