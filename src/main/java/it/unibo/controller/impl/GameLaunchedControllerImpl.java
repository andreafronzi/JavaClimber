package it.unibo.controller.impl;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.GameLaunchedInputController;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.command.impl.EnterPausa;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.command.impl.StopAlienMovement;
import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.World;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Rapresent the implementation of {@link GameLaunchedController} and
 * {@link GameLaunchedInputController}.
 * </p>
 */
public class GameLaunchedControllerImpl implements GameLaunchedController, GameLaunchedInputController {

  /**
   * The {@link LaunchedGame} entity which provide the data to render and receive
   * the command to update the model.
   */
  private final LaunchedGame launchedGame;

  /**
   * Constructor new GameLaunchedControllerImpl.
   *
   * @param launchedGame the launched game entity
   */
  public GameLaunchedControllerImpl(final LaunchedGame launchedGame) {
    this.launchedGame = launchedGame;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Alien> getAlien() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getAlien);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Coin>> getCoins() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getMoneys);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Enemy>> getEnemy() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getMonsters);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Gadget>> getGadgets() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getGadgets);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Platform>> getPlatforms() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getPlatforms);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMoveRightCommand() {
    this.launchedGame.addCommand(new MoveAlienRight());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMoveLeftCommand() {
    this.launchedGame.addCommand(new MoveAlienLeft());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handlePauseCommand() {
    this.launchedGame.addCommand(new EnterPausa());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleReleaseMovementCommand() {
    this.launchedGame.addCommand(new StopAlienMovement());
  }

  @Override
  public void runGame() {
    while () {
      // prendi evento
      this.launchedGame.getState().execute();
      // renderizza
    }
  }

}
