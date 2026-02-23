package it.unibo.view.renderers;

import java.awt.*;
import java.util.List;

import it.unibo.model.gameObj.api.GameObject;


public interface EntityRenderer<T extends GameObject> {

  void render(List<T> entity, Graphics2D g);
}
