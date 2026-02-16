package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.gameObj.impl.CoinImpl;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.gameObj.impl.EnemyImpl;


public class QueueWorldTest {

    private UpperWorld upperWorld;

    private void setUpUpperWorld() {
        this.upperWorld = new UpperWorld();
    }

    private Platform createPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).build();
        return platform;
    }

    @Test
    public void addPlatformTest() {
        setUpUpperWorld();
        Platform platform = createPlatform(new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addPlatform(platform));
    }

    @Test
    public void addMonsterTest() {
        setUpUpperWorld();
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addMonster(monster));
    }

    @Test
    public void addGadgetTest() {
        setUpUpperWorld();
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addGadget(gadget));
    }

    @Test
    public void addMoneyTest() {
        setUpUpperWorld();
        Coin money = new CoinImpl(0, 0, null, null);
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
    public void removeFirstPlatformTest() {
        setUpUpperWorld();
        Platform platform = createPlatform(new Vector2dImpl(0, 0));
        this.upperWorld.addPlatform(platform);
        assertEquals(platform, this.upperWorld.removeFirstPlatform().get());
    }

    @Test
    public void removeFirstMonsterTest() {
        setUpUpperWorld();
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(0, 0));
        this.upperWorld.addMonster(monster);
        assertEquals(monster, this.upperWorld.removeFirstMonster().get());
    }

    @Test
    public void removeFirstGadgetTest() {
        setUpUpperWorld();
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(0, 0));
        this.upperWorld.addGadget(gadget);
        assertEquals(gadget, this.upperWorld.removeFirstGadget().get());
    }

    @Test
    public void removeFirstCoinTest() {
        setUpUpperWorld();
        Coin coin = new CoinImpl(0, 0, null, null);
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
    public void getPlatformsTest() {
        setUpUpperWorld();
        Platform platform = createPlatform(new Vector2dImpl(0, 0));
        this.upperWorld.addPlatform(platform);
        assertEquals(1, this.upperWorld.getPlatforms().size());
    }

    @Test
    public void getMonstersTest() {
        setUpUpperWorld();
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(0, 0));
        this.upperWorld.addMonster(monster);
        assertEquals(1, this.upperWorld.getMonsters().size());
    }

    @Test
    public void getGadgetsTest() {
        setUpUpperWorld();
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(0, 0));
        this.upperWorld.addGadget(gadget);
        assertEquals(1, this.upperWorld.getGadgets().size());
    }

    @Test
    public void getCoinsTest() {
        setUpUpperWorld();
        Coin coin = new CoinImpl(0, 0, null, null);
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