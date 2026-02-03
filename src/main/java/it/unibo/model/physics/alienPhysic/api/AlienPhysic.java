package it.unibo.model.physics.alienPhysic.api;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.impl.Boundary;

/**
 * Rappresent the application of the chosen physic over the Alien.
 */
public interface AlienPhysic {

  /**
   * Upply chosen physical effect to alien's speed
   * @param alien the alien to update
   * @param dt the time step
   * @param boundary the boundary
   */
  void update(Alien alien, double dt, Boundary boundary);
}
