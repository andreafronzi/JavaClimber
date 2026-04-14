package javaclimber.worldConstructor.gameObjectSpawn.platformSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Director;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.DirectorImpl;

/**
 * Test for the DirectorImpl class.
 */
public class DirectorTest {

    private Director director;

    private static final double X = 50;
    private static final double Y = 50;

    private static final double WIDTH = 100;
    private static final double HEIGHT = 20;
    
    private static final double DELTA = 0.01;

    /**
     * Set up the test environment.
     */
    private void setUp() {
        director = new DirectorImpl(WIDTH, HEIGHT);
    }

    /**
     * Test for creating a normal platform.
     */
    @Test
    public void testNormalPlatformCreation() {
        setUp();
        Platform platform = director.normalPlatform(new Vector2dImpl(X, Y));
        assertEquals(X, platform.getPosX());
        assertEquals(Y, platform.getPosY());
    }

    /**
     * Test for creating a moving on-touch platform.
     */
    @Test
    public void testMovingOnTouchPlatform() {
        setUp();
        Platform platform = director.movingOnTouchPlatform(new Vector2dImpl(X, Y));
        assertEquals(X, platform.getPosX());
        assertEquals(Y, platform.getPosY());
    }

    /**
     * Test for creating a moving platform.
     */
    @Test
    public void testMovingPlatform() {
        setUp();
        Platform platform = director.movingPlatform(new Vector2dImpl(X, Y));
        platform.updatePosition(DELTA, new Boundary(2, 2));
    }

}
