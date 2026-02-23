package it.unibo.view.renderers;

import java.awt.*;

import it.unibo.model.gameObj.api.GameObject;


public interface EntityRenderer<T extends GameObject> {

  void render(T entity, Graphics2D g);
}
