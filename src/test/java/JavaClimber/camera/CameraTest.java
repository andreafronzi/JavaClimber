package JavaClimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.impl.CameraImpl;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.PlatformImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldConstructor.api.WorldConstructor;
import it.unibo.model.worldConstructor.impl.Difficult;
import it.unibo.model.worldConstructor.impl.PlatformPoolEasy;
import it.unibo.model.worldConstructor.impl.WorldConstructorImpl;

/**
 * Tests for {@link CameraImpl}
 */
public class CameraTest {

    private static final double VIEW_WIDTH = 800.0;
    private static final double VIEW_HEIGHT = 600.0;
    private static final double PLAT_W = 100.0;
    private static final double PLAT_H = 20.0;

    private World world;
    private WorldConstructor wc;
    private CameraImpl camera;

    /**
     * Set up all to simulate the camera behaviour. 
     */
    @BeforeEach
    void setUp() {
        AlienImpl alien = new AlienImpl(new Vector2dImpl(0, 0), new Vector2dImpl(10, 10), 50, 50);
        RealWorld realWorld = new RealWorld(alien);
        UpperWorld upperWorld = new UpperWorld();
        world = new World(upperWorld, realWorld);

        PlatformPoolEasy pool = new PlatformPoolEasy();
        
        Difficult difficult = new Difficult(
            100.0, 150.0, 50.0, 100.0, 0.5, 0.1, 5, 0.1, 2, 0.1, 2, 0.1, 2, pool
        );
        this.wc = new WorldConstructorImpl(difficult);

        this.camera = new CameraImpl(VIEW_WIDTH, VIEW_HEIGHT, world, wc);
    }
    /**
     * Tests that the camera is initialized with the correct dimensions.
     */
    @Test
    void testCorrectInitialization() {
        assertEquals(VIEW_WIDTH, camera.getViewportWidth());
        assertEquals(VIEW_HEIGHT, camera.getViewportHeight());
    }

    /**
     * Verifies that the game objects(in this case platform) move downwards when the camera moves up.
     */
    @Test
    void testMovement() {
        PlatformImpl platform = new PlatformImpl(new Vector2dImpl(100, 100), PLAT_W, PLAT_H, Optional.empty(), Optional.empty());
        world.getRealWorld().addPlatform(platform);
        camera.update(50.0);
        assertEquals(150.0, platform.getPosY());
    }

    /**
     * Verifies the promotion logic from the UpperWorld to RealWorld when a object enter in a specific limit.
     */
    @Test
    void testTransferFromUpperToReal() {
        PlatformImpl platform = new PlatformImpl(new Vector2dImpl(200, -101), PLAT_W, PLAT_H, Optional.empty(), Optional.empty());
        world.getUpperWorld().addPlatform(platform);
        camera.update(0.5);
        assertFalse(world.getUpperWorld().getPlatforms().isEmpty());
        assertTrue(world.getRealWorld().getPlatforms().isEmpty());

        camera.update(10.0);
        assertTrue(world.getUpperWorld().getPlatforms().isEmpty());
        assertFalse(world.getRealWorld().getPlatforms().isEmpty());
        assertEquals(platform, world.getRealWorld().getPlatforms().get(0));
    }

    /**
     * Verifies that objects that fall below the screen are removed from RealWorld.
     */
    @Test
    void testCleanRealWorld() {
        PlatformImpl platform = new PlatformImpl(new Vector2dImpl(100, 590), PLAT_W, PLAT_H, Optional.empty(), Optional.empty());
        world.getRealWorld().addPlatform(platform);
        camera.update(20.0);
        assertTrue(world.getRealWorld().getPlatforms().isEmpty());
    }

    @Test
    void testCheckAndGenerateUpperWorld() {
        camera.update(290.0);
        assertTrue(world.getUpperWorld().getPlatforms().isEmpty());
        camera.update(20.0);
        assertFalse(world.getUpperWorld().getPlatforms().isEmpty(), 
            "UpperWorld should contain generated platforms after generation trigger");
    }

}
