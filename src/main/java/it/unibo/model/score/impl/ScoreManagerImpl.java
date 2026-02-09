package it.unibo.model.score.impl;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.score.api.ScoreManager;

/**
 * Implementation of {@link ScoreManager} interface.
 */
public class ScoreManagerImpl implements ScoreManager {

    private int currentScore;
    private int coins;
    private int highScore;
    private double startY;

    public ScoreManagerImpl(int initialCoins) {
        this.coins = initialCoins;
        this.currentScore = 0;
        this.highScore = 0;
        this.startY = 0;
    }

    @Override
    public void updateScore(double playerY) {
        int score = (int) Math.max(0, startY - playerY);
        if (score > this.currentScore) {
            this.currentScore = score;

            if (this.currentScore > this.highScore) {
                this.highScore = this.currentScore;
            }
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
    public boolean spend(int price) {
        if (price <= this.coins && price >= 0) {
            this.coins -= price;
            return true;
        }
        return false;
    }

    @Override
    public void loadState(SaveState state) {
        this.coins = state.getCoins();
        this.highScore = state.getHighestScore();
    }

    @Override
    public void setStartY(double y) {
        this.startY = y;
    }

}
