package it.unibo.view.gamelaunchedview.renderers.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;
import it.unibo.view.gamelaunchedview.renderers.api.EntityRenderer;
import it.unibo.view.gamelaunchedview.renderers.skingRegistry.api.SkinRegistry;
import it.unibo.view.gamelaunchedview.renderers.skingRegistry.api.SkinSet;
import it.unibo.view.gamelaunchedview.renderers.skingRegistry.impl.SkinRegistryImpl;
import it.unibo.view.SpriteManager;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Renderer for the {@link Alien} in the game.
 */
public class AlienRenderer implements EntityRenderer<Alien> {

    /**
     * An {@link SkinSet} istance 
     */
    private final SkinSet skinSet;

    /**
     * The {@link SpriteManager} used to get the alien sprite.
     */
    private final SpriteManager spriteManager;  

    /**
     * Constructor for the AlienRenderer.
     *
     * @param spriteManager the SpriteManager used to retrieve the alien sprite
     * @param skinName the identifier of the skin to be used for rendering the alien
     */
    public AlienRenderer(final SpriteManager spriteManager, final String skinName) {
        final SkinRegistry skinRegistry = new SkinRegistryImpl();
        this.skinSet = skinRegistry.getSkinSet(skinName);
        
        this.spriteManager = spriteManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Alien> aliens, final Graphics2D g) {
        final Image sprite;
        final Alien alien = aliens.getFirst();

        if (alien.getSpeedX() >= GameObjDimension.NULL_ALIEN_SPEED) {
            sprite = spriteManager.get(this.skinSet.right());
        } else {
            sprite = spriteManager.get(this.skinSet.left());
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
