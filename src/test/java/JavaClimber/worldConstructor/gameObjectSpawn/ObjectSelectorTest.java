package JavaClimber.worldConstructor.gameObjectSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.api.GameObject;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.ObjectSelector;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.ObjectSelectorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

public class ObjectSelectorTest {

    private ObjectSelector objectSelector;
    private SpawnPool spawnPool;
    private Random random;
    
    private static final double PLAFORM_WIDTH = 30;
    private static final double PLAFORM_HEIGHT = 10;

    private static final double POS_X = 200;
    private static final double POS_Y = 780;
    
    @BeforeEach
    public void setUp() {
        this.random = new Random();
        this.spawnPool = new SpawnPoolEasy(PLAFORM_WIDTH, PLAFORM_HEIGHT);
        this.objectSelector = new ObjectSelectorImpl();
    }

    @Test
    public void testSelector() {
        var chance = this.random.nextDouble();
        var pos = new Vector2dImpl(POS_X, POS_Y);
        var pool = this.spawnPool.getGadgetPool();
        GameObject gameObject = this.objectSelector.selector(chance, pos, pool).get();
        assertEquals(POS_X, gameObject.getPosX());
        assertEquals(POS_Y, gameObject.getPosY());
    }

}
