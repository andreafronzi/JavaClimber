package it.unibo.controller.impl;

import it.unibo.controller.api.PauseController;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.api.StateOfLaunchedGame;
import it.unibo.model.launchedgame.impl.EndState;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuState;

/**
 * Implementation of {@link PauseController}.
 */
public class PauseControllerImpl implements PauseController {

    /**
     * The entity which provide the istance of the game.
     */
    private final LaunchedGame launchedGame;

    /**
     * The entity which provide the istance of the menu.
     */
    private final Menu menu;

    /**
     * The entity which provide the istance of the
     * running state.
     */
    private final StateOfLaunchedGame runningState;
    /**
     * The entity which provide the istance of the main
     * controller.
     */
    private final MainControllerImpl mainController;

    /**
     * Constructor new PauseControllerImpl.
     *
     * @param launchedGame   the launched game entity.
     * @param menu           the menu entity.
     * @param runningState   the running state entity.
     * @param mainController the main controller entity.
     */
    public PauseControllerImpl(final LaunchedGame launchedGame, final Menu menu, final StateOfLaunchedGame runningState,
            final MainControllerImpl mainController) {
        this.launchedGame = launchedGame;
        this.menu = menu;
        this.runningState = runningState;
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        this.launchedGame.setState(this.runningState);
        this.mainController.launchGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void menu() {
        this.launchedGame.setState(new EndState(this.launchedGame));
        this.menu.setState(new MenuState(this.menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.launchedGame.getMenu().getScoreManager().getCurrentScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNewHighScore() {
        return this.launchedGame.getMenu().getScoreManager().isNewHighScore();
    }

}
