package it.unibo.controller.impl;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.GameLaunchedInputController;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.command.impl.EnterPausa;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.command.impl.StopAlienMovement;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.World;

import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

/**
 * <p>
 * Rapresent the implementation of {@link GameLaunchedController} and
 * {@link GameLaunchedInputController}.
 * </p>
 */
public class GameLaunchedControllerImpl implements GameLaunchedController, GameLaunchedInputController {

  private static final long FRAME_TIME_MS = 16;
  private static final long DIVIDER = 1_000_000_000;
  private static final long DIVIDER_MS = 1_000_000;

  /**
   * The {@link Inventory} which provide the active skin and receive the command
   * to update the model.
   */
  private final Inventory inventory;

  /**
   * The {@link LaunchedGame} entity which provide the data to render and receive
   * the command to update the model.
   */
  private final LaunchedGame launchedGame;

  private JPanel panel;

  /**
   * Constructor new GameLaunchedControllerImpl.
   *
   * @param launchedGame the launched game entity
   * @param inventory    the inventory entity
   */
  public GameLaunchedControllerImpl(final LaunchedGame launchedGame, final Inventory inventory) {
    this.launchedGame = launchedGame;
    this.inventory = inventory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getActiveSkin() {
    return this.inventory.getSelectedSkin();
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
  public Optional<List<Platform>> getMovingPlatforms() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getMovingPlatforms);
  }

  /**
   * {@inheritdoc}
   */
  @Override
  public Optional<List<Platform>> getOnTouchPlatform() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getOnTouchPlatforms);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Platform>> getStaticPlatforms() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getStaticPlatforms);
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void runGame() {
    new Thread(() -> {
      long previousCycleStartTime = System.nanoTime();

      while (launchedGame.isRunning()) {
        final long currentCycleStartTime = System.nanoTime();
        final long elapsedNanos = currentCycleStartTime - previousCycleStartTime;

        final double dt = (double) elapsedNanos / DIVIDER;

        previousCycleStartTime = currentCycleStartTime;

        Optional<RunningCommand> cmd;
        while ((cmd = this.launchedGame.pollCommand()).isPresent()) {
          cmd.get().execute(launchedGame.getWorld().get().getRealWorld().getAlien(), launchedGame);
        }

        this.launchedGame.getState().execute(dt);

        this.panel.repaint();

        java.awt.Toolkit.getDefaultToolkit().sync();

        this.waitForNextFrame(currentCycleStartTime);
      }
    }, "GameLoop").start();
  }

  private void waitForNextFrame(final long currentCycleStartTimeNano) {
    final long elapsedTimeMs = (System.nanoTime() - currentCycleStartTimeNano) / DIVIDER_MS;

    final long sleepTime = FRAME_TIME_MS - elapsedTimeMs;

    if (sleepTime > 0) {
      try {
        Thread.sleep(sleepTime);
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPanel(final JPanel panel) {
    this.panel = panel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCurrentScore() {
    return this.launchedGame.getMenu().getScoreManager().getCurrentScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCollectedCoins() {
    return this.launchedGame.getMenu().getScoreManager().getCoins();
  }

}
