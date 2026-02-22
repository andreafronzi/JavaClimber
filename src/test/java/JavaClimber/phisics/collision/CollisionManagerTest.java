package JavaClimber.phisics.collision;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.gameObj.impl.CoinImpl;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.gameObj.impl.EnemyImpl;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.physics.collision.impl.CollisionManagerImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.impl.RealWorld;

/**
 * Test class for the {@link CollisionManagerImpl}.
 * This class contains unit tests to verify the correct behavior of the collision detection mechanism: 
 * verify that the collision logic correctly work and that when a collision occurs the collided element is removed from the world. 
 */
public class CollisionManagerTest {

    private static final double X = 10;
    private static final double Y = 100;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 30;

    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;

    private static final double LEFT_SIDE = 0;
    private static final double RIGHT_SIDE = 100;

    /**
     * The {@link Alien} instance used in the tests.
     */
    private Alien alien;

    /**
     * The {@link CollisionManager} instance used to detect collisions in the tests.
     */
    private CollisionManager collisionManager;

    /**
     * The {@link RealWorld} instance representing the game world in which the collisions are detected and where GameObj stands.
     */
    private RealWorld realWorld;

    /**
     * The {@link Enemy}, {@link Coin}, and {@link Gadget} instances used in the tests to verify the collision detection and the correct removal of collided elements from the world.
     */
    private Enemy e;
    private Coin c;
    private Gadget g;

    /**
     * Sets up the test environment before each test case is executed. Initializes the Alien, CollisionManager, RealWorld, and the Enemy, Coin, and Gadget instances used in the tests.
     */
    @BeforeEach
    public void setUp() {
        this.alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
                new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        final Boundary boundary = new Boundary(LEFT_SIDE, RIGHT_SIDE);
        this.collisionManager = new CollisionManagerImpl(boundary);
        this.realWorld = new RealWorld(alien);
        this.e = new EnemyImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT - 1));
        this.c = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT - 1), new ScoreManagerImpl(0));
        this.g = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT - 1));
    }

    /**
     * Tests the {@link CollisionManager#detectCollisions(RealWorld)} method to verify that when the Alien collides with an Enemy, the Enemy is removed from the RealWorld.
     */
    @Test
    public void detectCollisionOnEnemyTest() {
        this.realWorld.addMonster(this.e);
        this.collisionManager.detectCollisions(realWorld);
        assertFalse(this.realWorld.getMonsters().contains(this.e));
    }

    /**
     * Tests the {@link CollisionManager#detectCollisions(RealWorld)} method to verify that when the Alien collides with a Coin, the Coin is removed from the RealWorld.
     */
    @Test
    public void detectCollisionOnCoinTest() {
        this.realWorld.addMoney(this.c);
        this.collisionManager.detectCollisions(realWorld);
        assertFalse(this.realWorld.getMoneys().contains(this.c));
    }

    /**
     * Tests the {@link CollisionManager#detectCollisions(RealWorld)} method to verify that when the Alien collides with a Gadget, the Gadget is removed from the RealWorld.
     */
    @Test
    public void detectCollisionOnGadgetTest() {
        this.realWorld.addGadget(this.g);
        this.collisionManager.detectCollisions(realWorld);
        assertFalse(this.realWorld.getGadgets().contains(this.g));
    }
}
