package it.unibo.model.gameObj.PlatformBuilder.api;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformPhysic.api.MovementBehaviour;
import it.unibo.model.physics.platformPhysic.api.OnTouchBehaviour;

/**
 * <p>
 * Builder interface for creating {@link Platform} with specific attributes.
 * </p>
 */
public interface PlatformBuilder {

  /**
   * <p>
   * This method sets {@link Platform}'s position.
   * </p>
   * 
   * @param position the position of the Platform
   * @return this builder
   */
  PlatformBuilder at(Vector2d position);

  /**
   * <p>
   * This method sets {@link Platform}'s size.
   * </p>
   * 
   * @param width  the width of the Platform
   * @param height the height of the Platform
   * @return this builder
   */
  PlatformBuilder size(double width, double height);

  /**
   * <p>
   * This method sets the movement behavior of the {@link Platform}.
   * </p>
   * 
   * @param movementBehaviour the movement behavior of the Platform
   * @return this builder
   */
  PlatformBuilder addMovementBehaviour(MovementBehaviour movementBehaviour);

  /**
   * <p>
   * This method adds the behavior the {@link Platform} has when touched.
   * </p>
   * 
   * @param onTouchBehaviour the behavior when the Platform is touched
   * @return this builder
   */
  PlatformBuilder addJumpBehaviour(OnTouchBehaviour onTouchBehaviour);

  /**
   * <p>
   * This method builds the {@link Platform} with the specified attributes.
   * </p>
   * 
   * @return the built Platform
   */
  Platform build();
}
