package it.unibo.model.gameObj.api;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents an Alien entity in a two-dimensional game environment.
 * This interface provides methods to manage the Alien's physic movement.
 */
public interface Alien extends GameObject {

  /**
   *
   * @return x-coordinate of the speed of the Alien
   */
  double getSpeedX();

  /**
   *
   * @return y-coordinate of the speed of the Alien
   */
  double getSpeedY();

  /**
   * Check if the Alien is currently moving left.
   * 
   * @return {@code true} if the Alien is moving left, {@code false} otherwise.
   */
  boolean isMovingLeft();

  /**
   * Check if the Alien is currently moving right.
   * 
   * @return {@code true} if the Alien is moving right, {@code false} otherwise.
   */
  boolean isMovingRight();

  /**
   * Initiates movement of the Alien to the left. This method should set the Alien's state to indicate that it is moving left.
   */
  void moveLeft();

  /**
   * Initiates movement of the Alien to the right. This method should set the Alien's state to indicate that it is moving right.
   */
  void moveRight();

  /**
   * Notifies the Alien of a collision with a {@link StaticEntity}.
   * This method is called when the Alien comes into contact with a StaticEntity
   *
   * @param gObj the {@link StaticEntity} that the Alien has collided with
   * @param boundary the {@link Boundary} of the world
   * @param gameWorld the {@link GameWorld} which contains all gameObj
   * @param launchedGame the {@link LaunchedGame} instance which manage game state changes
   */
  void notifyCollision(StaticEntity gObj, Boundary boundary, GameWorld gameWorld, LaunchedGame launchedGame);

  /**
   * Set Alien's physic
   *
   * @param physic the new physic of the Alien
   */
  public void setPhysic(AlienPhysic physic);

  /**
   * Set Alien's speed with the one provided.
   *
   * @param speed the new speed of the Alien
   */
  void setSpeed(Vector2d speed);

  /**
   * Stop Alien's movement.
   */
  void stopMoving();

  /**
   * update Alien's position.
   *
   * @param dt elapsed time between two updates
   * @param boundWorld the boundary of the world
   * @param launchedGame the launched game
   */
  void updatePosition(double dt, BoundWorld boundWorld, LaunchedGame launchedGame);
}
