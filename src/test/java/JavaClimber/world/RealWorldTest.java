package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.EnemyImpl;
import it.unibo.model.gameObj.impl.CoinImpl;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.world.impl.RealWorld;

public class RealWorldTest {

    private RealWorld realWorld;

    private RealWorld setUpRealWorld(Vector2d pos, Vector2d velocity, int width, int height) {
        this.realWorld = new RealWorld(new AlienImpl(pos, velocity, width, height, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl())));
        return this.realWorld;  
    }

    @Test
    public void getAlienTest() {
        double x = 0;
        double y = 0;
        this.realWorld = setUpRealWorld(new Vector2dImpl(x, y), new Vector2dImpl(0, 0), 10, 10);
        Alien alien = this.realWorld.getAlien();
        assertEquals(alien, this.realWorld.getAlien());
        assertEquals(x, alien.getPosX());
        assertEquals(y, alien.getPosY());
    }

    private Platform createPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).build();
        return platform;
    }

    @Test
    public void removePlatformTest() {
        Platform platform = createPlatform(new Vector2dImpl(0, 0));
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addPlatform(platform);
        assertEquals(true, this.realWorld.removePlatform(platform));
    }

    @Test
    public void removeMonsterTest() {
        Enemy monster = new EnemyImpl(0, 0, new Vector2dImpl(0, 0));
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addMonster(monster);
        assertEquals(true, this.realWorld.removeMonster(monster));
    }

    @Test
    public void removeGadgetTest() {
        Gadget gadget = new EliCap(0, 0, new Vector2dImpl(0, 0));
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addGadget(gadget);
        assertEquals(true, this.realWorld.removeGadget(gadget));
    }

    @Test
    public void removeMoneyTest() {
        Coin money = new CoinImpl(0, 0, null, null);
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addMoney(money);
        assertEquals(true, this.realWorld.removeMoney(money));
    }
}
