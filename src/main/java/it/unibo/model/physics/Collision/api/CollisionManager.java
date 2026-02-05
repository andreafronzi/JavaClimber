package it.unibo.model.physics.Collision.api;

import java.util.List;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Platform;

public interface CollisionManager {
    

    void detectCollisions(List<Platform> platforms, Alien alien);
}
