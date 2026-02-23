package it.unibo.view.GameLaunchedView.renderers;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;

import java.awt.*;
import java.util.Objects;
import java.util.List;

public class PlatformRenderer implements EntityRenderer<Platform> {

  private final SpriteManager spriteManager;

  public PlatformRenderer(final SpriteManager spriteManager) {
    this.spriteManager = spriteManager;
  }

  @Override
  public void render(final List<Platform> platforms, final Graphics2D g) {
      final Image sprite = spriteManager.get(SpriteEnum.PLATFORM);
      if (!Objects.isNull(sprite)) {
        platforms.forEach(platform -> {
            g.drawImage(sprite, 
                (int) platform.getPosX(), 
                (int) platform.getPosY(), 
                (int) platform.getWidth(), 
                (int) platform.getHeight(), 
                null);
        });
        }
    }
}
