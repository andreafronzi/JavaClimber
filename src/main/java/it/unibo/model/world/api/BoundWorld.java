package it.unibo.model.world.api;

import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;

public interface BoundWorld {
    
    BoundY getBoundY(); 

    Boundary getBoundX();

}
