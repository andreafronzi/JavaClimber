package it.unibo.model.physics.platformPhysic.impl;

import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformPhysic.api.MovementBehaviour;

/**
 * Defines horizontal movement behavior for game objects.
 * This behavior updates the position of an object along the X-axis and handles
 * collisions with specified horizontal boundaries, reversing direction when a boundary is reached.
 */
public class HorizontalMovementBehavior implements MovementBehaviour {

  /**
   * Displacement per unit of time.
   */
  private double ds;

  /**
   * Constructs an instance of {@code OrizzontalMovementBehavior} with the specified horizontal
   * displacement speed.
   *
   * @param ds the horizontal displacement speed for the movement behavior
   */
  public HorizontalMovementBehavior(final double ds) {
    this.ds = ds;
  }

  @Override
  public void updatePosition(final Vector2d position, final double width, final double heigth, final double dt, final Boundary boundary) {
    double x = position.getX() + (ds * dt);
    if(position.getX() + (ds * dt) < boundary.x0()) {
      x = boundary.x0();
      this.ds = -this.ds;
    } else if((position.getX() + width) + (ds * dt) > boundary.x1()) {
      x = boundary.x1() - width;
      this.ds = -this.ds;
    }
    position.setX(x);
  }
}
