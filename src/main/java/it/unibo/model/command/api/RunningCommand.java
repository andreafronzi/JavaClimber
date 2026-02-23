package it.unibo.model.command.api;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.gameObj.api.Alien;

/**
 * Represents a command that can be executed.
 */
public interface RunningCommand {

  void execute(Alien alien, LaunchedGame launchedGame);
}
