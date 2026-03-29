package it.unibo.model.command.api;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.gameobj.api.Alien;

/**
 * <p>
 * Represents a command that can be executed.
 * It deals with a simplified version of patter Command, without delegation and where one of the method parameter actually execute the operation
 * </p>
 */
public interface RunningCommand {

  /**
   * <p>Execute an action.</p>
   *
   * @param alien the {@link Alien} which can change his speed
   * @param launchedGame  the {@link LaunchedGame} context which can go in pause state
   */
  void execute(Alien alien, LaunchedGame launchedGame);
}
