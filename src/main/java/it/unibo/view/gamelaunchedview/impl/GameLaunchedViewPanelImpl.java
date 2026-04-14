package it.unibo.view.gamelaunchedview.impl;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.GameLaunchedInputController;
import it.unibo.controller.impl.GameLaunchedControllerImpl;
import it.unibo.view.SpriteManager;
import it.unibo.view.gamelaunchedview.input.impl.LaunchedGameInputHandlerImpl;
import it.unibo.view.gamelaunchedview.renderers.impl.AlienRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.CoinRender;
import it.unibo.view.gamelaunchedview.renderers.impl.EnemyRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.GadgetRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.MovingPlatformRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.OnTouchPlatformRenderer;
import it.unibo.view.gamelaunchedview.renderers.impl.PlatformRenderer;

import java.awt.*;
import java.util.List;

import javax.swing.*;

/**
 * <p>
 * Rapresent the application's panel seen when the game is launched.
 * </p>
 */
public class GameLaunchedViewPanelImpl extends JPanel {

  /**
   * The {@link GameLaunchedControllerImpl} which provide the game elements to
   * render.
   */
  private final GameLaunchedController launchedController;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Alien} entity within the game
   * view panel.
   */
  private final AlienRenderer alienRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Platform} entities within the
   * game view panel.
   */
  private final PlatformRenderer platformRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Enemy} entities within the game
   * view panel.
   */
  private final EnemyRenderer enemyRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Coin} entities within the game
   * view panel.
   */
  private final CoinRender coinRenderer;

  /**
   * Renders the {@link it.unibo.model.gameobj.api.Gadget} entities within the
   * game view panel.
   */
  private final GadgetRenderer gadgetRenderer;

  /**
   * Renders the moving {@link it.unibo.model.gameobj.api.Platform} entities
   * within the
   * game view panel.
   */
  private final MovingPlatformRenderer movingPlatformRenderer;

  /**
   * Renders the moving {@link it.unibo.model.gameobj.api.Platform} entities which
   * have onTouch behaviour within the
   * game view panel.
   */
  private final OnTouchPlatformRenderer onTouchPlatformRenderer;

  /**
   * <p>
   * Construct a new {@link GameLaunchedViewPanelImpl}.
   * </p>
   *
   * @param launchedController the {@link GameLaunchedControllerImpl} which
   *                           provide the game elements to render.
   * @param inputController    the {@link GameLaunchedInputController} which
   *                           handle the input from the user.
   */
  public GameLaunchedViewPanelImpl(final GameLaunchedController launchedController,
      final GameLaunchedInputController inputController) {
    super();
    this.launchedController = launchedController;

    final SpriteManager spriteManager = new SpriteManager();
    spriteManager.loadResources();

    this.alienRenderer = new AlienRenderer(spriteManager, this.launchedController.getActiveSkin());
    this.platformRenderer = new PlatformRenderer(spriteManager);
    this.enemyRenderer = new EnemyRenderer(spriteManager);
    this.coinRenderer = new CoinRender(spriteManager);
    this.gadgetRenderer = new GadgetRenderer(spriteManager);
    this.movingPlatformRenderer = new MovingPlatformRenderer(spriteManager);
    this.onTouchPlatformRenderer = new OnTouchPlatformRenderer(spriteManager);

    this.addKeyListener(new LaunchedGameInputHandlerImpl(inputController));
    setDoubleBuffered(true); // Aggiungi questa linea nel costruttore
  }

  /**
   * {@inheritDoc}
   *
   * @param g the {@code Java.awt.Graphics} the copy of the graphics used to paint
   *          component
   */
  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    final Graphics2D g2d = (Graphics2D) g;
    
    final double LOGICAL_WIDTH = 600.0;
    final double LOGICAL_HEIGHT = 1000.0;

    double scale = Math.min(this.getWidth() / LOGICAL_WIDTH, this.getHeight() / LOGICAL_HEIGHT);
    int xOffset = (int) ((this.getWidth() - (LOGICAL_WIDTH * scale)) / 2.0);
    int yOffset = (int) ((this.getHeight() - (LOGICAL_HEIGHT * scale)) / 2.0);

    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    g2d.translate(xOffset, yOffset);
    g2d.scale(scale, scale);

    g2d.setColor(new Color(250, 250, 230));
    g2d.fillRect(0, 0, (int) LOGICAL_WIDTH, (int) LOGICAL_HEIGHT);

    g2d.setColor(new Color(230, 230, 210));
    for (int i = 0; i < LOGICAL_WIDTH; i += 40) {
        g2d.drawLine(i, 0, i, (int) LOGICAL_HEIGHT);
    }
    for (int i = 0; i < LOGICAL_HEIGHT; i += 40) {
        g2d.drawLine(0, i, (int) LOGICAL_WIDTH, i);
    }

    this.renderAll(g2d);

    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, 24));
    g2d.drawString("Score: " + this.launchedController.getCurrentScore(), 15, 30);
    g2d.drawString("Coins: " + this.launchedController.getCollectedCoins(), 15, 60);
    
    g2d.scale(1.0 / scale, 1.0 / scale);
    g2d.translate(-xOffset, -yOffset);
  }

  /**
   * <p>
   * Renders all game elements on the provided graphics context.
   * </p>
   *
   * @param g the {@code Graphics2D} object used for rendering the game elements.
   */
  private void renderAll(final Graphics2D g) {
    this.launchedController.getAlien().ifPresent(alien -> this.alienRenderer.render(List.of(alien), g));
    this.launchedController.getMovingPlatforms()
        .ifPresent(platforms -> this.movingPlatformRenderer.render(platforms, g));
    this.launchedController.getOnTouchPlatform()
        .ifPresent(platforms -> this.onTouchPlatformRenderer.render(platforms, g));
    this.launchedController.getStaticPlatforms().ifPresent(platforms -> this.platformRenderer.render(platforms, g));
    this.launchedController.getEnemy().ifPresent(enemies -> this.enemyRenderer.render(enemies, g));
    this.launchedController.getCoins().ifPresent(coins -> this.coinRenderer.render(coins, g));
    this.launchedController.getGadgets().ifPresent(gadgets -> this.gadgetRenderer.render(gadgets, g));
  }
}
