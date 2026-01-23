package it.unibo.model.score.api;

/**
 * Interface for managing the game score.
 */
public interface ScoreManager {

    /**
     * Update the score based on the current Y position.
     * @param currentY the current Y position
     */
    void updateScore(double currentY);

    /**
     * Add coins to the score.
     * @param coins the number of coins to add
     */
    void addCoins(int coins);

    /**
     * Get the current score.
     * @return the current score
     */
    int getCurrentScore();

    /**
     * Get the number of collected coins.
     * @return the number of coins
     */
    int getCoins();

    /**
     * Get the highest score achieved.
     * @return the high score
     */
    int getHighScore();

    /**
     * Check if the current score is a new high score.
     * @return true if it is a new high score, false otherwise
     */
    boolean isNewHighScore();

    /**
     * Reset the score.
     */
    void reset();
}
