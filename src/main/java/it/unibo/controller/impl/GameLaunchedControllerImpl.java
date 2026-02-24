package it.unibo.controller.impl;

import java.util.List;

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

public class GameLaunchedControllerImpl {

  private final CommandState<RunningCommand> runningCommand;
  private final GameWorld gameWorld;

  public GameLaunchedControllerImpl(final GameWorld gameWorld, final CommandState<RunningCommand> runningCommand) {
    this.runningCommand = runningCommand;
    this.gameWorld = gameWorld;
  }

  public void addMoveRightCommand() {
    this.runningCommand.addCommand(new MoveAlienRight());
  }

  public void addMoveLeftCommand() {
    this.runningCommand.addCommand(new MoveAlienLeft());
  }

  public void addPauseCommand() {
    this.runningCommand.addCommand(new EnterPausa());
  }

  public Alien getAlien() {
    return this.gameWorld.getAlien();
  }

  public List<Coin> getCoins() {
    return this.gameWorld.getMoneys();
  }

  public List<Gadget> getGadgets() {
    return this.gameWorld.getGadgets();
  }

  public List<Enemy> getMonsters() {
    return this.gameWorld.getMonsters();
  }

  public List<Platform> getPlatforms() {
    return this.gameWorld.getPlatforms();
  }
}
