package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents an implementation of the {@link Enemy} interface.
 * This class models an enemy entity within a two-dimensional game environment.
 */
public class EnemyImpl extends GameObj implements Enemy {

  /**
   * Constructs a new EnemyImpl.
   *
   * @param height Enemy's height
   * @param width Enemy's width
   * @param position the position of the Enemy
   */
  public EnemyImpl(final double height, final double width, final Vector2d position) {
    super(height, width, position);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void die(final GameWorld gameWorld) {
    gameWorld.removeMonster(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final GameWorld gameWorld, final ActiveUpgrades activeUpgrades) {
    physic.hitEnemy(alien, this, gameWorld, activeUpgrades);
  }
}
