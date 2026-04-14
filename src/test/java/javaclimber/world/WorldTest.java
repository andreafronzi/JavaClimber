package javaclimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.*;

/**
 * Test for the World class.
 */
public class WorldTest {

    private World world;

    /**
     * Set up the world before each test.
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
        BoundWorld boundWorld = new BoundWorldImpl(new BoundY(0, 800), new Boundary(0, 600));
        AlienImpl alien = new AlienImpl(null, null, 0, 0, null);
        QueueWorld upperWorld = new UpperWorld(boundWorld);
        GameWorld realWorld = new RealWorld(alien, boundWorld);
        setUpWorld(upperWorld, realWorld);
        assertEquals(upperWorld, this.world.getUpperWorld());
    }

    /**
     * Test for getting the real world.
     */
    @Test
    public void getRealWorldTest() {
        BoundWorld boundWorld = new BoundWorldImpl(new BoundY(0, 800), new Boundary(0, 600));
        AlienImpl alien = new AlienImpl(null, null, 0, 0, null);
        UpperWorld upperWorld = new UpperWorld(boundWorld);
        RealWorld realWorld = new RealWorld(alien, boundWorld);
        setUpWorld(upperWorld, realWorld);
        assertEquals(realWorld, this.world.getRealWorld());
    }
}