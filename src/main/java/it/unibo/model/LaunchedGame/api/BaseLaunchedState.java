package it.unibo.model.LaunchedGame.api;

/**
 * Abstract base class for launched game states.
 */
public abstract class BaseLaunchedState implements StateOfLaunchedGame {

    protected LaunchedGame launchedGame;

    /**
     * Constructs a new BaseLaunchedState.
     * 
     * @param launchedGame the game context
     */
    public BaseLaunchedState(final LaunchedGame launchedGame) {
        this.launchedGame = launchedGame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
    }
                        
}