package it.unibo.view.renderers;

import it.unibo.model.gameObj.api.Gadget;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;

import java.awt.*;
import java.util.Objects;

public class GadgetRenderer implements EntityRenderer<Gadget> {

  private final SpriteManager spriteManager;

  public GadgetRenderer(final SpriteManager spriteManager) {
    this.spriteManager = spriteManager;
  }

  @Override
  public void render(final Gadget gadget, final Graphics2D g) {
      final Image sprite = spriteManager.get(SpriteEnum.GADGET);
      if (!Objects.isNull(sprite)) {
          g.drawImage(sprite, 
              (int) gadget.getPosX(), 
              (int) gadget.getPosY(), 
              (int) gadget.getWidth(), 
              (int) gadget.getHeight(), 
              null);
      }
    }
}

