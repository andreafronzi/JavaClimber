package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;

public interface AddOnSelector {    
    
    <X> X selectAddOn(final Vector2d pos);

}
