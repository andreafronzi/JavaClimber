package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformPhysic.impl.HorizontalMovementBehavior;
import it.unibo.model.physics.platformPhysic.impl.OnTouchDestroyBehavior;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.EnemyImpl;
import it.unibo.model.gameObj.platformbuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.impl.CoinImpl;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;

public class RealWorldTest {

    private GameWorld realWorld;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;

    private static final double X_MIN = 0;
    private static final double X_MAX = 600;

    private static final double POS_X = 0;
    private static final double POS_Y = 0;

    private static final double VELOCITY_X = 0;
    private static final double VELOCITY_Y = 0;

    private static final double PLATFORM_WIDTH = 10;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double MONSTER_WIDTH = 10;
    private static final double MONSTER_HEIGHT = 10;

    private static final double GADGET_WIDTH = 10;
    private static final double GADGET_HEIGHT = 10;

    private static final double MONEY_WIDTH = 10;
    private static final double MONEY_HEIGHT = 10;

    @BeforeEach
    private void setUpRealWorld() {
        this.realWorld = new RealWorld(
                new AlienImpl(new Vector2dImpl(POS_X, POS_Y), new Vector2dImpl(VELOCITY_X, VELOCITY_Y), PLATFORM_WIDTH,
                        PLATFORM_HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()))),
                new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
    }

    
    private Platform createStaticPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).build();
        return platform;
    }
    
    private Platform createMovingPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).addMovementBehaviour(new HorizontalMovementBehavior(100)).build();
        return platform;
    }
    
    private Platform createOnTouchPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).addJumpBehaviour(new OnTouchDestroyBehavior()).build();
        return platform;
    }

    @Test
    public void getAlienTest() {
        Alien alien = this.realWorld.getAlien();
        assertEquals(alien, this.realWorld.getAlien());
        assertEquals(POS_X, alien.getPosX());
        assertEquals(POS_Y, alien.getPosY());
    }
    
    @Test
    public void removeStaticPlatformTest() {
        Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addStaticPlatform(platform);
        assertEquals(true, this.realWorld.removeStaticPlatform(platform));
    }

    @Test
    public void removeMovingPlatformTest() {
        Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addMovingPlatform(platform);
        assertEquals(true, this.realWorld.removeMovingPlatform(platform));
    }

    @Test
    public void removeOnTouchPlatformTest() {
        Platform platform = createOnTouchPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addOnTouchPlatform(platform);
        assertEquals(true, this.realWorld.removeOnTouchPlatform(platform));
    }

    @Test
    public void removeMonsterTest() {
        Enemy monster = new EnemyImpl(MONSTER_WIDTH, MONSTER_HEIGHT, new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addMonster(monster);
        assertEquals(true, this.realWorld.removeMonster(monster));
    }

    @Test
    public void removeGadgetTest() {
        Gadget gadget = new EliCap(GADGET_WIDTH, GADGET_HEIGHT, new Vector2dImpl(POS_X, POS_Y));
        this.realWorld.addGadget(gadget);
        assertEquals(true, this.realWorld.removeGadget(gadget));
    }

    @Test
    public void removeMoneyTest() {
        Coin money = new CoinImpl(MONEY_WIDTH, MONEY_HEIGHT, new Vector2dImpl(POS_X, POS_Y), null);
        this.realWorld.addMoney(money);
        assertEquals(true, this.realWorld.removeMoney(money));
    }
}
