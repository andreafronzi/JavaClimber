package it.unibo.view.gameObjSprite;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;

public class AlienSprite {
    final Alien alien;

    public AlienSprite(final Alien alien) {
        this.alien = alien;
    }

    public void drawMario(final Graphics graphics, final SpriteManager spriteManager) throws IOException{
        if(alien.getSpeedX() >= 0) {
            final Image sprite = spriteManager.get(SpriteEnum.DOODLER_RIGHT);
            if(!Objects.isNull(sprite)) {
                graphics.drawImage(spriteManager.get(SpriteEnum.DOODLER_LEFT), (int) alien.getPosX(), (int) alien.getPosY(), (int)alien.getWidth(), (int)alien.getHeight(), null);
            }
        } else {
            final Image sprite = spriteManager.get(SpriteEnum.DOODLER_LEFT);
            if(!Objects.isNull(sprite)) {
                graphics.drawImage(spriteManager.get(SpriteEnum.DOODLER_LEFT), (int) alien.getPosX(), (int) alien.getPosY(), (int)alien.getWidth(), (int)alien.getHeight(), null);
            }
        }
    }
}
