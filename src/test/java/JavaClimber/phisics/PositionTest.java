package JavaClimber.phisics;

import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.PositionImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    public void testGetX() {
        final Position p = new PositionImpl(10, 20);
        assertEquals(10, p.getX(), 0.001);
    }

    @Test
    public void testGetY() {
        final Position p = new PositionImpl(10, 20);
        assertEquals(20, p.getY(), 0.001);
    }

    @Test
    public void testNegativeCoordinates() {
        final Position p = new PositionImpl(-5, -15);
        assertEquals(-5, p.getX(), 0.001);
        assertEquals(-15, p.getY(), 0.001);
    }

    @Test
    public void testZeroCoordinates() {
        final Position p = new PositionImpl(0, 0);
        assertEquals(0, p.getX(), 0.001);
        assertEquals(0, p.getY(), 0.001);
    }

}
