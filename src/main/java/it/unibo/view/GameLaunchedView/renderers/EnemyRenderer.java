package it.unibo.view.GameLaunchedView.renderers;

import it.unibo.model.gameObj.api.Enemy;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class EnemyRenderer implements EntityRenderer<Enemy> {

  private final SpriteManager spriteManager;

  public EnemyRenderer(final SpriteManager spriteManager) {
    this.spriteManager = spriteManager;
  }

  @Override
  public void render(final List<Enemy> enemies, final Graphics2D g) {
    final Image sprite = spriteManager.get(SpriteEnum.ENEMY);
    if (!Objects.isNull(sprite)) {
      for (Enemy enemy : enemies) {
        g.drawImage(sprite,
            (int) enemy.getPosX(),
            (int) enemy.getPosY(),
            (int) enemy.getWidth(),
            (int) enemy.getHeight(),
            null);
      }
    }
  }
}
