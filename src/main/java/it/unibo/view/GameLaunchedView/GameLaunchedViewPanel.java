package it.unibo.view.GameLaunchedView;

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
 * Rapresent the application's panel seen when the game in launched
 */
public class GameLaunchedViewPanel extends JPanel {

  /**
   *
   */
  private final GameLaunchedControllerImpl controller;

  private final AlienRenderer alienRenderer;
  private final PlatformRenderer platformRenderer;
  private final EnemyRenderer enemyRenderer;
  private final CoinRender coinRenderer;
  private final GadgetRenderer gadgetRenderer;

  public GameLaunchedViewPanel(final GameLaunchedControllerImpl controller) {
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
