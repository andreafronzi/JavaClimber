package it.unibo.view.renderers;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;

import java.awt.*;
import java.util.Objects;

public class AlienRenderer implements EntityRenderer<Alien> {

  private final SpriteManager spriteManager;

  public AlienRenderer(final SpriteManager spriteManager) {
    this.spriteManager = spriteManager;
  }

  @Override
  public void render(final Alien alien, final Graphics2D g) {
      final Image sprite;
      if (alien.getSpeedX() >= 0) {
          sprite = spriteManager.get(SpriteEnum.DOODLER_RIGHT);
      } else {
          sprite = spriteManager.get(SpriteEnum.DOODLER_LEFT);
      }
      if (!Objects.isNull(sprite)) {
          g.drawImage(sprite, 
              (int) alien.getPosX(), 
              (int) alien.getPosY(), 
              (int) alien.getWidth(), 
              (int) alien.getHeight(), 
              null);
      }
    }
}
