package JavaClimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * Tests for {@link AltitudeManager}
 */
public class AltitudeManagerTest {

    private Alien alien;
    private AltitudeManager altitudeManager;

    /**
     * Setup before each test.
     */
    @BeforeEach
    void setup() {
        alien = new AlienImpl(new Vector2dImpl(100, 0), new Vector2dImpl(0, 0), 50, 50);
        altitudeManager = new AltitudeManager(alien);
    }

    /**
     * Verifies that when the alien moves up, the observer is notified with the exact distance climbed.
     */
    @Test
    void testNotifyClimb() {
        final double[] result = { 0.0 };
        altitudeManager.addObserver(delta -> result[0] = delta);
        alien.setPosition(new Vector2dImpl(100, -100));
        altitudeManager.verifiedAltitude();
        assertEquals(100.0, result[0]);
    }

    /**
     * Verifies that when the alien moves down, the observer receives no notification.
     */
    @Test
    void testNotifyFall() {
        final double[] result = { 0.0 };
        altitudeManager.addObserver(delta -> result[0] = delta);
        alien.setPosition(new Vector2dImpl(100, 50));
        altitudeManager.verifiedAltitude();
        assertEquals(0.0, result[0]);
    }
}
