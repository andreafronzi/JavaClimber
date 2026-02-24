package it.unibo.view.GameLaunchedView.impl;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.impl.GameLaunchedControllerImpl;
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
 * <p>Rapresent the application's panel seen when the game is launched.</p>
 */
public class GameLaunchedViewPanelImpl extends JPanel {

  /**
   * The {@link GameLaunchedControllerImpl} which provide the game elements to render.
   */
  private final GameLaunchedController controller;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Alien} entity within the game view panel.
   */
  private final AlienRenderer alienRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Platform} entities within the game view panel.
   */
  private final PlatformRenderer platformRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Enemy} entities within the game view panel.
   */
  private final EnemyRenderer enemyRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Coin} entities within the game view panel.
   */
  private final CoinRender coinRenderer;

  /**
   * Renders the {@link it.unibo.model.gameObj.api.Gadget} entities within the game view panel.
   */
  private final GadgetRenderer gadgetRenderer;

  /**
   * <p>Construct a new {@link GameLaunchedViewPanelImpl}.</p>
   *
   * @param controller the {@link GameLaunchedControllerImpl} which provide the game elements to render.
   */
  public GameLaunchedViewPanelImpl(final GameLaunchedController controller) {
    super();
    this.controller = controller;

    final SpriteManager spriteManager = new SpriteManager();
    spriteManager.loadResources();

    this.alienRenderer = new AlienRenderer(spriteManager);
    this.platformRenderer = new PlatformRenderer(spriteManager);
    this.enemyRenderer = new EnemyRenderer(spriteManager);
    this.coinRenderer = new CoinRender(spriteManager);
    this.gadgetRenderer = new GadgetRenderer(spriteManager);
  }

  /**
   * {@inheritDoc}
   *
   * @param g the {@code Graphics} the copy of the graphics used to paint component
   */
  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    this.renderAll((Graphics2D) g);
  }

  /**
   * <p>Renders all game elements on the provided graphics context.</p>
   *
   * @param g the {@code Graphics2D} object used for rendering the game elements.
   */
  private void renderAll(final Graphics2D g) {
    this.alienRenderer.render(List.of(this.controller.getAlien()), g);
    this.platformRenderer.render(this.controller.getPlatforms(), g);
    this.enemyRenderer.render(this.controller.getEnemy(), g);
    this.coinRenderer.render(this.controller.getCoins(), g);
    this.gadgetRenderer.render(this.controller.getGadgets(), g);
  }
}
