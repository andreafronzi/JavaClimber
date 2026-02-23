package it.unibo.model.command.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.physics.impl.Vector2dImpl;

public class MoveAlienLeft implements RunningCommand {

  private static final double SPEED_X = -10;

  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.setSpeed(new Vector2dImpl(SPEED_X, alien.getSpeedY()));
  }
}
