package javaclimber.phisics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.model.LaunchedGame.impl.LaunchedGameImpl;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.gameobj.impl.CoinImpl;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.alienphysic.impl.AlienEliCapPhysic;
import it.unibo.model.physics.alienphysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.GameObjDimension;
import it.unibo.view.MainViewImpl;

/**
 * <p>Test class for {@link AlienEliCapPhysic}.</p>
 */
class AlienEliCapPhysicTest {

    private static final double EPSILON = 0.001;

    private static final double X = 10;
    private static final double Y = 100;

    private static final double X1 = 10;
    private static final double Y1 = -400;

    private static final double X2 = 10;

    private static final double X3 = 10;
    
    private static final double WIDTH = 0;
    private static final double HEIGHT = 0;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 0;

    private static final double ELICAP_SPEED_X = 0;
    private static final double ELICAP_SPEED_Y = -500;

    private static final double SPEED_AFTER_ELICAP_AND_GRAVITY_X = 0;

    private static final double LEFT_SIDE = 0;
    private static final double RIGHT_SIDE = 100;

    private static final double UPPER_WORLD = 0;
    private static final double LOWER_WORLD = 100;


    private static final double DT = 1;
    private static final double DT2 = 3;
    private static final double DT3 = 4;

    private static final int COINS_NUMBER = 1;

    /**
     * <p>Verify that AlienEliCapPhysic is setted correctly when EliCap is collected through {@link Gadget#onCollect(Alien)} and wheter his application works as expected 
     * through {@link Alien#updatePosition(double, Boundary)} and {@link AlienPhysic#update(Alien, double, Boundary)}.</p>
     */
    @Test
    public void testAlienEliCapPhysicBehavior() {
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        //velocita del mostra quando prende elicap = 10
        eliCap.onCollect(alien, new RealWorld(alien, boundary));

        assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);
        assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);

        alien.updatePosition(DT, boundary, new LaunchedGameImpl(new MainControllerImpl(new MainViewImpl()), new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
        assertEquals(X1, alien.getPosX(), EPSILON);
        assertEquals(Y1, alien.getPosY(), EPSILON);
        assertEquals(ELICAP_SPEED_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y, alien.getSpeedY(), EPSILON);
    }

    /**
     * <p>Test to verify if an update equals to the time interval of the EliCap gadget correctly update the alien position and speed.</p>
     */
    @Test
    public void testAlienEliCapPhysicBehaviorUpdatingWithTimeInterval() {
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        eliCap.onCollect(alien, new RealWorld(alien, boundary));

        //before updating position the physic shouldn't apply modifications on the alien speed and position
        assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);  
        assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);

        alien.updatePosition(DT2, boundary, new LaunchedGameImpl(new MainControllerImpl(new MainViewImpl()), new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
        
        //after updating position
        assertEquals(X2, alien.getPosX(), EPSILON);
        assertEquals((Y +ELICAP_SPEED_Y*DT2), alien.getPosY(), EPSILON);
        assertEquals(ELICAP_SPEED_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y, alien.getSpeedY(), EPSILON);
    }

    /**
     * <p>Test to verify if an update greater than the time interval of the EliCap gadget correctly update the alien position and speed, and if the alien physic is setted to {@link AlienNormalPhysic} after the time interval.</p>
     */
    @Test
    public void testAlienEliCapPhysicBehaviorUpdatingWithMoreThanTimeInterval() {
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        
        eliCap.onCollect(alien, new RealWorld(alien, boundary));
        alien.updatePosition(DT3, boundary, new LaunchedGameImpl(new MainControllerImpl(new MainViewImpl()), new MenuImpl(new MainControllerImpl(new MainViewImpl()))));

        assertEquals(X3, alien.getPosX(), EPSILON);
        assertEquals((Y + ELICAP_SPEED_Y*DT2) + ((ELICAP_SPEED_Y + (GameObjDimension.GRAVITY * (DT3 - DT2))) * (DT3 - DT2)), alien.getPosY(), EPSILON);
        assertEquals(SPEED_AFTER_ELICAP_AND_GRAVITY_X, alien.getSpeedX(), EPSILON);
        assertEquals(ELICAP_SPEED_Y + GameObjDimension.GRAVITY * (DT3 - DT2), alien.getSpeedY(), EPSILON);
    }

    /**
    * <p>Tests the {@link AlienEliCapPhysic#hitCoin(Coin, ActiveUpgrades, GameWorld)} method. Verify wheter, after the touch, is updated the number of coin collected.</p>
    */
    @Test
    public void testHitCoin() {
        final ScoreManager scoreManager = new ScoreManagerImpl();
        final Coin coin = new CoinImpl(HEIGHT, WIDTH, new Vector2dImpl(X, Y + HEIGHT), scoreManager);
        final Gadget eliCap = new EliCap(HEIGHT, WIDTH, new Vector2dImpl(X, Y));
        final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        final BoundWorld boundary = new BoundWorldImpl(new BoundY(UPPER_WORLD, LOWER_WORLD), new Boundary(LEFT_SIDE, RIGHT_SIDE));
        
        eliCap.onCollect(alien, new RealWorld(alien, boundary));

        alien.notifyCollision(coin, boundary.getBoundX(), new RealWorld(alien, boundary), new LaunchedGameImpl(new MainControllerImpl(new MainViewImpl()), new MenuImpl(new MainControllerImpl(new MainViewImpl()))));
    
        assertEquals(COINS_NUMBER, scoreManager.getCoins(), EPSILON);
  }
}
