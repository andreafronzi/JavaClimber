package it.unibo.model.gameobj.api;

import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * <p>Represents an Alien entity in a two-dimensional game environment.
 * This interface provides methods to manage the Alien's physic movement.</p>
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
   * <p>Check if the Alien is currently moving left.</p>
   * 
   * @return {@code true} if the Alien is moving left, {@code false} otherwise.
   */
  boolean isMovingLeft();

  /**
   * <p>Check if the Alien is currently moving right.</p>
   * 
   * @return {@code true} if the Alien is moving right, {@code false} otherwise.
   */
  boolean isMovingRight();

  /**
   * <p>Initiates movement of the Alien to the left.</p>
   * <p>This method should set the Alien's state to indicate that it is moving left.</p>
   */
  void moveLeft();

  /**
   * <p>Initiates movement of the Alien to the right.</p>
   * <p>This method should set the Alien's state to indicate that it is moving right.</p>
   */
  void moveRight();

  /**
   * <p>Notifies the Alien of a collision with a {@link StaticEntity}.
   * This method is called when the Alien comes into contact with a StaticEntity</p>
   *
   * @param gObj the {@link StaticEntity} that the Alien has collided with
   * @param boundary the {@link Boundary} of the world
   * @param gameWorld the {@link GameWorld} which contains all gameObj
   * @param launchedGame the {@link LaunchedGame} instance which manage game state changes
   */
  void notifyCollision(StaticEntity gObj, Boundary boundary, GameWorld gameWorld, LaunchedGame launchedGame);

  /**
   * <p>Set Alien's physic</p>
   *
   * @param physic the new physic of the Alien
   */
  public void setPhysic(AlienPhysic physic);

  /**
   * <p>Set Alien's speed with the one provided.</p>
   *
   * @param speed the new speed of the Alien
   */
  void setSpeed(Vector2d speed);

  /**
   * <p>Stop Alien's movement.</p>
   */
  void stopMoving();

  /**
   * <p>update Alien's position.</p>
   *
   * @param dt elapsed time between two updates
   * @param boundWorld the boundary of the world
   * @param launchedGame the launched game
   */
  void updatePosition(double dt, BoundWorld boundWorld, LaunchedGame launchedGame);
}
