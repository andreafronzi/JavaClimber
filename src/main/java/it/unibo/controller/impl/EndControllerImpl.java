package it.unibo.controller.impl;

import it.unibo.controller.api.EndController;
import it.unibo.controller.api.MainController;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.impl.EndState;
import it.unibo.model.LaunchedGame.impl.InitialState;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuState;

public class EndControllerImpl implements EndController{

    private final LaunchedGame launchedGame;
    private final Menu menu;
    private final MainController mainController;

    public EndControllerImpl(final LaunchedGame launchedGame, final Menu menu, final MainController mainController) {
        this.launchedGame = launchedGame;
        this.menu = menu;
        this.mainController = mainController;
    }
    
    @Override
    public void menu() {
        this.launchedGame.setState(new EndState(this.launchedGame));;
        this.menu.setState(new MenuState(this.menu));
        this.mainController.openMenuView();
    }

    @Override
    public void restart() {
        this.launchedGame.setState(new InitialState(this.launchedGame));
        this.mainController.launchGame();
    }

    @Override
    public void end() {
        this.launchedGame.setState(new EndState(this.launchedGame));
        this.menu.setState(new MenuState(this.menu));
        this.mainController.openEndView();
    }

}
