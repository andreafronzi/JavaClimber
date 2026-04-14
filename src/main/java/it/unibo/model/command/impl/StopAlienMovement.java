package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameobj.api.Alien;

/**
 * Rapresent an implementation of {@link RunningCommand} that stop alien
 * movement.
 */
public class StopAlienMovement implements RunningCommand {

  /**
   * Stop the {@link Alien} movement.
   *
   * @param alien the {@link Alien} to stop.
   */
  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.stopMoving();
  }
}
