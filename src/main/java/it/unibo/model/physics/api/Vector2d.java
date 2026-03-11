package it.unibo.model.physics.api;

/**
 * <p>
 * Rapresents a two-dimensional vector (Cartesian Coordinates).
 * </p>
 */
public interface Vector2d {

  /**
   * <p>
   * Returns the X component of the vector.
   * </p>
   *
   * @return the X component value
   */
  double getX();

  /**
   * <p>
   * Returns the Y component of the vector.
   * </p>
   *
   * @return the Y component value
   */
  double getY();

  /**
   * <p>
   * Sets the X component of the vector.
   * </p>
   *
   * @param x the new X component value
   */
  void setX(double x);

  /**
   * <p>
   * Sets the Y component of the vector.
   * </p>
   *
   * @param y the new Y component value
   */
  void setY(double y);
}
