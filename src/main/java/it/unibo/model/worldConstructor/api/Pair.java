package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

public interface Pair<X> {
    
    public double getChance();

    public X createGameObj(final Vector2d pos);
    
}
