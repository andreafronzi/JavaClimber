package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.impl.PlatformPoolCreatorImpl;
import it.unibo.model.worldConstructor.impl.PlatformPoolEasy;

public class PlatformPoolCreatorTest {

    private PlatformPoolCreatorImpl platformPoolCreator;

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
        this.platformPoolCreator = new PlatformPoolCreatorImpl();
    }

    public Vector2dImpl pos() {
        return new Vector2dImpl(100, 100);
    }

    @Test
    public void setSpawnPoolTest() {
        this.platformPoolCreator.setSpawnPool(new PlatformPoolEasy(
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                MIN_PLATFORM_WIDTH,
                MIN_PLATFORM_HEIGHT,
                MAX_PLATFORM_WIDTH,
                MAX_PLATFORM_HEIGHT,
                MIN_PLATFORM_SPACING,
                MAX_PLATFORM_SPACING));
    }

    @Test
    public void createPlatformTest() {
        var pos = this.pos();
        var platform = this.platformPoolCreator.createPlatform(CHANCE, pos);
        assertEquals(pos.getX(), platform.getPosX());
        assertEquals(pos.getY(), platform.getPosY());
    }

    @Test
    public void createMonsterTest() {
        var pos = this.pos();
        var platform = this.platformPoolCreator.createMonster(CHANCE, pos);
        assertEquals(pos.getX(), platform.getPosX());
        assertEquals(pos.getY(), platform.getPosY());
    }

    @Test
    public void createGadgetTest() {
        var pos = this.pos();
        var platform = this.platformPoolCreator.createGadget(CHANCE, pos);
        assertEquals(pos.getX(), platform.getPosX());
        assertEquals(pos.getY(), platform.getPosY());
    }

    @Test
    public void createMoneyTest() {
        var pos = this.pos();
        var platform = this.platformPoolCreator.createMoney(CHANCE, pos);
        assertEquals(pos.getX(), platform.getPosX());
        assertEquals(pos.getY(), platform.getPosY());
    }

}
