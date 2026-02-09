package JavaClimber.phisics;

import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.impl.Vector2dImpl;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for AlienNormalPhysic.
 */
public class AlienNormalPhysicTest {

  private static final double EPSILON = 0.001;

  private static final double X = 10;
  private static final double Y = 100;

  private static final double X1 = 90;

  private static final double SPEED_X = 0;
  private static final double SPEED_Y = 0;

  private static final double SPEED_AFTER_JUMP = -10;
  private static final double SPEED1_Y = 50;
  private static final double SPEED1_X = 50;
  private static final double SPEED2_X = -200;

  private static final double WIDTH = 50;
  private static final double HEIGTH = 50;

  private static final double LEFT_BOUNDARY = 0;
  private static final double RIGHT_BOUNDARY = 100;

  private static final double DT = 0.40;

  private static final double NEW_SPEED_Y = 20;
  private static final double NEW_Y = 108;

  /**
   * Test the {@link AlienNormalPhysic#update(Alien, double, Boundary)} method to verify expected vertical movement behavior.
   */
  @Test
  void testUpdateAlienPosition() {
    final AlienPhysic physic = new AlienNormalPhysic();
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGTH);
    assertEquals(0,alien.getSpeedY(), EPSILON);
    final Boundary boundary = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    physic.update(alien, DT, boundary);
    assertEquals(NEW_SPEED_Y, alien.getSpeedY(), EPSILON);
    assertEquals(NEW_Y, alien.getPosY(), EPSILON);
  }

  /**
   * Tests the behavior of the {@link AlienNormalPhysic#update(Alien, double, Boundary)} method.
   * It verifies that Pacman effect correctly repositions the alien to the left edge of the boundary.
   */
  @Test
  void testRightToLeftPacmanEffect() {
    final AlienPhysic physic = new AlienNormalPhysic();
    final Alien alien = new AlienImpl(new Vector2dImpl(X1, Y), new Vector2dImpl(SPEED1_X, SPEED_Y), WIDTH, HEIGTH);
    final Boundary boundary = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    physic.update(alien, DT, boundary);
    assertEquals(LEFT_BOUNDARY, alien.getPosX(), EPSILON);
  }

  /**
   * Tests the behavior of the {@link AlienNormalPhysic#update(Alien, double, Boundary)} method.
   * It verifies that Pacman effect correctly repositions the alien to the right edge of the boundary.
   */
  @Test
  void testLeftToRightPacmanEffect() {
    final AlienPhysic physic = new AlienNormalPhysic();
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED2_X, SPEED_Y), WIDTH, HEIGTH);
    final Boundary boundary = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    physic.update(alien, DT, boundary);
    assertEquals(RIGHT_BOUNDARY - WIDTH, alien.getPosX(), EPSILON);
  }

  @Test
  void testHitGadget() {
    final AlienPhysic physic = new AlienNormalPhysic();
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED1_Y), WIDTH, HEIGTH);
    final Gadget eliCap = new EliCap(HEIGTH, WIDTH, new Vector2dImpl(X, Y + HEIGTH));
    
    physic.hitGadget(alien, eliCap);
    assertEquals(alien.getPosY(), eliCap.getPosY() - alien.getHeight(), EPSILON);
    assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);
  }

  /**
   * Tests the {@link AlienNormalPhysic#hitPlatform(Alien, Platform, Boundary)} method.
   */
  @Test
  void testHitPlatform() {
    final AlienPhysic physic = new AlienNormalPhysic();
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED1_Y), WIDTH, HEIGTH);
    final Boundary boundary = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    final PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
    final Platform platform = platformBuilder
                .at(new Vector2dImpl(X, Y + HEIGTH))
                .size(WIDTH, HEIGTH)
                .build();

    physic.hitPlatform(alien, platform, boundary);
    assertEquals(alien.getPosY(), platform.getPosY() - alien.getHeight(), EPSILON);
    assertEquals(SPEED_AFTER_JUMP, alien.getSpeedY(), EPSILON);
  }
}
