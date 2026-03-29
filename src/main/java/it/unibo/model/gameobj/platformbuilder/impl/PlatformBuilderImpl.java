package it.unibo.model.gameobj.platformbuilder.impl;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.PlatformImpl;
import it.unibo.model.gameobj.platformbuilder.api.PlatformBuilder;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.physics.platformphysic.api.OnTouchBehaviour;

import java.util.Optional;

/**
 * <p>
 * Implementation of the {@link PlatformBuilder} interface for building
 * {@link Platform} objects.
 * </p>
 */
public class PlatformBuilderImpl implements PlatformBuilder {

  /**
   * <p>
   * {@link Vector2d} which represents the position of the Platform.
   * </p>
   */
  private Vector2d position;

  /**
   * <p>
   * Width of the Platform.
   * </p>
   */
  private double width;

  /**
   * <p>
   * Height of the Platform.
   * </p>
   */
  private double height;

  /**
   * <p>
   * Optional {@link MovementBehaviour} of the Platform.
   * </p>
   */
  private Optional<MovementBehaviour> movementBehaviour = Optional.empty();

  /**
   * <p>
   * Optional {@link OnTouchBehaviour} of the Platform.
   * </p>
   */
  private Optional<OnTouchBehaviour> onTouchBehaviour = Optional.empty();

  /**
   * <p>
   * Constructs a new {@link PlatformBuilderImpl} with default values.
   * </p>
   */
  public PlatformBuilderImpl() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder addMovementBehaviour(final MovementBehaviour movementBehaviour) {
    this.movementBehaviour = Optional.of(movementBehaviour);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder addJumpBehaviour(final OnTouchBehaviour onTouchBehaviour) {
    this.onTouchBehaviour = Optional.of(onTouchBehaviour);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder at(final Vector2d position) {
    this.position = position;
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Platform build() {
    return new PlatformImpl(this.position, this.width, this.height, this.movementBehaviour, this.onTouchBehaviour);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder size(final double width, final double height) {
    this.width = width;
    this.height = height;
    return this;
  }

}
