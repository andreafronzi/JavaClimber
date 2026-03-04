package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.physics.impl.Vector2dImpl;

/**
 * <p>Rapresent an implementation of {@link RunningCommand} that move {@link Alien} left.</p>
 */
public class MoveAlienLeft implements RunningCommand {

  /**
   * A constant representing the horizontal speed set to the {@link Alien}.
   */
  private static final double SPEED_X = -400.0;

  /**
   * <p>Move the {@link Alien} left.</p>
   *
   * @param alien the {@link Alien} to move.
   */
  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.setSpeed(new Vector2dImpl(SPEED_X, alien.getSpeedY()));
  }
}
