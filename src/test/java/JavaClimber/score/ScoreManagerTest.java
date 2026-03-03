package JavaClimber.score;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Set;

/**
 * Tests for the {@link ScoreManagerImpl} class.
 */
public class ScoreManagerTest {

    private ScoreManagerImpl scoreManager;

    /**
     * Sets up a new ScoreManager instance before each test.
     */
    @BeforeEach
    void setUp() {
        scoreManager = new ScoreManagerImpl();
    }

    /**
     * Tests initial values of the ScoreManager and loads from the file.
     */
    @Test
    void testInitialValuesAndLoad() {
        SaveState state = new SaveState(1500, 5000, Set.of(), Map.of(), "skin_basic", 3, 1);
        scoreManager.loadState(state);

        assertEquals(0, scoreManager.getCurrentScore());
        assertEquals(0, scoreManager.getCoins());
        assertEquals(5000, scoreManager.getHighScore());
    }

    /**
     * Tests the {@link ScoreManager#updateScore(double)} method without camera movement.
     * Score = max(0, |startY - playerY|)
     */
    @Test
    void testUpdateScoreStaticCamera() {
        scoreManager.setStartY(600.0);

        scoreManager.updateScore(450.0);
        assertEquals(150, scoreManager.getCurrentScore());

        scoreManager.updateScore(100.0);
        assertEquals(500, scoreManager.getCurrentScore());

        scoreManager.updateScore(300.0);
        assertEquals(500, scoreManager.getCurrentScore());
    }

    /**
     * Tests the {@link ScoreManager#updateScore(double)} method with camera movement.
      * Score = max(0, |startY - (playerY - totalCameraDelta)|)
     */
    @Test
    void testUpdateScoreWithCameraDelta() {
        scoreManager.setStartY(600.0);
        scoreManager.updateScore(300.0);
        assertEquals(300, scoreManager.getCurrentScore());
        scoreManager.update(100.0);
        scoreManager.updateScore(400.0);
        assertEquals(300, scoreManager.getCurrentScore());
        scoreManager.updateScore(200.0);
        assertEquals(500, scoreManager.getCurrentScore());
    }

    /**
     * Tests the {@link ScoreManager#addCoins(int)} and {@link ScoreManager#getCoins()} methods.
     */
    @Test
    void testCoinsLogic() {
        scoreManager.addCoins(100);
        assertEquals(100, scoreManager.getCoins());
        scoreManager.addCoins(200);
        assertEquals(300, scoreManager.getCoins());
        scoreManager.addCoins(-10);
        assertEquals(300, scoreManager.getCoins());
    }

    /**
     * Tests the {@link ScoreManager#getHighScore()} method.
     */
    @Test
    void testHighScoreLogic() {
        scoreManager.loadState(new SaveState(0, 100, Set.of(), Map.of(), "", 4, 2));
        scoreManager.setStartY(600.0);

        scoreManager.updateScore(400.0);
        assertEquals(200, scoreManager.getHighScore());
        scoreManager.update(50.0);
        scoreManager.updateScore(300.0);
        assertEquals(350, scoreManager.getHighScore());
    }
}
