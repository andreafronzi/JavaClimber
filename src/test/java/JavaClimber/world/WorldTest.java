package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.*;

public class WorldTest {

    private World world;

    private void setUpWorld(final QueueWorld upperWorld, final GameWorld realWorld) {
        this.world = new World(upperWorld, realWorld);
    }

    @Test
    public void getUpperWorldTest() {
        BoundWorld boundWorld = new BoundWorldImpl(new BoundY(0, 800), new Boundary(0, 600));
        AlienImpl alien = new AlienImpl(new Vector2dImpl(0, 0), new Vector2dImpl(0,0), 10, 10, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        QueueWorld upperWorld = new UpperWorld(boundWorld);
        GameWorld realWorld = new RealWorld(alien, boundWorld);
        setUpWorld(upperWorld, realWorld);
        assertEquals(upperWorld, this.world.getUpperWorld());
    }

    @Test
    public void getRealWorldTest() {
        BoundWorld boundWorld = new BoundWorldImpl(new BoundY(0, 800), new Boundary(0, 600));
        AlienImpl alien = new AlienImpl(new Vector2dImpl(0, 0), new Vector2dImpl(0,0), 10, 10, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        UpperWorld upperWorld = new UpperWorld(boundWorld);
        RealWorld realWorld = new RealWorld(alien, boundWorld);
        setUpWorld(upperWorld, realWorld);
        assertEquals(realWorld, this.world.getRealWorld());
    }
}