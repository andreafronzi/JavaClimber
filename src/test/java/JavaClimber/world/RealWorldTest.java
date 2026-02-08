package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.Player;
import it.unibo.model.world.impl.RealWorld;

public class RealWorldTest {
    
    private RealWorld realWorld;

    @Test
    public void getAlienTest(){
        Player player = new Player(new Vector2dImpl(10,15));
        this.realWorld = new RealWorld(player);
        assertEquals(player, this.realWorld.getAlien());
        assertEquals(10, player.getPosX());
        assertEquals(15, player.getPosY());
    }
}
