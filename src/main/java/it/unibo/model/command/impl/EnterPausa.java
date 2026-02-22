package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.impl.PauseState;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameObj.api.Alien;

public class EnterPausa implements RunningCommand{

    @Override
    public void execute(Alien alien, LaunchedGame launchedGame) {
        launchedGame.setState(new PauseState(launchedGame));
    }
    
}
