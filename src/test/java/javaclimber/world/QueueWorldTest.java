package javaclimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.gameobj.impl.EnemyImpl;
import it.unibo.model.gameobj.platformbuilder.impl.PlatformBuilderImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.impl.HorizontalMovementBehavior;
import it.unibo.model.physics.platformphysic.impl.OnTouchDestroyBehavior;

public class QueueWorldTest {

    private UpperWorld upperWorld;

    private static final double POS_X = 0;
    private static final double POS_Y = 0;

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;

    private static final double X_MIN = 0;
    private static final double X_MAX = 600;

    @BeforeEach
    private void setUpUpperWorld() {
        this.upperWorld = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
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

    private Platform createOnToucPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).addJumpBehaviour(new OnTouchDestroyBehavior()).build();
        return platform;
    }

    @Test
    public void addStaticPlatformTest() {
        Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        assertEquals(true, this.upperWorld.addStaticPlatform(platform));
    }

    @Test
    public void addMovingPlatformTest() {
        Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        assertEquals(true, this.upperWorld.addMovingPlatform(platform));
    }

    @Test
    public void addOnTouchPlatformTest() {
        Platform platform = createOnToucPlatform(new Vector2dImpl(POS_X, POS_Y));
        assertEquals(true, this.upperWorld.addOnTouchPlatform(platform));
    }

    @Test
    public void addMonsterTest() {
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(POS_X, POS_Y));
        assertEquals(true, this.upperWorld.addMonster(monster));
    }

    @Test
    public void addGadgetTest() {
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(POS_X, POS_Y));
        assertEquals(true, this.upperWorld.addGadget(gadget));
    }

    @Test
    public void addMoneyTest() {
        Coin money = new CoinImpl(0, 0, new Vector2dImpl(POS_X, POS_Y), null);
        assertEquals(true, this.upperWorld.addMoney(money));
    }

    /*
     * @Test
     * public void addTrapTest() {
     * setUpUpperWorld();
     * Trap trap = new Trap(new Vector2dImpl(0, 0));
     * assertEquals(true, this.upperWorld.addTrap(trap));
     * }
     */

    @Test
    public void removeFirstStaticPlatformTest() {
        Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addStaticPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstStaticPlatform().get());
    }

    @Test
    public void removeFirstMovingPlatformTest() {
        Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMovingPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstMovingPlatform().get());
    }

    @Test
    public void removeFirstOnTouchPlatformTest() {
        Platform platform = createOnToucPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addOnTouchPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstOnTouchPlatform().get());
    }

    @Test
    public void removeFirstMonsterTest() {
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMonster(monster);
        assertEquals(monster, this.upperWorld.removeFirstMonster().get());
    }

    @Test
    public void removeFirstGadgetTest() {
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addGadget(gadget);
        assertEquals(gadget, this.upperWorld.removeFirstGadget().get());
    }

    @Test
    public void removeFirstCoinTest() {
        Coin coin = new CoinImpl(0, 0, new Vector2dImpl(POS_X, POS_Y), null);
        this.upperWorld.addMoney(coin);
        assertEquals(coin, this.upperWorld.removeFirstMoney().get());
    }

    /*
     * @Test
     * public void removeFirstTrapTest() {
     * setUpUpperWorld();
     * Trap trap = new Trap(new Vector2dImpl(0, 0));
     * this.upperWorld.addTrap(trap);
     * assertEquals(trap, this.upperWorld.removeFirstTrap().get());
     * }
     */

    @Test
    public void getStaticPlatformsTest() {
        Platform platform = createStaticPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addStaticPlatform(platform);
        assertEquals(1, this.upperWorld.getStaticPlatforms().size());
    }

    @Test
    public void getMovingPlatformsTest() {
        Platform platform = createMovingPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMovingPlatform(platform);
        assertEquals(1, this.upperWorld.getMovingPlatforms().size());
    }

    @Test
    public void getOnTouchPlatformsTest() {
        Platform platform = createOnToucPlatform(new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addOnTouchPlatform(platform);
        assertEquals(1, this.upperWorld.getOnTouchPlatforms().size());
    }

    @Test
    public void getMonstersTest() {
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addMonster(monster);
        assertEquals(1, this.upperWorld.getMonsters().size());
    }

    @Test
    public void getGadgetsTest() {
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(POS_X, POS_Y));
        this.upperWorld.addGadget(gadget);
        assertEquals(1, this.upperWorld.getGadgets().size());
    }

    @Test
    public void getCoinsTest() {
        Coin coin = new CoinImpl(0, 0, new Vector2dImpl(POS_X, POS_Y), null);
        this.upperWorld.addMoney(coin);
        assertEquals(1, this.upperWorld.getMoneys().size());
    }

    /*
     * @Test
     * public void getTrapsTest() {
     * setUpUpperWorld();
     * Trap trap = new Trap(new Vector2dImpl(0, 0));
     * this.upperWorld.addTrap(trap);
     * assertEquals(1, this.upperWorld.getTraps().size());
     * }
     */
}