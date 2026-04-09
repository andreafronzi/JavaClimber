package javaclimber.worldConstructor.gameObjectSpawn.addOnSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

public class AddOnPoolTest {

    private final static double PLATFORM_WIDTH = 30;
    private final static double PLATFORM_HEIGHT = 20;
    
    private final static double MIN_Y = 0;
    private final static double MAX_Y = 800;

    private final static double MIN_X = 0;
    private final static double MAX_X = 400;
    
    private AddOnPool addOnPoolEasy;

    @BeforeEach
    public void setUp() {
        var spawnPoolEasy = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
        var world = new UpperWorld(new BoundWorldImpl(new BoundY(MIN_Y, MAX_Y), new Boundary(MIN_X, MAX_X)));
        var spawnPoolCreator = new SpawnPoolCreatorImpl(world);
        spawnPoolCreator.setSpawnPool(spawnPoolEasy);
        this.addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, 0.3);
    }

    @Test
    public void testGetAddOnPool() {
        var addOnPool = this.addOnPoolEasy.getAddOnPool();
        assertEquals(false, addOnPool.isEmpty());
    }

    @Test 
    public void testGetChanceAddOn() {
        var chanceAddOn = this.addOnPoolEasy.getChanceAddOn();
        assertEquals( 0.3, chanceAddOn);
    }

}
