package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

public interface Pair<X> {
    
    public Vector2d getPosition();

    public X getGameObj();
    
}
