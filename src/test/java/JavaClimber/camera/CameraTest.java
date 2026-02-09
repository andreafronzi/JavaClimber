package JavaClimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.camera.api.Camera;
import it.unibo.model.camera.impl.CameraImpl;

/**
 * Tests for {@link Camera}
 */
public class CameraTest {

    private static final double VIEW_WIDTH = 800.0;
    private static final double VIEW_HEIGHT = 600.0;
    private Camera camera = new CameraImpl(VIEW_WIDTH, VIEW_HEIGHT);

    /**
     * Tests that the camera is initialized with the correct dimensions and flag.
     */
    @Test
    void testCorrectInitialization() {
        assertEquals(0, camera.getX());
        assertEquals(0, camera.getY());
        assertEquals(VIEW_WIDTH, camera.getViewportWidth());
        assertEquals(VIEW_HEIGHT, camera.getViewportHeight());
        assertTrue(camera.shouldGenerateWorld());
    }

    /**
     * Verifies that the camera follows the player and calculate correctly the new camera Y.
     */
    @Test
    void testFollowPlayer() {
        double targetY = -1000.0;
        camera.update(targetY);
        /*  getY = target - (HEIGHT * 0.5)
        *   -1000 - (600 * 0.5) = -1300
        */
        assertEquals(-1300.0, camera.getY());  
    }

    /**
     * Verifies that, if after reached an highestY and the alien falls, the camera not follow a lower camera Y.
     */
    @Test
    void testNotFollowIfFalls() {
        camera.update(-1000.0);
        double highestY = camera.getY();
        camera.update(0.0);
        assertEquals(highestY, camera.getY());
    }

    /**
     * Verifies the correct change of the flag for generation upper world. 
     */
    @Test
    void testGeneration() {
        camera.resetGeneration();
        assertFalse(camera.shouldGenerateWorld());

        camera.update(200.0);
        assertFalse(camera.shouldGenerateWorld());

        camera.update(-100.0);
        assertTrue(camera.shouldGenerateWorld());
        camera.resetGeneration();
        assertFalse(camera.shouldGenerateWorld());
    }

    /**
     * 
     */
    @Test
    void testLowerLimit() {
        camera.update(-1000.0);
        /*  lower_limit = getY + HEIGHT
        *   -1300 + 600 = -700
        */
       assertEquals(-700.0, camera.getLowerLimit());
    }
}
