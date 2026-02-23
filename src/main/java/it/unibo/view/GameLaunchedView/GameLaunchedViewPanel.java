package it.unibo.view.GameLaunchedView;

import it.unibo.controller.GameLaunchedController;
import it.unibo.view.SpriteManager;
import it.unibo.view.GameLaunchedView.renderers.AlienRenderer;
import it.unibo.view.GameLaunchedView.renderers.CoinRender;
import it.unibo.view.GameLaunchedView.renderers.EnemyRenderer;
import it.unibo.view.GameLaunchedView.renderers.GadgetRenderer;
import it.unibo.view.GameLaunchedView.renderers.PlatformRenderer;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.*;

public class GameLaunchedViewPanel extends JPanel {
    
    private final GameLaunchedController controller;
    private final Graphics2D g;

    private final SpriteManager spriteManager;

    private final AlienRenderer alienRenderer;
    private final PlatformRenderer platformRenderer;
    private final EnemyRenderer enemyRenderer;
    private final CoinRender coinRenderer;
    private final GadgetRenderer gadgetRenderer;

    public GameLaunchedViewPanel(final GameLaunchedController controller) {
        super();
        this.controller = controller;
        this.g = (Graphics2D) this.getGraphics();
        
        this.spriteManager = new SpriteManager();
        this.spriteManager.loadResources();

        this.alienRenderer = new AlienRenderer(this.spriteManager);
        this.platformRenderer = new PlatformRenderer(this.spriteManager);
        this.enemyRenderer = new EnemyRenderer(this.spriteManager);
        this.coinRenderer = new CoinRender(this.spriteManager);
        this.gadgetRenderer = new GadgetRenderer(this.spriteManager);
    }

    public void renderAll() {
        this.alienRenderer.render(List.of(this.controller.getAlien()), this.g);
        this.platformRenderer.render(this.controller.getPlatforms(), this.g);
        this.enemyRenderer.render(this.controller.getMonsters(), this.g);
        this.coinRenderer.render(this.controller.getCoins(), this.g);
        this.gadgetRenderer.render(this.controller.getGadgets(), this.g);
    }


}
