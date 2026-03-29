package it.unibo.view.GameLaunchedView.renderers.impl;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Objects;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;
import it.unibo.view.GameLaunchedView.renderers.api.EntityRenderer;

import java.awt.*;

public class OnTouchPlatformRenderer implements EntityRenderer<Platform> {

    /**
     * The {@link SpriteManager} used to get the platform sprite.
     */
    private final SpriteManager spriteManager;

    /**
     * Constructor for the MovingPlatformRenderer.
     *
     * @param spriteManager the SpriteManager used to retrieve the platform sprite
     */
    public OnTouchPlatformRenderer(final SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Platform> platforms, final Graphics2D g) {
        final Image sprite = spriteManager.get(SpriteEnum.BROKEN_PLATFORM);
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
