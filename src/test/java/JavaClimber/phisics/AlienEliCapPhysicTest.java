package JavaClimber.phisics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.impl.AlienEliCapPhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

/**
 * Test class for {@link AlienEliCapPhysic}.
 */
public class AlienEliCapPhysicTest {

    private static final double EPSILON = 0.001;

    private static final double X = 10;
    private static final double Y = 100;

    private static final double X1 = 10;
    private static final double Y1 = 90;

    private static final double X2 = 10;
    private static final double Y2 = 80;

    private static final double X3 = 10;
    private static final double Y3 = 120;
    
    private static final double WIDTH = 0;
    private static final double HEIGHT = 0;

    private static final double LEFT_BOUNDARY = 0;
    private static final double RIGHT_BOUNDARY = 100;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 0;

    private static final double ELICAP_SPEED_X = 0;
    private static final double ELICAP_SPEED_Y = -10;

    private static final double SPEED_AFTER_ELICAP_AND_GRAVITY_X = 0;
    private static final double SPEED_AFTER_ELICAP_AND_GRAVITY_Y = 40;

    private static final double LEFT_SIDE = 0;
    private static final double RIGHT_SIDE = 100;

    private static final double DT = 1;
    private static final double DT2 = 2;
    private static final double DT3 = 3;



    /**
     * Verify that AlienEliCapPhysic is setted correctly when EliCap is collected through {@link Gadget#onCollect(Alien)} and wheter his application works as expected 
     * through {@link Alien#updatePosition(double, Boundary)} and {@link AlienPhysic#update(Alien, double, Boundary)}.
     */
    @Test
    public void testAlienEliCapPhysicBehavior() {
        final Boundary boundary = new Boundary(LEFT_SIDE, RIGHT_SIDE);
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        //velocita del mostra quando prende elicap = 10
        eliCap.onCollect(alien);

        assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);
        assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);

        alien.updatePosition(DT, boundary);
        assertEquals(X1, alien.getPosX(), EPSILON);
        assertEquals(Y1, alien.getPosY(), EPSILON);
        assertEquals(ELICAP_SPEED_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y, alien.getSpeedY(), EPSILON);
    }

    /**
     * Test to verify if an update equals to the time interval of the EliCap gadget correctly update the alien position and speed.
     */
    @Test
    public void testAlienEliCapPhysicBehaviorUpdatingWithTimeInterval() {
        final Boundary boundary = new Boundary(LEFT_SIDE, RIGHT_SIDE);
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        eliCap.onCollect(alien);

        assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);  
        assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);

        alien.updatePosition(DT2, boundary);
        assertEquals(X2, alien.getPosX(), EPSILON);
        assertEquals(Y2, alien.getPosY(), EPSILON);
        assertEquals(ELICAP_SPEED_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y, alien.getSpeedY(), EPSILON);
    }

    /**
     * Test to verify if an update greater than the time interval of the EliCap gadget correctly update the alien position and speed, and if the alien physic is setted to AlienNormalPhysic after the time interval.
     */
    @Test
    public void testAlienEliCapPhysicBehaviorUpdatingWithMoreThanTimeInterval() {
        final Boundary boundary = new Boundary(LEFT_SIDE, RIGHT_SIDE);
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()), new ShopItemFactoryImpl()));
        eliCap.onCollect(alien);
        alien.updatePosition(DT3, boundary);

        assertEquals(X3, alien.getPosX(), EPSILON);
        assertEquals(Y3, alien.getPosY(), EPSILON);
        assertEquals(SPEED_AFTER_ELICAP_AND_GRAVITY_X, alien.getSpeedX(), EPSILON);
        assertEquals(SPEED_AFTER_ELICAP_AND_GRAVITY_Y, alien.getSpeedY(), EPSILON);
    }
}
