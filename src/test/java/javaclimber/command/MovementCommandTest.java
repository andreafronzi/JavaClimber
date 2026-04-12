package javaclimber.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.LaunchedGame.impl.LaunchedGameImpl;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.command.impl.StopAlienMovement;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

/**
 * <p>Test class for the {@link RunningCommand} implementations.</p>
 */
class MovementCommandTest {
    
    private static final double X = 10;
    private static final double Y = 20;
    
    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 0;

    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;
    
    /**
     * <p>Test the {@link MoveAlienLeft} command by executing it on an {@link AlienImpl} and checking if the speedX is updated correctly.</p>
     */
    @Test
    public void moveAlienLeftTest() {
        final RunningCommand moveLeft = new MoveAlienLeft();
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        moveLeft.execute(alien, new LaunchedGameImpl(null));
        
        assertTrue(alien.isMovingLeft());
    }

    /**
     * <p>Test the {@link MoveAlienRight} command by executing it on an {@link AlienImpl} and checking if the speedX is updated correctly.</p>
     */
    @Test
    public void moveAlienRightTest() {
        final RunningCommand moveRight = new MoveAlienRight();
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        moveRight.execute(alien, new LaunchedGameImpl(null));
        
        assertTrue(alien.isMovingRight());
    }

    /**
     * <p>Test the {@link StopAlienMovement} command by executing it on an {@link AlienImpl} and checking if the speedX is updated correctly.</p>
     */
    @Test
    public void stopAlien() {
        final RunningCommand stop = new StopAlienMovement();
        final AlienImpl alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT, new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
        stop.execute(alien, new LaunchedGameImpl(null));
        
        assertFalse(alien.isMovingLeft() || alien.isMovingRight());
    }
}
