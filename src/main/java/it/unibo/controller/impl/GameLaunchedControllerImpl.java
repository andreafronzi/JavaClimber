package it.unibo.controller.impl;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.model.LaunchedGame.api.CommandState;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.command.impl.EnterPausa;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.api.GameWorld;

import java.util.List;

public class GameLaunchedControllerImpl implements GameLaunchedController {

  private final CommandState<RunningCommand> runningCommand;
  private final GameWorld gameWorld;

  public GameLaunchedControllerImpl(final GameWorld gameWorld, final CommandState<RunningCommand> runningCommand) {
    this.runningCommand = runningCommand;
    this.gameWorld = gameWorld;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Alien getAlien() {
    return this.gameWorld.getAlien();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Coin> getCoins() {
    return this.gameWorld.getMoneys();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Enemy> getEnemy() {
    return this.gameWorld.getMonsters();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Gadget> getGadgets() {
    return this.gameWorld.getGadgets();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Platform> getPlatforms() {
    return this.gameWorld.getPlatforms();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMoveRightCommand() {
    this.runningCommand.addCommand(new EnterPausa());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMoveLeftCommand() {
    this.runningCommand.addCommand(new MoveAlienLeft());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handlePauseCommand() {
    this.runningCommand.addCommand(new MoveAlienRight());
  }
}
