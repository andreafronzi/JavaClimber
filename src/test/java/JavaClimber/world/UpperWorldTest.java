package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.*;

public class UpperWorldTest {

    private UpperWorld upperWorld;

    private void setUpUpperWorld() {
        this.upperWorld = new UpperWorld();
    }

    @Test
    public void addPlatformTest() {
        setUpUpperWorld();
        Platform platform = new PlatformImpl(new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addPlatform(platform));
    }

    @Test
    public void addMonsterTest() {
        setUpUpperWorld();
        Monster monster = new Monster(new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addMonster(monster));
    }

    @Test
    public void addGadgetTest() {
        setUpUpperWorld();
        Gadget gadget = new Gadget(new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addGadget(gadget));
    }

    @Test
    public void addMoneyTest() {
        setUpUpperWorld();
        Money money = new Money(new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addMoney(money));
    }

    @Test
    public void addTrapTest() {
        setUpUpperWorld();
        Trap trap = new Trap(new Vector2dImpl(0, 0));
        assertEquals(true, this.upperWorld.addTrap(trap));
    }

    @Test
    public void removePlatformTest() {
        setUpUpperWorld();
        Platform platform = new PlatformImpl(new Vector2dImpl(0, 0));
        this.upperWorld.addPlatform(platform);
        assertEquals(platform, this.upperWorld.removePlatform().get());
    }

    @Test
    public void removeMonsterTest() {
        setUpUpperWorld();
        Monster monster = new Monster(new Vector2dImpl(0, 0));
        this.upperWorld.addMonster(monster);
        assertEquals(monster, this.upperWorld.removeMonster().get());
    }

    @Test
    public void removeGadgetTest() {
        setUpUpperWorld();
        Gadget gadget = new Gadget(new Vector2dImpl(0, 0));
        this.upperWorld.addGadget(gadget);
        assertEquals(gadget, this.upperWorld.removeGadget().get());
    }

    @Test
    public void removeMoneyTest() {
        setUpUpperWorld();
        Money money = new Money(new Vector2dImpl(0, 0));
        this.upperWorld.addMoney(money);
        assertEquals(money, this.upperWorld.removeMoney().get());
    }

    @Test
    public void removeTrapTest() {
        setUpUpperWorld();
        Trap trap = new Trap(new Vector2dImpl(0, 0));
        this.upperWorld.addTrap(trap);
        assertEquals(trap, this.upperWorld.removeTrap().get());
    }

    @Test
    public void removePlatformEmptyTest() {
        setUpUpperWorld();
        assertEquals(true, this.upperWorld.removePlatform().isEmpty());
    }

    @Test
    public void removeMonsterEmptyTest() {
        setUpUpperWorld();
        assertEquals(true, this.upperWorld.removeMonster().isEmpty());
    }

    @Test
    public void removeGadgetEmptyTest() {
        setUpUpperWorld();
        assertEquals(true, this.upperWorld.removeGadget().isEmpty());
    }

    @Test
    public void removeMoneyEmptyTest() {
        setUpUpperWorld();
        assertEquals(true, this.upperWorld.removeMoney().isEmpty());
    }

    @Test
    public void removeTrapEmptyTest() {
        setUpUpperWorld();
        assertEquals(true, this.upperWorld.removeTrap().isEmpty());
    }

    @Test
    public void getPlatformsTest() {
        setUpUpperWorld();
        Platform platform = new PlatformImpl(new Vector2dImpl(0, 0));
        this.upperWorld.addPlatform(platform);
        assertEquals(1, this.upperWorld.getPlatforms().size());
    }

    @Test
    public void getMonstersTest() {
        setUpUpperWorld();
        Monster monster = new Monster(new Vector2dImpl(0, 0));
        this.upperWorld.addMonster(monster);
        assertEquals(1, this.upperWorld.getMonsters().size());
    }

    @Test
    public void getGadgetsTest() {
        setUpUpperWorld();
        Gadget gadget = new Gadget(new Vector2dImpl(0, 0)); 
        this.upperWorld.addGadget(gadget);
        assertEquals(1, this.upperWorld.getGadgets().size());
    }

    @Test
    public void getMoneysTest() {
        setUpUpperWorld();
        Money money = new Money(new Vector2dImpl(0, 0));
        this.upperWorld.addMoney(money);
        assertEquals(1, this.upperWorld.getMoneys().size());    
    }

    @Test
    public void getTrapsTest() {
        setUpUpperWorld();
        Trap trap = new Trap(new Vector2dImpl(0, 0));
        this.upperWorld.addTrap(trap);
        assertEquals(1, this.upperWorld.getTraps().size());
    }
}   