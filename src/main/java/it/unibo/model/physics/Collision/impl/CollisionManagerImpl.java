package it.unibo.model.physics.Collision.impl;

import it.unibo.model.gameObj.api.*;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.Collision.api.CollisionManager;
import it.unibo.model.world.impl.RealWorld;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of the CollisionManager interface.
 * This class is responsible for detecting collisions between the Alien and other game objects in the RealWorld.
 */
public class CollisionManagerImpl implements CollisionManager {

  /**
   * The boundary of the game world
   */
  private final Boundary boundary;

  /**
   * Constructs a new CollisionManagerImpl with the specified boundary.
   *
   * @param boundary the boundary of the game world
   */
  public CollisionManagerImpl(final Boundary boundary) {
    this.boundary = boundary;
  }

  @Override
  public void detectCollisions(final RealWorld realWorld) {

    final Alien alien = realWorld.getAlien();

    Stream.of(realWorld.getPlatforms(), realWorld.getGadgets(), realWorld.getMoneys(), realWorld.getMonsters())
        .flatMap(List::stream)
        .filter(se -> checkCollision(alien, se))
        .forEach(se -> alien.notifyCollision(se, boundary, realWorld));
  }

  /**
   * Checks if there is a collision between the Alien and a StaticEntity (Platform, Gadget, Money, or Monster).
   *
   * @param alien the Alien entity in the world
   * @param se the StaticEntity to check for collision with the Alien
   * @return true if there is a collision, false otherwise
   */
  private boolean checkCollision(final Alien alien, final StaticEntity se) {
    final Rectangle2D alienHitbox = new Rectangle2D.Double(alien.getPosX(), alien.getPosY(), alien.getWidth(), alien.getHeight());
    final Rectangle2D seHitbox = new Rectangle2D.Double(se.getPosX(), se.getPosY(), se.getWidth(), se.getHeight());
    return alienHitbox.intersects(seHitbox);
  }
}
