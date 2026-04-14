package it.unibo.model.command.impl;

import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.launchedgame.api.LaunchedGame;

/**
 * <p>Rapresent an implementation of {@link RunningCommand} that stop alien movement.</p>
 */
public class StopAlienMovement implements RunningCommand {

  /**
   * <p>Stop the {@link Alien} movement.</p>
   * 
   * @param alien the {@link Alien} to stop.
   */
  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.stopMoving();
  }
}
