package it.unibo.model.gameObj.PlatformBuilder.impl;

import it.unibo.model.gameObj.PlatformBuilder.api.PlatformBuilder;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.PlatformImpl;
import it.unibo.model.physics.api.MovementBehaviour;
import it.unibo.model.physics.api.OnTouchBehaviour;
import it.unibo.model.physics.api.Vector2d;

import java.util.Optional;

/**
 * Implementation of the {@link PlatformBuilder} interface for building {@link Platform} objects.
 */
public class PlatformBuilderImpl implements PlatformBuilder {
  
  private Vector2d position;

  private double width;
  private double height;

  private Optional<MovementBehaviour> movementBehaviour = Optional.empty();
  private Optional<OnTouchBehaviour> onTouchBehaviour = Optional.empty();

  public PlatformBuilderImpl() {}

  @Override
  public PlatformBuilder addMovementBehaviour(final MovementBehaviour movementBehaviour) {
    this.movementBehaviour = Optional.of(movementBehaviour);
    return this;
  }

  @Override
  public PlatformBuilder addJumpBehaviour(OnTouchBehaviour onTouchBehaviour) {
    this.onTouchBehaviour = Optional.of(onTouchBehaviour);
    return this;
  }

  @Override
  public PlatformBuilder at(final Vector2d position) {
    this.position = position;
    return this;
  }

  @Override
  public Platform build() {
    return new PlatformImpl(this.position, this.width, this.height, this.movementBehaviour, this.onTouchBehaviour);
  }

  @Override
  public PlatformBuilder size(final double width, final double height) {
    this.width = width;
    this.height = height;
    return this;
  }

}
