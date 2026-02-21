package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldConstructor.impl.Difficult;
import it.unibo.model.worldConstructor.impl.PlatformPoolEasy;
import it.unibo.model.worldConstructor.impl.WorldConstructorImpl;

public class WorldConstructorTest {

    private WorldConstructorImpl worldConstructor;
    private World world;
    private Boundary boundX;
    private BoundY boundY;
    private UpperWorld upperWorld;
    private Difficult difficult;

    @BeforeEach
    public void setUp() {
        this.upperWorld = new UpperWorld();
        this.world = new World(upperWorld, null);
        this.difficult = new Difficult(0, 70, 30, 20, 0.2, 0.4, 4, 0.8, 4, 1.0, 4, 0, 0,
                new PlatformPoolEasy(10, 10, 5, 5, 10, 10, 5, 5));
        this.boundX = new Boundary(0, 400);
        this.boundY = new BoundY(0, 800);
        worldConstructor = new WorldConstructorImpl(this.difficult, this.boundX, this.boundY, this.upperWorld);
    }

    @Test
    public void fillWorldTest() {
        worldConstructor.fillWorld();
        assertEquals(false, world.getUpperWorld().getPlatforms().isEmpty());
        var lastPlatform = world.getUpperWorld().getPlatforms().get(world.getUpperWorld().getPlatforms().size() - 1);
        assertEquals(true, lastPlatform.getPosY() < boundY.minY() + difficult.maxDistanceY()
                && lastPlatform.getPosY() > boundY.minY()); 
    }
}
