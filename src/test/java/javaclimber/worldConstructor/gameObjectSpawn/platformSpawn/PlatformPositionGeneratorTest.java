package javaclimber.worldConstructor.gameObjectSpawn.platformSpawn;   

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.Distance;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPositionGeneratorImpl;

/**
 * Test for the PlatformPositionGeneratorImpl class.
 */
public class PlatformPositionGeneratorTest {

    private PlatformPositionGeneratorImpl platformPositionGenerator;

    private static final double MAX_X = 400;
    private static final double MIN_X = 0;
    
    private static final double MAX_Y = 800;
    private static final double MIN_Y = 0;

    private static final double POS_X = 200;
    private static final double POS_Y = 780;

    private static final double PLATFORM_WIDTH = 50;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double MAX_DISTANCE_Y = 100;
    private static final double MIN_DISTANCE_Y = 50;
    private static final double MAX_DISTANCE_X = 50;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        var pos = new Vector2dImpl(POS_X, POS_Y);
        var bound = new BoundWorldImpl(new BoundY(MIN_Y, MAX_Y), new Boundary(MIN_X, MAX_X));
        var distance = new Distance(MAX_DISTANCE_Y, MIN_DISTANCE_Y, MAX_DISTANCE_X);    
        this.platformPositionGenerator = new PlatformPositionGeneratorImpl(bound, pos, distance);
    }

    /**
     * Test for generating a platform position.
     */
    @Test
    void testGeneratePosition() {
        var newPos = platformPositionGenerator.generatePosition(PLATFORM_WIDTH, PLATFORM_HEIGHT, new Vector2dImpl(POS_X, POS_Y));
        assertEquals(true, newPos.getX() >= MIN_X && newPos.getX() <= MAX_X);
        assertEquals(true, newPos.getY() >= MIN_Y && newPos.getY() <= MAX_Y);
        assertEquals(true, newPos.getX() >= POS_X - MAX_DISTANCE_X);
        assertEquals(true, newPos.getX() <= POS_X + MAX_DISTANCE_X);
        assertEquals(true, newPos.getY() >= POS_Y - MAX_DISTANCE_Y );
        assertEquals(true, newPos.getY() <= POS_Y - MIN_DISTANCE_Y);
    }

}
