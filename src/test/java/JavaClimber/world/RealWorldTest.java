package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.world.impl.RealWorld;

public class RealWorldTest {

    private RealWorld realWorld;

    private RealWorld setUpRealWorld(Vector2d pos, Vector2d velocity, int width, int height) {
        this.realWorld = new RealWorld(new AlienImpl(pos, velocity, width, height));
        return this.realWorld;  
    }

    @Test
    public void getAlienTest() {
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        Alien alien = this.realWorld.getAlien();
        assertEquals(alien, this.realWorld.getAlien());
        assertEquals(10, alien.getPosX());
        assertEquals(15, alien.getPosY());
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

    public void removeMonsterTest() {
        Enemy monster = new Enemy(new Vector2dImpl(0, 0));
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addPlatform(monster);
        assertEquals(true, this.realWorld.removePlatform(monster));
    }

    public void removeGadgetTest() {
        Gadget gadget = new Gadget(new Vector2dImpl(0, 0));
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addPlatform(gadget);
        assertEquals(true, this.realWorld.removePlatform(gadget));
    }

    public void removeMoneyTest() {
        Coin money = new Coin(new Vector2dImpl(0, 0));
        this.realWorld = setUpRealWorld(new Vector2dImpl(0, 0), new Vector2dImpl(0, 0), 10, 10);
        this.realWorld.addPlatform(money);
        assertEquals(true, this.realWorld.removePlatform(money));
    }
}
