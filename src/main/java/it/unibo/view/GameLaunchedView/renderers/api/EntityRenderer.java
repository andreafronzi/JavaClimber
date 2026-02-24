package it.unibo.view.GameLaunchedView.renderers.api;

import it.unibo.model.gameObj.api.GameObject;

import java.awt.*;
import java.util.List;

/**
 * Interface for rendering game entities.
 *
 * @param <T> the type of game entity to render, which must extend
 *            {@link GameObject}
 */
public interface EntityRenderer<T extends GameObject> {

    /**
     * Renders a list of entities of type T using the provided Graphics2D object.
     *
     * @param entitys the list of entities to render
     * @param g       the Graphics2D object used for rendering
     */
    void render(List<T> entities, Graphics2D g);
}
