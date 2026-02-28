package JavaClimber.worldGenerator.gameObjectSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

public class PlatformPoolCreatorTest {

    private SpawnPoolCreatorImpl platformPoolCreator;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int MIN_PLATFORM_WIDTH = 20;
    private static final int MIN_PLATFORM_HEIGHT = 20;
    private static final int MAX_PLATFORM_WIDTH = 30;
    private static final int MAX_PLATFORM_HEIGHT = 30;
    private static final int MIN_PLATFORM_SPACING = 40;
    private static final int MAX_PLATFORM_SPACING = 40;
    private final static double CHANCE = 0.5;

    @BeforeEach
    public void setUp() {
    }

    public Vector2dImpl pos() {
        return new Vector2dImpl(100, 100);
    }

    @Test
    public void setSpawnPoolTest() {
    }

    @Test
    public void createPlatformTest() {
    }

    @Test
    public void createMonsterTest() {
    }

    @Test
    public void createGadgetTest() {
    }

    @Test
    public void createMoneyTest() {
    }

}
