package it.unibo.view.GameLaunchedView.renderers.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Objects;

import it.unibo.view.GameLaunchedView.renderers.api.EntityRenderer;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;


public class CoinRender implements EntityRenderer<Coin> {

    private final SpriteManager spriteManager;

    public CoinRender(SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    @Override
    public void render(List<Coin> coins, Graphics2D g) {
        final Image sprite;
        sprite = spriteManager.get(SpriteEnum.COIN);
        for (Coin coin : coins) {
            if (!Objects.isNull(sprite)) {
                g.drawImage(sprite,
                        (int) coin.getPosX(),
                        (int) coin.getPosY(),
                        (int) coin.getWidth(),
                        (int) coin.getHeight(),
                        null);
            }
        }
    }

}
