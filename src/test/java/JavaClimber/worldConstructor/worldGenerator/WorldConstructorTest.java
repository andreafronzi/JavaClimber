package JavaClimber.worldConstructor.worldGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;
import it.unibo.model.worldConstructor.worldGenerator.impl.WorldConstructorImpl;

public class WorldConstructorTest {

    private WorldConstructorImpl worldConstructor;
    private World world;
    private Boundary boundX;
    private BoundY boundY;
    private UpperWorld upperWorld;
    private Difficult difficult;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void fillWorldTest() {
    }
}
