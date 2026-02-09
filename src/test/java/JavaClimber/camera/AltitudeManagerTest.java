package JavaClimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.physics.impl.Vector2dImpl;

public class AltitudeManagerTest {

    private Alien alien;
    private AltitudeManager altitudeManager;
    private TestObserver observer;

    private class TestObserver implements AltitudeObserver {
        double receivedDelta = 0;
        boolean wasCalled = false;

        @Override
        public void update(double delta) {
            this.receivedDelta = delta;
            this.wasCalled = true;
        }
    }

    @BeforeEach
    void setup() {
        alien = new AlienImpl(new Vector2dImpl(100, 0), new Vector2dImpl(0, 0), 50, 50);
        altitudeManager = new AltitudeManager(alien);
        observer = new TestObserver();
        altitudeManager.addObserver(observer);
    }

    @Test
    void testNotifyClimb() {
        alien.setPosition(new Vector2dImpl(100, -100));
        altitudeManager.verifiedAltitude();
        assertTrue(observer.wasCalled);
        assertEquals(100.0, observer.receivedDelta);
    }

    @Test
    void testNotifyFall() {
        alien.setPosition(new Vector2dImpl(100, 50));
        altitudeManager.verifiedAltitude();;
        assertEquals(0.0, observer.receivedDelta);
    }
}
