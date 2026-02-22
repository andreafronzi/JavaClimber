package it.unibo.model.world.api;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.world.impl.BoundY;

public interface BoundWorld {
    
    BoundY getBoundY(); 

    Boundary getBoundX();

}
