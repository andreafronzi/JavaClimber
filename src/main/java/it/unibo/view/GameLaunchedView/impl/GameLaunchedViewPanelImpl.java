package it.unibo.view.GameLaunchedView.impl;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.GameLaunchedInputController;
import it.unibo.controller.impl.GameLaunchedControllerImpl;
import it.unibo.view.GameLaunchedView.input.impl.LaunchedGameInputHandlerImpl;
import it.unibo.view.SpriteManager;
import it.unibo.view.GameLaunchedView.renderers.impl.AlienRenderer;
import it.unibo.view.GameLaunchedView.renderers.impl.CoinRender;
import it.unibo.view.GameLaunchedView.renderers.impl.EnemyRenderer;
import it.unibo.view.GameLaunchedView.renderers.impl.GadgetRenderer;
import it.unibo.view.GameLaunchedView.renderers.impl.PlatformRenderer;

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
   * Renders the {@link it.unibo.model.gameObj.api.Alien} entity within the game
   * view panel.
   */
  private final AlienRenderer alienRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Platform} entities within the
   * game view panel.
   */
  private final PlatformRenderer platformRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Enemy} entities within the game
   * view panel.
   */
  private final EnemyRenderer enemyRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Coin} entities within the game
   * view panel.
   */
  private final CoinRender coinRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Gadget} entities within the
   * game view panel.
   */
  private final GadgetRenderer gadgetRenderer;

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

    this.addKeyListener(new LaunchedGameInputHandlerImpl(inputController));
    setDoubleBuffered(true); // Aggiungi questa linea nel costruttore
  }

  /**
   * {@inheritDoc}
   *
   * @param g the {@code Java.awt.Graphics} the copy of the graphics used to paint component
   */
  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    final Graphics2D g2d = (Graphics2D) g;
    final int gameWidth = 600;
    final int xOffset = (this.getWidth() - gameWidth) / 2;

    // Draw outer background
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    // Draw game background
    g2d.setColor(new Color(250, 250, 230)); // Cream color
    g2d.fillRect(xOffset, 0, gameWidth, this.getHeight());

    // Optional: Draw a light grid for the background
    g2d.setColor(new Color(230, 230, 210));
    for (int i = 0; i < gameWidth; i += 40) {
        g2d.drawLine(xOffset + i, 0, xOffset + i, this.getHeight());
    }
    for (int i = 0; i < this.getHeight(); i += 40) {
        g2d.drawLine(xOffset, i, xOffset + gameWidth, i);
    }

    g2d.translate(xOffset, 0);
    this.renderAll(g2d);

    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, 24));
    g2d.drawString("Score: " + this.launchedController.getCurrentScore(), 15, 30);
    g2d.drawString("Coins: " + this.launchedController.getCollectedCoins(), 15, 60);
    g2d.translate(-xOffset, 0);
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
    this.launchedController.getPlatforms().ifPresent(platforms -> this.platformRenderer.render(platforms, g));
    this.launchedController.getEnemy().ifPresent(enemies -> this.enemyRenderer.render(enemies, g));
    this.launchedController.getCoins().ifPresent(coins -> this.coinRenderer.render(coins, g));
    this.launchedController.getGadgets().ifPresent(gadgets -> this.gadgetRenderer.render(gadgets, g));
  }
}
