package it.unibo.model.gameObj.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Abstract game object with a two-dimensional position and a two-dimensional size.
 * This class provides common functionalities to manage the position
 * of an object in a Cartesian coordinate system.
 */
public abstract class GameObj implements GameObject {

  /**
   * The height of the object.
   */
  private final double height;

  /**
   * The width of the object.
   */
  private final double width;

  /**
   * The two-dimensional position of the object.
   */
  protected final Vector2d position;

  /**
   * Constructs a new GameObj with the specified two-dimensional position.
   *
   * @param position the initial position of the game object,
   *                 provided as a {@link Vector2d} instance
   */
  public GameObj(final double height, final double width, final Vector2d position) {
    this.height = height;
    this.width = width;
    this.position = position;
  }

  public double getHeight() {
    return this.height;
  }

  public double getPosX() {
    return this.position.getX();
  }

  public double getPosY() {
    return this.position.getY();
  }

  public double getWidth() {
    return this.width;
  }

  public void setPosition(final Vector2d position) {
    this.position.setX(position.getX());
    this.position.setY(position.getY());
  }

}
