package it.unibo.model.physics.Collision.impl;

import it.unibo.model.gameObj.api.*;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.Collision.api.CollisionManager;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Stream;

public class CollisionManagerImpl implements CollisionManager {

  private final Boundary boundary;

  public CollisionManagerImpl(final Boundary boundary) {
    this.boundary = boundary;
  }

  @Override
  public void detectCollisions(List<Platform> platforms, List<Coin> coins, List<Enemy> enemies, List<Gadget> gadgets, Alien alien) {
    Stream.of(platforms, coins, enemies, gadgets)
        .flatMap(List::stream)
        .filter(se -> checkCollision(alien, se))
        .forEach(se -> alien.notifyCollision(se, boundary));
  }

  private boolean checkCollision(final Alien alien, final StaticEntity se) {
    final Rectangle2D alienHitbox = new Rectangle2D.Double(alien.getPosX(), alien.getPosY(), alien.getWidth(), alien.getHeight());
    final Rectangle2D seHitbox = new Rectangle2D.Double(se.getPosX(), se.getPosY(), se.getWidth(), se.getHeight());
    return alienHitbox.intersects(seHitbox);
  }
}
