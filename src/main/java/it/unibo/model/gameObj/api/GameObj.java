package it.unibo.model.gameObj.api;

import it.unibo.model.physics.api.Vector2d;

/**
 * Abstract game object with a two-dimensional position.
 * This class provides common functionalities to manage the position
 * of an object in a Cartesian coordinate system.
 */
public abstract class GameObj {

  /**
   * The two-dimensional position of the object.
   */
  private final Vector2d position;

  /**
   * Constructs a new GameObj with the specified two-dimensional position.
   *
   * @param position the initial position of the game object,
   *                 provided as a {@link Vector2d} instance
   */
  public GameObj(final Vector2d position) {
    this.position = position;
  }

  /**
   *
   * @return x-coordinate of the position of the object
   */
  public double getPosX() {
    return this.position.getX();
  }

  /**
   *
   * @return y-coordinate of the position of the object
   */
  public double getPosY() {
    return this.position.getY();
  }

  /**
   * Set the Position of the object with the one provided.
   *
   * @param position the new position of the object
   */
  public void setPosition(final Vector2d position) {
    this.position.setX(position.getX());
    this.position.setY(position.getY());
  }

}
