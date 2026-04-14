package it.unibo.model.launchedgame.api;

/**
 * Abstract base class for launched game states.
 */
public abstract class AbstractLaunchedState implements StateOfLaunchedGame {

    /**
     * The game context that this state belongs to.
     */
    protected LaunchedGame launchedGame;

    /**
     * Constructs a new BaseLaunchedState.
     * 
     * @param launchedGame the game context
     */
    public AbstractLaunchedState(final LaunchedGame launchedGame) {
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
    public void execute(final double dt) {
    }

}
