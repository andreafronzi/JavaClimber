package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.Player;
import it.unibo.model.world.impl.*;
import it.unibo.model.world.impl.World;

public class WorldTest {

    private World world;

    private void setUpWorld(final UpperWorld upperWorld, final RealWorld realWorld) {
        this.world = new World(upperWorld, realWorld);
    }

    @Test
    public void getUpperWorldTest() {
        Player player = new Player(new Vector2dImpl(0, 0));
        UpperWorld upperWorld = new UpperWorld();
        RealWorld realWorld = new RealWorld(player);
        setUpWorld(upperWorld, realWorld);
        assertEquals(upperWorld, this.world.getUpperWorld());
    }

    @Test
    public void getRealWorldTest() {
        Player player = new Player(new Vector2dImpl(0, 0));
        UpperWorld upperWorld = new UpperWorld();
        RealWorld realWorld = new RealWorld(player);
        setUpWorld(upperWorld, realWorld);
        assertEquals(realWorld, this.world.getRealWorld());
    }
}