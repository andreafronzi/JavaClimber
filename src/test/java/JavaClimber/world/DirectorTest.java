package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldConstructor.api.Director;
import it.unibo.model.worldConstructor.impl.DirectorImpl;

public class DirectorTest {

    private Director director;

    private static final double X = 50;
    private static final double Y = 50;

    private static final double WIDTH = 100;
    private static final double HEIGHT = 20;
    
    private static final double DELTA = 0.01;

    private void setUp() {
        director = new DirectorImpl(WIDTH, HEIGHT);
    }

    @Test
    public void testNormalPlatformCreation() {
        setUp();
        Platform platform = director.normalPlatform(new Vector2dImpl(X, Y));
        assertEquals(X, platform.getPosX());
        assertEquals(Y, platform.getPosY());
    }

    @Test
    public void testMovingOnTouchPlatform() {
        setUp();
        Platform platform = director.movingOnTouchPlatform(new Vector2dImpl(X, Y));
        assertEquals(X, platform.getPosX());
        assertEquals(Y, platform.getPosY());
    }

    @Test
    public void testMovingPlatform() {
        setUp();
        Platform platform = director.movingPlatform(new Vector2dImpl(X, Y));
        platform.updatePosition(DELTA, new Boundary(2, 2));
    }

}
