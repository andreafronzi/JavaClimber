package JavaClimber.GameObj;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienTest {

  private static final double EPSILON = 0.001;
  private static final double X = 10;
  private static final double Y = 20;
  private static final double NEW_X = 30;
  private static final double NEW_Y = 40;
  private static final double WIDTH = 50;
  private static final double HEIGTH = 50;
  /**
   * Tests the {@link Alien#getPosX()} method.
   */
  @Test
  void testGetPosX() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    assertEquals(X, alien.getPosX(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getPosY()} method.
   */
  @Test
  void testGetPosY() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    assertEquals(Y, alien.getPosY(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getWidth()} method.
   */
  @Test
  void testGetWidth() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    assertEquals(HEIGTH, alien.getHeigth(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getHeigth()} method.
   */
  @Test
  void testGetHeigth() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    assertEquals(HEIGTH, alien.getHeigth(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getSpeedX()} method.
   */
  @Test
  void testGetSpeedX() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    assertEquals(0, alien.getSpeedX(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getSpeedY()} method.
   */
  @Test
  void testGetSpeedY() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    assertEquals(0, alien.getSpeedY(), EPSILON);
  }

  /**
   * Tests the {@link Alien#setPosition(Vector2d)} method.
   */
  @Test
  void testSetPosition() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    alien.setPosition(new Vector2dImpl(NEW_X, NEW_Y));
    assertEquals(NEW_X, alien.getPosX(), EPSILON);
    assertEquals(NEW_Y, alien.getPosY(), EPSILON);
  }

  /**
   * Tests the {@link Alien#setSpeed(Vector2d)} method.
   */
  @Test
  void testSetSpeed() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), WIDTH, HEIGTH);
    alien.setSpeed(new Vector2dImpl(NEW_X, NEW_Y));
    assertEquals(NEW_X, alien.getSpeedX(), EPSILON);
    assertEquals(NEW_Y, alien.getSpeedY(), EPSILON);
  }

}
