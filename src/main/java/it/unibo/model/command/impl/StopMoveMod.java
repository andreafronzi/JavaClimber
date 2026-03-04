package it.unibo.model.command.impl;

import it.unibo.model.command.api.RunningCommand;

public class StopMoveMod implements RunningCommand {

    @Override
    public void execute(it.unibo.model.gameObj.api.Alien alien, it.unibo.model.LaunchedGame.api.LaunchedGame launchedGame) {
        alien.stopMoving();
    }
    
}
