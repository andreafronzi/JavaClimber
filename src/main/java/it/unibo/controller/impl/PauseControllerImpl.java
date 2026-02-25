package it.unibo.controller.impl;

import it.unibo.controller.api.PauseController;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.api.StateOfLaunchedGame;
import it.unibo.model.LaunchedGame.impl.EndState;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuState;

public class PauseControllerImpl implements PauseController {

    private final LaunchedGame launchedGame;
    private final Menu menu;
    private final StateOfLaunchedGame runningState;
    private final MainControllerImpl mainController;

    public PauseControllerImpl(final LaunchedGame launchedGame, final Menu menu, final StateOfLaunchedGame runningState,
            final MainControllerImpl mainController) {
        this.launchedGame = launchedGame;
        this.menu = menu;
        this.runningState = runningState;
        this.mainController = mainController;
    }

    @Override
    public void resume() {
        this.launchedGame.setState(this.runningState);
    }

    @Override
    public void menu() {
        this.launchedGame.setState(new EndState(this.launchedGame));
        this.menu.setState(new MenuState(this.menu));
    }

    @Override
    public void openViewPause() {
        this.mainController.openPauseView();
    }

}
