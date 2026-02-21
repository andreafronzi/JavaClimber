package JavaClimber.phisics.platformPhysic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.gameObj.impl.PlatformImpl;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformPhysic.impl.OnTouchDestroyBehavior;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.impl.RealWorld;

/**
 * Test class for {@link OnTouchDestroyBehavior}.
 */
public class OnTouchDestroyBehaviorTest {

    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double INITIAL_Y = 40;
    private static final double INITIAL_X = 10;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 10;

    private static final double LEFT_BOUNDARY = 0;
    private static final double RIGHT_BOUNDARY = 100;

    private static final double PLATFORM1_X = 50;
    private static final double PLATFORM1_Y = 100;
    private static final double PLATFORM2_X = 50;
    private static final double PLATFORM2_Y = 50;

    /**
     * The {@link Alien}.
     */
    private Alien alien;

    /**
     * The {@link RealWorld} in which the test will be executed.
     */
    private RealWorld world;
    
    /**
     * The {@link Platform} that will be removed when touched by the alien.
     */
    private Platform platformToRemove;

    /**
     * Set up the test environment by initializing the alien, the world, and the platforms before each test case.
     */
    @BeforeEach
    public void setUp() {
        final ActiveUpgrades upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl());
        this.alien = new AlienImpl(new Vector2dImpl(INITIAL_X, INITIAL_Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, upgrades);
        this.world = new RealWorld(this.alien); 
        this.platformToRemove = new PlatformImpl(new Vector2dImpl(PLATFORM2_X, PLATFORM2_Y), PLATFORM_WIDTH, PLATFORM_HEIGHT, Optional.empty(), Optional.of(new OnTouchDestroyBehavior()));

        this.world.addPlatform(new PlatformImpl(new Vector2dImpl(PLATFORM1_X, PLATFORM1_Y), PLATFORM_WIDTH, PLATFORM_HEIGHT, Optional.empty(), Optional.empty()));
        this.world.addPlatform(this.platformToRemove);
    }
    
    /**
     * Verify that when the alien touches the {@link Platform} with the {@link OnTouchDestroyBehavior}, the platform is removed from the world.
     */
    @Test
    public void verifyPlatformDestruction() {
        final ActiveUpgrades upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl());
        final AlienPhysic physic = new AlienNormalPhysic();
        final Boundary boundary = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
        
        this.alien.setPhysic(physic);

        assertEquals(this.platformToRemove.getPosY(), alien.getPosY() + HEIGHT);

        this.platformToRemove.onHitBy(this.alien, physic, boundary, this.world, upgrades);
        assertFalse(this.world.getPlatforms().contains(this.platformToRemove));
    } 
}
