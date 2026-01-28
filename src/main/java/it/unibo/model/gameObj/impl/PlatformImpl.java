package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.MovementBehaviour;
import it.unibo.model.physics.api.OnTouchBehaviour;
import it.unibo.model.physics.api.Vector2d;

import java.util.Optional;

public class PlatformImpl extends GameObj implements Platform {
  private final double width;
  private final double height;

  private final Optional<MovementBehaviour> movementBehaviour;
  private final Optional<OnTouchBehaviour> onTouchBehaviour;


  /**
   * Constructs a new GameObj with the specified two-dimensional position.
   *
   * @param position the initial Platform's position,
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
    super(position);
    this.width = width;
    this.height = height;
    this.movementBehaviour = movementBehaviour;
    this.onTouchBehaviour = onTouchBehaviour;
  }

  @Override
  public boolean containsGadget() {
    return false;
  }
  
  @Override
  public double getHeight() {
    return this.height;
  }
  
  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public void onTouch(final Boundary boundary) {
    this.onTouchBehaviour.ifPresent(otb -> otb.onTouch(this.position, this.width, this.height, boundary));
  }

  @Override
  public void updatePosition(final double dt, final Boundary boundary) {
    this.movementBehaviour.ifPresent(mb -> mb.updatePosition(this.position, this.width, this.height, dt, boundary));
  }

}
