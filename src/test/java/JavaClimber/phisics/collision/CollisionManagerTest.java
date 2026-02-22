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

public class CollisionManagerTest {

    private static final double EPSILON = 0.001;

    private static final double X = 10;
    private static final double Y = 100;

    private static final double X1 = 90;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 30;

    private static final double SPEEDY_WITH_ELICAP = -10;
    private static final double SPEED_AFTER_JUMP = -10;
    private static final double SPEED1_Y = 50;
    private static final double SPEED1_X = 50;
    private static final double SPEED2_X = -200;

    private static final double WIDTH = 50;
    private static final double HEIGTH = 50;

    private static final double DT = 0.40;

    private static final double LEFT_SIDE = 0;
    private static final double RIGHT_SIDE = 100;

    private Alien alien;
    private CollisionManager collisionManager;
    private RealWorld realWorld;

    private Enemy e;
    private Coin c;
    private Gadget g;

    @BeforeEach
    public void setUp() {
        this.alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGTH,
                new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        final Boundary boundary = new Boundary(LEFT_SIDE, RIGHT_SIDE);
        this.collisionManager = new CollisionManagerImpl(boundary);
        this.realWorld = new RealWorld(alien);
        this.e = new EnemyImpl(HEIGTH, WIDTH, new Vector2dImpl(X, Y + HEIGTH - 1));
        this.c = new CoinImpl(HEIGTH, WIDTH, new Vector2dImpl(X, Y + HEIGTH - 1), new ScoreManagerImpl(0));
        this.g = new EliCap(HEIGTH, WIDTH, new Vector2dImpl(X, Y + HEIGTH - 1));
    }

    @Test
    public void detectCollisionOnEnemyTest() {
        this.realWorld.addMonster(this.e);
        this.collisionManager.detectCollisions(realWorld);
        assertFalse(this.realWorld.getMonsters().contains(this.e));
    }

    @Test
    public void detectCollisionOnCoinTest() {
        this.realWorld.addMoney(this.c);
        this.collisionManager.detectCollisions(realWorld);
        assertFalse(this.realWorld.getMoneys().contains(this.c));
    }

    @Test
    public void detectCollisionOnGadgetTest() {
        this.realWorld.addGadget(this.g);
        this.collisionManager.detectCollisions(realWorld);
        assertFalse(this.realWorld.getGadgets().contains(this.g));
    }
}
