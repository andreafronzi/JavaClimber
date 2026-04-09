package javaclimber.worldConstructor.worldGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.GameObjDimension;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.Distance;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPoolEasy;
import it.unibo.model.worldConstructor.worldGenerator.impl.WorldConstructorImpl;

public class WorldConstructorTest {

    private WorldConstructorImpl worldConstructor;
    private QueueWorld upperWorld;
    private Difficult difficult;
    private SpawnPoolCreatorImpl spawnPoolCreator;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 600;

    private static final double X_MIN = 0;
    private static final double X_MAX = 800;

    private static final double PLATFORM_WIDTH = 100;
    private static final double PLATFORM_HEIGHT = 20;

    private static final double MAX_PLATFORM_DISTANCE_X = 20;

    private static final double MAX_PLATFORM_DISTANCE_Y = 70;
    private static final double MIN_PLATFORM_DISTANCE_Y = 10;

    private static final double SPAWN_PROBABILITY = 0.5;

    private static final double HEIGHT_EASY = 0;

    @BeforeEach
    public void setUp() {
        var boundary = new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX));
        this.upperWorld = new UpperWorld(boundary);
        var distance = new Distance(MAX_PLATFORM_DISTANCE_Y, MIN_PLATFORM_DISTANCE_Y, MAX_PLATFORM_DISTANCE_X);
        this.spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
        var spawnPool = new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT, new ScoreManagerImpl());
        this.spawnPoolCreator.setSpawnPool(spawnPool);
        var addOnPool = new AddOnPoolEasy(this.spawnPoolCreator, SPAWN_PROBABILITY);
        var platformPool = new PlatformPoolEasy(this.spawnPoolCreator, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.difficult = new Difficult(HEIGHT_EASY, distance, spawnPool, addOnPool, platformPool);
        this.worldConstructor = new WorldConstructorImpl(upperWorld, difficult, this.spawnPoolCreator);
    }
    
    @Test
    public void fillWorldTest() {
        this.worldConstructor.fillWorld();
        if(!this.upperWorld.getGadgets().isEmpty()){
            assertEquals(true, this.upperWorld.getGadgets().getLast().getPosY() > Y_MIN - GameObjDimension.ELYCAP_HEIGHT);
        }
        if (!this.upperWorld.getMoneys().isEmpty()) {
            assertEquals(true, this.upperWorld.getMoneys().getLast().getPosY() > Y_MIN - GameObjDimension.COIN_HEIGHT);
        }
        if (!this.upperWorld.getMonsters().isEmpty()) {
            assertEquals(true, this.upperWorld.getMonsters().getLast().getPosY() > Y_MIN - GameObjDimension.ENEMY_HEIGHT);
        }
        if (!this.upperWorld.getMovingPlatforms().isEmpty()) {
            assertEquals(true, this.upperWorld.getMovingPlatforms().getLast().getPosY() > Y_MIN);
        }
        if (!this.upperWorld.getStaticPlatforms().isEmpty()) {
            assertEquals(true, this.upperWorld.getStaticPlatforms().getLast().getPosY() > Y_MIN);
        }
        if (!this.upperWorld.getOnTouchPlatforms().isEmpty()) {
            assertEquals(true, this.upperWorld.getOnTouchPlatforms().getLast().getPosY() > Y_MIN);
        }
    }
}