package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.api.FactoryAddOn;
import it.unibo.model.worldConstructor.impl.FactoryAddOnImpl;

public class FactoryAddOnTest {

    private FactoryAddOn factoryAddOn;
    private final static double COIN_HEIGHT = 0.5;
    private final static double COIN_WIDTH = 0.5;
    private final static double ELYCAP_HEIGHT = 0.5;
    private final static double ELYCAP_WIDTH = 0.5;
    private final static double ENEMY_HEIGHT = 0.5;
    private final static double ENEMY_WIDTH = 0.5;
    private final static double POSITION_X = 0;
    private final static double POSITION_Y = 0;

    @BeforeEach
    void setUp() {
        factoryAddOn = new FactoryAddOnImpl(
                COIN_WIDTH,
                COIN_HEIGHT,
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                ELYCAP_WIDTH,
                ELYCAP_HEIGHT);
    }

    @Test
    public void createCoinTest() {
        Coin coin = this.factoryAddOn.createCoin(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(coin, coin);
    }

    @Test
    public void createEnemyTest() {
        Enemy enemy = this.factoryAddOn.createEnemy(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(enemy, enemy);
    }

    @Test
    public void createElycapTest() {
        Gadget elycap = this.factoryAddOn.createElycap(new Vector2dImpl(POSITION_X, POSITION_Y));
        assertEquals(elycap, elycap);
    }
}
