package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.impl.Difficult;

public interface PlatformPositionGenerator {
    
    public Vector2d generatePosition();

    public void setDifficult(Difficult difficult);

}
