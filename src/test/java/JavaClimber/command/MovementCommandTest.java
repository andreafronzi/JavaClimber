package JavaClimber.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.LaunchedGame.impl.LaunchedGameImpl;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.command.impl.StopAlienMovement;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

/**
 * Test class for the {@link RunningCommand} implementations.
 */
public class MovementCommandTest {

    private static final double EPSILON = 0.001;
    
    private static final double X = 10;
    private static final double Y = 20;
    
    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 0;

    private static final double NULL_SPEED_X = 0;
    private static final double SPEED_X_LEFT_AFTER_COMMAND = -10;
    private static final double SPEED_X_RIGHT_AFTER_COMMAND = 10;

    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;
    
    /**
     * Test the {@link MoveAlienLeft} command by executing it on an {@link AlienImpl} and checking if the speedX is updated correctly.
     */
    @Test
    public void moveAlienLeftTest() {
        final RunningCommand moveLeft = new MoveAlienLeft();
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        moveLeft.execute(alien, new LaunchedGameImpl(null, null));
        
        assertTrue(alien.isMovingLeft());
    }

    /**
     * Test the {@link MoveAlienRight} command by executing it on an {@link AlienImpl} and checking if the speedX is updated correctly.
     */
    @Test
    public void moveAlienRightTest() {
        final RunningCommand moveRight = new MoveAlienRight();
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        moveRight.execute(alien, new LaunchedGameImpl(null, null));
        
        assertTrue(alien.isMovingRight());
    }

    /**
     * Test the {@link StopAlienMovement} command by executing it on an {@link AlienImpl} and checking if the speedX is updated correctly.
     */
    @Test
    public void stopAlien() {
        final RunningCommand stop = new StopAlienMovement();
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        stop.execute(alien, new LaunchedGameImpl(null, null));
        
        assertFalse(alien.isMovingLeft() || alien.isMovingRight());
    }
}
