package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameObj.api.Alien;

public class MoveLeftMod implements RunningCommand {

    @Override
    public void execute(Alien alien, LaunchedGame launchedGame) {
        alien.moveLeft();
    }
    
}
