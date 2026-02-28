package it.unibo.view.GameLaunchedView.renderers.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.view.GameLaunchedView.renderers.api.EntityRenderer;
import it.unibo.view.SpriteEnum;
import it.unibo.view.SpriteManager;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Renderer for the {@link Alien} in the game.
 */
public class AlienRenderer implements EntityRenderer<Alien> {

    /**
     * The constant representing the null speed of the Alien.
     */
    private static final double NULL_SPEED = 0.0;

    /**
     * The {@link SpriteManager} used to get the alien sprite.
     */
    private final SpriteManager spriteManager;

    /**
     * Constructor for the AlienRenderer.
     *
     * @param spriteManager the SpriteManager used to retrieve the alien sprite
     */
    public AlienRenderer(final SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Alien> aliens, final Graphics2D g) {
        final Image sprite;
        final Alien alien = aliens.getFirst();

        if (alien.getSpeedX() >= NULL_SPEED) {
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
