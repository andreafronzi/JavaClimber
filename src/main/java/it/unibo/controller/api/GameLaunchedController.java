package it.unibo.controller.api;

import it.unibo.model.gameObj.api.*;

import java.util.List;

/**
 * <p>Rapresent a controller which handle input from user and give to the model the command to execute, also give to the view the data to render.</p>
 */
public interface GameLaunchedController {

  /**
   * Provide to the view the {@link Alien} entity to render.
   *
   * @return the {@link Alien} entity
   */
  public Alien getAlien();

  /**
   * Provide to the view the {@link Coin} entities to render.
   *
   * @return the {@link Coin} entities
   */
  public List<Coin> getCoins();

  /**
   * <p>Provide to the view the {@link Enemy} entities to render.</p>
   *
   * @return the {@link Enemy} entities
   */
  public List<Enemy> getEnemy();

  /**
   * <p>Provide to the view the {@link Gadget} entities to render.</p>
   *
   * @return the {@link Gadget} entities
   */
  public List<Gadget> getGadgets();

  /**
   * <p>Provide to the view the {@link Platform} entities to render.</p>
   *
   * @return the {@link Platform} entities
   */
  public List<Platform> getPlatforms();

  /**
   * Handle the input to move the Alien entity to the right.
   * This method signals the model to update the {@link Alien}'s movement state,
   * allowing the Alien to begin or continue moving in the rightward direction.
   */
  public void handleMoveRightCommand();

  /**
   * Handle the input to move the Alien entity to the left.
   * This method signals the model to update the {@link Alien}'s movement state,
   * allowing the Alien to begin or continue moving in the leftward direction.
   */
  public void handleMoveLeftCommand();

  /**
   * Handle the input to pause the {@link it.unibo.model.LaunchedGame.api.LaunchedGame}.
   */
  public void handlePauseCommand();
}
