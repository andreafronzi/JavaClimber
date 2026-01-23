package JavaClimber.score;

import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link ScoreManagerImpl} class.
 */
public class ScoreManagerTest {

    private static final int INITIAL_HIGH_SCORE = 100;
    private ScoreManager scoreManager;

    /** 
     * Sets up a new ScoreManager instance before each test.
     */
    @BeforeEach
    void setUp() {
        scoreManager = new ScoreManagerImpl(INITIAL_HIGH_SCORE);
    }

    /** 
     * Tests initial values of the ScoreManager.
     */
    @Test
    void testInitialValues() {
        assertEquals(0, scoreManager.getCurrentScore());
        assertEquals(0, scoreManager.getCoins());
        assertEquals(INITIAL_HIGH_SCORE, scoreManager.getHighScore());
        assertFalse(scoreManager.isNewHighScore());
    }

    /** 
     * Tests the {@link ScoreManager#updateScore(double)} method.
     */
    @Test
    void testUpdateScore() {
        scoreManager.updateScore(50.0);
        assertEquals(50, scoreManager.getCurrentScore());
        
        scoreManager.updateScore(30.0);
        assertEquals(50, scoreManager.getCurrentScore());
        
        scoreManager.updateScore(60.0);
        assertEquals(60, scoreManager.getCurrentScore());
    }

    /** 
     * Tests the {@link ScoreManager#addCoins(int)} and {@link ScoreManager#getCoins()} methods.
     */
    @Test
    void testCoins() {
        scoreManager.addCoins(10);
        assertEquals(10, scoreManager.getCoins());
        scoreManager.addCoins(5);
        assertEquals(15, scoreManager.getCoins());
        //
        scoreManager.addCoins(-10);
        assertEquals(15, scoreManager.getCoins());
    }

    /** 
     * Tests the {@link ScoreManager#getHighScore()} method.
     */
    @Test
    void testHighScoreLogic() {
        scoreManager.updateScore(50.0);
        assertFalse(scoreManager.isNewHighScore());
        assertEquals(100, scoreManager.getHighScore());

        scoreManager.updateScore(150.0);
        assertTrue(scoreManager.isNewHighScore());
        assertEquals(150, scoreManager.getHighScore());

        scoreManager.updateScore(120.0);
        assertTrue(scoreManager.isNewHighScore());
        assertEquals(150, scoreManager.getCurrentScore());
    }

    /** 
     * Tests the {@link ScoreManager#reset()} method.
     */
    @Test
    void testReset() {
        scoreManager.updateScore(50.0);
        scoreManager.addCoins(10);
        scoreManager.reset();
        
        assertEquals(0, scoreManager.getCurrentScore());
        assertEquals(0, scoreManager.getCoins());
        // L'high score deve persistere anche dopo il reset
        assertEquals(INITIAL_HIGH_SCORE, scoreManager.getHighScore());
    }

    /** 
     * Tests high score update after reset.
     */
    @Test
    void testHighScoreAfterReset() {
        scoreManager.updateScore(150.0);
        scoreManager.reset();
        
        assertEquals(150, scoreManager.getHighScore());
        assertEquals(0, scoreManager.getCurrentScore());
        assertFalse(scoreManager.isNewHighScore());
    }
}
