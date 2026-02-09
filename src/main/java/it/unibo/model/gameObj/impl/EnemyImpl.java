package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;

public class EnemyImpl extends GameObj implements Enemy {

  private static final boolean ALIVE = true;

  private boolean isAlive;
  /**
   * Constructs a new EnemyImpl.
   *
   * @param height Enemy's height
   * @param width Enemy's width
   * @param position the position of the Enemy
   */
  public EnemyImpl(final double height, final double width, final Vector2d position) {
    super(height, width, position);
    this.isAlive = ALIVE;

  }

  @Override
  public void die() {
    this.isAlive = !ALIVE;
  }

  @Override
  public boolean idDead() {
    return !this.isAlive;
  }

  @Override
  public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final ActiveUpgrades activeUpgrades) {
    physic.hitEnemy(alien, this, activeUpgrades);
  }
}
