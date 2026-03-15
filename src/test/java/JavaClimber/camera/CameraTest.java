package JavaClimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.impl.CameraImpl;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.PlatformImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.GameObjDimension;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.Distance;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPoolEasy;
import it.unibo.model.worldConstructor.worldGenerator.api.WorldConstructor;
import it.unibo.model.worldConstructor.worldGenerator.impl.WorldConstructorImpl;


/**
 * Tests for {@link CameraImpl}
 */
public class CameraTest {

    private static final double VIEW_WIDTH = 800.0;
    private static final double VIEW_HEIGHT = 600.0;
    private static final double PLAT_W = 100.0;
    private static final double PLAT_H = 20.0;

    private World world;
    private WorldConstructor wc;
    private CameraImpl camera;
    private ScoreManager scoreManager;


    /**
     * Set up all to simulate the camera behaviour. 
     */
    @BeforeEach
    void setUp() {
        ShopItemFactory factory = new ShopItemFactoryImpl();
        Inventory inventory = new InventoryImpl(factory);
        ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(inventory);
        AlienImpl alien = new AlienImpl(new Vector2dImpl(0, 0), new Vector2dImpl(10, 10), 50, 50, activeUpgrades);
        
        Boundary boundX = new Boundary(0, VIEW_WIDTH);
        BoundY boundY = new BoundY(-2000, VIEW_HEIGHT);
        BoundWorld boundWorld = new BoundWorldImpl(boundY, boundX);
        RealWorld realWorld = new RealWorld(alien, boundWorld);
        UpperWorld upperWorld = new UpperWorld(boundWorld);
        world = new World(upperWorld, realWorld);
        this.scoreManager = new ScoreManagerImpl();

        
        var distanceEasy = new Distance(200, 100, 300);
        var spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
        var spawnPoolEasy = new SpawnPoolEasy(GameObjDimension.EASY_PLATFORM_WIDTH,
                GameObjDimension.EASY_PLATFORM_HEIGHT, scoreManager);
        var platformPoolEasy = new PlatformPoolEasy(spawnPoolCreator, GameObjDimension.EASY_PLATFORM_WIDTH,
                GameObjDimension.EASY_PLATFORM_HEIGHT);
        var addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, 0.5);
        var difficultList = List.of(new Difficult(0, distanceEasy, spawnPoolEasy, addOnPoolEasy, platformPoolEasy));
        spawnPoolCreator.setSpawnPool(spawnPoolEasy);
        
        this.wc = new WorldConstructorImpl(upperWorld, difficultList.getFirst(), spawnPoolCreator);

        this.camera = new CameraImpl(VIEW_WIDTH, VIEW_HEIGHT, world, wc);
    }
    /**
     * Tests that the camera is initialized with the correct dimensions.
     */
    @Test
    void testCorrectInitialization() {
        assertEquals(VIEW_WIDTH, camera.getViewportWidth());
        assertEquals(VIEW_HEIGHT, camera.getViewportHeight());
    }

    /**
     * Verifies that the game objects(in this case platform) move downwards when the camera moves up.
     */
    @Test
    void testMovement() {
        PlatformImpl platform = new PlatformImpl(new Vector2dImpl(100, 100), PLAT_W, PLAT_H, Optional.empty(), Optional.empty());
        world.getRealWorld().addStaticPlatform(platform);
        camera.update(50.0);
        assertEquals(150.0, platform.getPosY());
        assertEquals(50.0, world.getRealWorld().getAlien().getPosY());
    }

    /**
     * Verifies the promotion logic from the UpperWorld to RealWorld when a object enter in a specific limit.
     */
    @Test
    void testTransferFromUpperToReal() {
        PlatformImpl platform = new PlatformImpl(new Vector2dImpl(200, -101), PLAT_W, PLAT_H, Optional.empty(), Optional.empty());
        world.getUpperWorld().addStaticPlatform(platform);
        camera.update(0.5);
        assertFalse(world.getUpperWorld().getStaticPlatforms().isEmpty());

        camera.update(10.0);
        assertFalse(world.getRealWorld().getStaticPlatforms().isEmpty());
    }

    /**
     * Verifies that objects that fall below the screen are removed from RealWorld.
     */
    @Test
    void testCleanRealWorld() {
        PlatformImpl platform = new PlatformImpl(new Vector2dImpl(100, 590), PLAT_W, PLAT_H, Optional.empty(), Optional.empty());
        world.getRealWorld().addStaticPlatform(platform);
        camera.update(20.0);
        assertFalse(world.getRealWorld().getStaticPlatforms().contains(platform));
    }

}
