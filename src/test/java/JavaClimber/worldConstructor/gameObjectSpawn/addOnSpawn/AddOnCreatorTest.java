package JavaClimber.worldConstructor.gameObjectSpawn.addOnSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;

public class AddOnCreatorTest {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 800;
    private static final double X_MIN = 0;
    private static final double X_MAX = 400;

    private static final double PLATFORM_WIDTH = 20;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double POS_X = 200;
    private static final double POS_Y = 700;

    private static final double CHANCE_ADDON = 0.3;
    
    private Random random;
    private QueueWorld world;
    private SpawnPoolCreator spawnPoolCreator;
    private AddOnCreator addOnCreator;

    @BeforeEach
    public void setUp(){
        this.random = new Random();
        this.world = new UpperWorld(new BoundWorldImpl(new BoundY(Y_MIN, Y_MAX), new Boundary(X_MIN, X_MAX)));
        this.spawnPoolCreator = new SpawnPoolCreatorImpl(world);
        this.spawnPoolCreator.setSpawnPool(new SpawnPoolEasy(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        this.addOnCreator = new AddOnCreatorImpl(new AddOnPoolEasy(this.spawnPoolCreator, CHANCE_ADDON));
    }

    @Test
    public void createAddOnTest(){
        var pos = new Vector2dImpl(POS_X, POS_Y);
        this.addOnCreator.selectAddOn(random.nextDouble(1), pos);
        if(this.world.getGadgets().size() > 0){
            var gadget = this.world.removeFirstGadget().get();
            assertEquals(POS_X + ((PLATFORM_WIDTH - gadget.getWidth()) / 2), gadget.getPosX());
            assertEquals(POS_Y + gadget.getHeight() , gadget.getPosY());
        } else if(this.world.getMoneys().size() > 0){
            var money = this.world.removeFirstMoney().get();
            assertEquals(POS_X + ((PLATFORM_WIDTH - money.getWidth()) / 2), money.getPosX());
            assertEquals(POS_Y + money.getHeight() , money.getPosY());
        } else {
            var monster = this.world.removeFirstMonster().get();
            assertEquals(POS_X + ((PLATFORM_WIDTH - monster.getWidth()) / 2), monster.getPosX());
            assertEquals(POS_Y + monster.getHeight() , monster.getPosY());
        }
    }

}
