package it.unibo.model.LaunchedGame.impl;

import it.unibo.model.LaunchedGame.api.*;

/**
 * Represents the final state of a game session.
 * Handles end-game logic like saving scores or returning to menu.
 */
public class EndState extends BaseLaunchedState {

    /**
     * Constructs a new EndState.
     * 
     * @param launchedGame the game context
     */
    public EndState(final LaunchedGame launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     * Performs end-game operations.
     */
    @Override
    public void onEnter() {
        int collectedCoins = this.launchedGame.getMenu().getScoreManager().getCoins();
        this.launchedGame.getMenu().getInventory().addCoins(collectedCoins);
        this.launchedGame.getMenu().getInventory().updateConsumables();

        this.launchedGame.getMenu().getMainController().openEndView();
        this.launchedGame.getMenu().getMainController().saveProgress();
    }
}
