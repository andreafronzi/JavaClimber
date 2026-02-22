package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.worldConstructor.api.PlatformPool;
import it.unibo.model.worldConstructor.impl.PlatformPoolEasy;

public class PlatformPoolTest {
    
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int MIN_PLATFORM_WIDTH = 20;
    private static final int MIN_PLATFORM_HEIGHT = 20;
    private static final int MAX_PLATFORM_WIDTH = 30;
    private static final int MAX_PLATFORM_HEIGHT = 30;
    private static final int MIN_PLATFORM_SPACING = 40;
    private static final int MAX_PLATFORM_SPACING = 40;

    private PlatformPool platformPool;

    @BeforeEach
    public void setUp() {
        // Initialize the platform pool with appropriate parameters for testing
        this.platformPool = new PlatformPoolEasy(   
            WINDOW_WIDTH,
            WINDOW_HEIGHT,
            MIN_PLATFORM_WIDTH,
            MIN_PLATFORM_HEIGHT,
            MAX_PLATFORM_WIDTH,
            MAX_PLATFORM_HEIGHT,
            MIN_PLATFORM_SPACING,
            MAX_PLATFORM_SPACING
        );
    }

    @Test
    public void getPlatformTest() {
        var platforms = this.platformPool.getPlatformPool();
        assertEquals(3, platforms.size());
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
