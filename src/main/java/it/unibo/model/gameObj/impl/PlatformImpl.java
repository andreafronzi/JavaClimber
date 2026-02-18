package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformPhysic.api.MovementBehaviour;
import it.unibo.model.physics.platformPhysic.api.OnTouchBehaviour;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.impl.RealWorld;

import java.util.Optional;

/**
 * A concrete implementation of the {@link Platform} interface.
 */
public class PlatformImpl extends GameObj implements Platform {

  private final Optional<MovementBehaviour> movementBehaviour;
  private final Optional<OnTouchBehaviour> onTouchBehaviour;

  /**
   * Constructs a new Platform with the specified two-dimensional position, dimension, and movement and touch behavior.
   *
   * @param position the initial Platform's position,
   * @param width the Platform's width,
   * @param height the Platform's height,
   * @param movementBehaviour the movement behavior,
   * @param onTouchBehaviour the touch behavior
   */
  public PlatformImpl(
    final Vector2d position,
    final double width,
    final double height,
    final Optional<MovementBehaviour> movementBehaviour,
    final Optional<OnTouchBehaviour> onTouchBehaviour)
  {
    super(height, width, position);
    this.movementBehaviour = movementBehaviour;
    this.onTouchBehaviour = onTouchBehaviour;
  }

  @Override
  public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final RealWorld realWorld, final ActiveUpgrades activeUpgrades) {
    physic.hitPlatform(alien, this, boundary, realWorld, activeUpgrades);
  }

  @Override
  public void onTouch(final Boundary boundary, final RealWorld realWorld) {
    this.onTouchBehaviour.ifPresent(otb -> otb.onTouch(this, boundary, realWorld));
  }

  @Override
  public void updatePosition(final double dt, final Boundary boundary) {
    this.movementBehaviour.ifPresent(mb -> mb.updatePosition(this.position, super.getHeight(), super.getHeight(), dt, boundary));
  }


}
