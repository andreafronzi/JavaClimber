package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * Rapresent an implementation of {@link RunningCommand} that stop alien movement.
 */
public class StopAlienMovement implements RunningCommand {

  /**
   * A constant representing a null speed value.
   */
  private static final double NULL_SPEED = 0;

  /**
   * {@inheritDoc}
   * Stop alien movement.
   *
   * @param alien the alien to stop
   * @param launchedGame the game context
   */
  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.setSpeed(new Vector2dImpl(NULL_SPEED, alien.getSpeedY()));
  }
}
