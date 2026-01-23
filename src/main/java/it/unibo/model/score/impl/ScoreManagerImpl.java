package it.unibo.model.score.impl;

import it.unibo.model.score.api.ScoreManager;

/**
 * Implementation of {@link ScoreManager} interface.
 */
public class ScoreManagerImpl implements ScoreManager {

    private int currentScore;
    private int coins;
    private int highScore;
    private double maxPlayerY;

    
    public ScoreManagerImpl(int startingHighScore) {
        this.highScore = startingHighScore;
        this.reset();
    }

    @Override
    public void updateScore(double currentY) {
        if (currentY > this.maxPlayerY) {
            this.maxPlayerY = currentY;
            this.currentScore = (int) this.maxPlayerY;
        }
    }

    @Override
    public void addCoins(int coins) {
        if (coins > 0) {
            this.coins += coins;
        }
    }

    @Override
    public int getCurrentScore() {
        return this.currentScore;
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public int getHighScore() {
        return Math.max(this.highScore, this.currentScore);
    }

    @Override
    public boolean isNewHighScore() {
        return this.currentScore > this.highScore && this.currentScore != 0;
    }

    @Override
    public void reset() {
        if (this.isNewHighScore()) {
            this.highScore = this.currentScore;
        }
        this.currentScore = 0;
        this.coins = 0;
        this.maxPlayerY = 0.0;
    }

}
