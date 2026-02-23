package it.unibo.view.GameLaunchedView.renderers;

import it.unibo.model.gameObj.api.GameObject;

import java.awt.*;
import java.util.List;

public interface EntityRenderer<T extends GameObject> {

    void render(List<T> entity, Graphics2D g);
}
