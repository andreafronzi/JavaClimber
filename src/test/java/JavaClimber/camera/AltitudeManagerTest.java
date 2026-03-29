package JavaClimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * Tests for {@link AltitudeManager}
 */
public class AltitudeManagerTest {

    private Alien alien;
    private AltitudeManager altitudeManager;
    private static final double THRESHOLD = 350.0;

    /**
     * Setup before each test.
     */
    @BeforeEach
    void setup() {
        alien = new AlienImpl(new Vector2dImpl(100, 600), new Vector2dImpl(0, 0), 50, 50, null);
        altitudeManager = new AltitudeManager(alien, THRESHOLD);
    }

    /**
     * Verifies that when the alien is below the threshold, the observer is not notified (delta remains 0).
     */
    @Test
    void testNoNotifyBelowThreshold() {
        final double[] result = { 0.0 };
        altitudeManager.addObserver(delta -> result[0] = delta);
        alien.setPosition(new Vector2dImpl(100, 400));
        altitudeManager.verifiedAltitude();
        assertEquals(0.0, result[0]);
    }

    /**
     * Verifies that when the alien moves above the threshold, the observer is notified with the correct delta.
     */
    @Test
    void testNotifyExceedingThreshold() {
        final double[] result = { 0.0 };
        altitudeManager.addObserver(delta -> result[0] = delta);
        alien.setPosition(new Vector2dImpl(100, 300));
        altitudeManager.verifiedAltitude();
        assertEquals(50.0, result[0]);
    }

    /**
     * Verifies that when the alien moves above the threshold multiple times, the observer is notified with the correct cumulative delta.
     */
    @Test
    void testContinuousClimb() {
        final double[] totalDelta = { 0.0 };
        altitudeManager.addObserver(delta -> {
            totalDelta[0] += delta;
            alien.setPosition(new Vector2dImpl(alien.getPosX(), THRESHOLD));
        });

        alien.setPosition(new Vector2dImpl(100, 300));
        altitudeManager.verifiedAltitude();
        assertEquals(50.0, totalDelta[0]);
        assertEquals(THRESHOLD, alien.getPosY());

        alien.setPosition(new Vector2dImpl(100, 320));
        altitudeManager.verifiedAltitude();
        assertEquals(80.0, totalDelta[0]);
        assertEquals(THRESHOLD, alien.getPosY());
    }
}
