package it.unibo.view.GameLaunchedView.renderers.skingRegistry.api;

import it.unibo.view.SpriteEnum;

/**
 * <p>Interface representing left and right sprites faces for a character's skin.</p>
 */
public interface SkinSet {

    /**
     * <p>Gets the sprite for the left-facing character.</p>
     * 
     * @return the left-facing sprite
     */
    SpriteEnum getLeft();
    
    /**
     * <p>Gets the sprite for the right-facing character.</p>
     * 
     * @return the right-facing sprite
     */
    SpriteEnum getRight();
}
