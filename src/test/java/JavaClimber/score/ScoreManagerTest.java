package JavaClimber.score;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

/**
 * Tests for the {@link ScoreManagerImpl} class.
 */
public class ScoreManagerTest {

    private static final int INITIAL_COINS = 100;
    private ScoreManager scoreManager;

    /**
     * Sets up a new ScoreManager instance before each test.
     */
    @BeforeEach
    void setUp() {
        scoreManager = new ScoreManagerImpl(INITIAL_COINS);
    }

    /**
     * Tests initial values of the ScoreManager and loads from the file.
     */
    @Test
    void testInitialValuesAndLoad() {
        SaveState state = new SaveState(1500, 5000, Set.of(), Map.of(), "skin_basic");
        scoreManager.loadState(state);

        assertEquals(0, scoreManager.getCurrentScore());
        assertEquals(1500, scoreManager.getCoins());
        assertEquals(5000, scoreManager.getHighScore());
    }

    /**
     * Tests the {@link ScoreManager#updateScore(double)} method.
     * Score = max(0, |startY - playerY|)
     */
    @Test
    void testUpdateScore() {
        scoreManager.setStartY(600.0);

        scoreManager.updateScore(450.0);
        assertEquals(150, scoreManager.getCurrentScore());

        scoreManager.updateScore(100.0);
        assertEquals(500, scoreManager.getCurrentScore());

        scoreManager.updateScore(300.0);
        assertEquals(500, scoreManager.getCurrentScore());
    }

    /**
     * Tests the {@link ScoreManager#addCoins(int)},
     * {@link ScoreManager#getCoins()} and {@link ScoreManager#spend()} methods.
     */
    @Test
    void testCoinsAndSpend() {
        scoreManager.addCoins(100);
        assertEquals(200, scoreManager.getCoins());
        scoreManager.addCoins(200);
        assertEquals(400, scoreManager.getCoins());
        scoreManager.addCoins(-10);
        assertEquals(400, scoreManager.getCoins());

        assertTrue(scoreManager.spend(200));
        assertEquals(200, scoreManager.getCoins());

        assertFalse(scoreManager.spend(500));
        assertEquals(200, scoreManager.getCoins());
    }

    /**
     * Tests the {@link ScoreManager#getHighScore()} method.
     */
    @Test
    void testHighScoreLogic() {
        scoreManager.loadState(new SaveState(0, 100, Set.of(), Map.of(), "")); // Record vecchio: 100
        scoreManager.setStartY(600.0);

        scoreManager.updateScore(400.0);
        assertEquals(200, scoreManager.getHighScore());
    }
}
