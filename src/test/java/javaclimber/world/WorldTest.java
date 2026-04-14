package javaclimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;

/**
 * Test for the World class.
 */
public class WorldTest {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;

    private static final double X_MIN = 0;
    private static final double X_MAX = 600;

    private static final double WIDTH = 0;
    private static final double HEIGHT = 0;

    /**
     * The world to test.
     */
    private World world;

    /**
     * Set up the world before each test.
     * 
     * @param upperWorld the upper world
     * @param realWorld the real world
     */
    private void setUpWorld(final QueueWorld upperWorld, final GameWorld realWorld) {
        this.world = new World(upperWorld, realWorld);
    }

    /**
     * Test for getting the upper world.
     */
    @Test
    public void getUpperWorldTest() {
        final BoundWorld boundWorld = new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX));
        final AlienImpl alien = new AlienImpl(null, null, WIDTH, HEIGHT, null);
        final QueueWorld upperWorld = new UpperWorld(boundWorld);
        final GameWorld realWorld = new RealWorld(alien, boundWorld);
        setUpWorld(upperWorld, realWorld);
        assertEquals(upperWorld, this.world.getUpperWorld());
    }

    /**
     * Test for getting the real world.
     */
    @Test
    public void getRealWorldTest() {
        final BoundWorld boundWorld = new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX));
        final AlienImpl alien = new AlienImpl(null, null, WIDTH, HEIGHT, null);
        final QueueWorld upperWorld = new UpperWorld(boundWorld);
        final GameWorld realWorld = new RealWorld(alien, boundWorld);
        setUpWorld(upperWorld, realWorld);
        assertEquals(realWorld, this.world.getRealWorld());
    }
}
