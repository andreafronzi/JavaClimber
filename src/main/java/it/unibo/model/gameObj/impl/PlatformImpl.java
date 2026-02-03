package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformPhysic.api.MovementBehaviour;
import it.unibo.model.physics.platformPhysic.api.OnTouchBehaviour;

import java.util.Optional;

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
  public void onTouch(final Boundary boundary) {
    this.onTouchBehaviour.ifPresent(otb -> otb.onTouch(this.position, super.getWidth(), super.getHeight(), boundary));
  }

  @Override
  public void updatePosition(final double dt, final Boundary boundary) {
    this.movementBehaviour.ifPresent(mb -> mb.updatePosition(this.position, super.getHeight(), super.getHeight(), dt, boundary));
  }

}
