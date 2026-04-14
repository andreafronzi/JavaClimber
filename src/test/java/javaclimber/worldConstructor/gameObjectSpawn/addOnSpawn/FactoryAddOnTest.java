package javaclimber.worldConstructor.gameObjectSpawn.addOnSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.FactoryAddOn;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.FactoryAddOnImpl;

/**
 * Test for the FactoryAddOnImpl class.
 */
public class FactoryAddOnTest {

    private FactoryAddOn factoryAddOn;
    private final static double POSITION_X = 0;
    private final static double POSITION_Y = 0;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        factoryAddOn = new FactoryAddOnImpl(new ScoreManagerImpl());
    }

    /**
     * Test for creating a coin.
     */
    @Test
    public void createCoinTest() {
        Coin coin = this.factoryAddOn.createCoin(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(coin, coin);
    }

    /**
     * Test for creating an enemy.
     */
    @Test
    public void createEnemyTest() {
        Enemy enemy = this.factoryAddOn.createEnemy(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(enemy, enemy);
    }

    /**
     * Test for creating an EliCap gadget.
     */
    @Test
    public void createElycapTest() {
        Gadget elycap = this.factoryAddOn.createElycap(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(elycap, elycap);
    }
}
