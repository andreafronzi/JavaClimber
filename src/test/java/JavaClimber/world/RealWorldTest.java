package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.world.impl.RealWorld;

public class RealWorldTest {
    
    private RealWorld realWorld;

    @Test
    public void getAlienTest(){
        AlienImpl alien = new AlienImpl(new Vector2dImpl(10, 15), new Vector2dImpl(0,0), 10, 10);
        this.realWorld = new RealWorld(alien);
        assertEquals(alien, this.realWorld.getAlien());
        assertEquals(10, alien.getPosX());
        assertEquals(15, alien.getPosY());
    }
}
