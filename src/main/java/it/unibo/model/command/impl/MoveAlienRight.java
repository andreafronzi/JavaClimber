package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameObj.api.Alien;

/**
 * <p>Rapresent an implementation of {@link RunningCommand} that move {@link Alien} right.</p>
 */
public class MoveAlienRight implements RunningCommand {

  /**
   * <p>Move the {@link Alien} right.</p>
   * 
   * @param alien the {@link Alien} to move.
   */
  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.moveRight();
  }
}
