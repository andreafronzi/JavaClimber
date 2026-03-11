package it.unibo.model.physics.collision.impl;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.gameObj.api.*;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * Implementation of the CollisionManager interface.
 * </p>
 * <p>
 * This class is responsible for detecting collisions between the Alien and
 * other game objects in the GameWorld.
 * </p>
 */
public class CollisionManagerImpl implements CollisionManager {

  /**
   * The {@link Boundary} of the game world
   */
  private final Boundary boundary;

  /**
   * <p>
   * Constructs a new CollisionManagerImpl with the specified boundary.
   * </p>
   *
   * @param boundary the {@link Boundary} of the game world
   */
  public CollisionManagerImpl(final Boundary boundary) {
    this.boundary = boundary;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void detectCollisions(final GameWorld gameWorld, final LaunchedGame launchedGame) {

    final Alien alien = gameWorld.getAlien();

    Stream.of(gameWorld.getMovingPlatforms(), gameWorld.getOnTouchPlatforms(), gameWorld.getStaticPlatforms(), gameWorld.getGadgets(), gameWorld.getMoneys(), gameWorld.getMonsters())
        .flatMap(List::stream)
        .filter(se -> checkCollision(alien, se))
        .toList()
        .forEach(se -> alien.notifyCollision(se, boundary, gameWorld, launchedGame));
  }

  /**
   * Checks if there is a collision between the Alien and a StaticEntity
   * (Platform, Gadget, Money, or Monster).
   *
   * @param alien the {@link Alien} entity in the world
   * @param se    the {@link StaticEntity} to check for collision with the Alien
   * @return true if there is a collision, false otherwise
   */
  private boolean checkCollision(final Alien alien, final StaticEntity se) {
    final Rectangle2D alienHitbox = new Rectangle2D.Double(alien.getPosX(), alien.getPosY(), alien.getWidth(),
        alien.getHeight());
    final Rectangle2D seHitbox = new Rectangle2D.Double(se.getPosX(), se.getPosY(), se.getWidth(), se.getHeight());
    return alienHitbox.intersects(seHitbox);
  }
}
