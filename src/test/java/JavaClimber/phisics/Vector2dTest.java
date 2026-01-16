package JavaClimber.phisics;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for testing the functionality of the {@link Vector2d} implementation {@link Vector2dImpl}.
 */
public class Vector2dTest {
  /**
   * Tests the {@link Vector2d#getX()} method.
   */
  @Test
  public void testGetX() {
   final Vector2d p = new Vector2dImpl(10, 20);
    assertEquals(10, p.getX(), 0.001);
  }

    /**
     * Tests the {@link Vector2d#getY()} method.
     */
    @Test
    public void testGetY() {
      final Vector2d p = new Vector2dImpl(10, 20);
      assertEquals(20, p.getY(), 0.001);
    }

    @Test
    public void testNegativeCoordinates() {
      final Vector2d p = new Vector2dImpl(-5, -15);
      assertEquals(-5, p.getX(), 0.001);
      assertEquals(-15, p.getY(), 0.001);
    }

    @Test
    public void testZeroCoordinates() {
        final Vector2d p = new Vector2dImpl(0, 0);
        assertEquals(0, p.getX(), 0.001);
        assertEquals(0, p.getY(), 0.001);
    }

    /**
     * Tests the {@link Vector2d#setX(double)} method.
     */
    @Test
    public void testSetX() {
        final Vector2d p = new Vector2dImpl(10, 20);
        p.setX(30);
        assertEquals(30, p.getX(), 0.001);
    }

  /**
   * Tests the {@link Vector2d#setY(double)} method.
   */
  @Test
    public void testSetY() {
        final Vector2d d = new Vector2dImpl(10, 20);
        d.setY(30);
        assertEquals(30, d.getY(), 0.001);
    }



}
