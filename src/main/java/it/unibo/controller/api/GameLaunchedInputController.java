package it.unibo.controller.api;

import it.unibo.model.gameObj.api.Alien;

/**
 * <p>Rapresent the controller which handle input from user and give to the model the command to execute.</p>
 */
public interface GameLaunchedInputController {

  /**
   * <p>
   * Handle the input to move the Alien entity to the right.
   * This method signals the model to update the {@link Alien}'s movement state,
   * allowing the Alien to begin or continue moving in the rightward direction.
   * </p>
   */
  public void handleMoveRightCommand();

  /**
   * <p>
   * Handle the input to move the Alien entity to the left.
   * This method signals the model to update the {@link Alien}'s movement state,
   * allowing the Alien to begin or continue moving in the leftward direction.
   * </p>
   */
  public void handleMoveLeftCommand();

  /**
   * <p>
   * Handle the input to pause the {@link it.unibo.model.LaunchedGame.api.LaunchedGame}.
   * </p>
   */
  public void handlePauseCommand();

  /**
   * <p>
   *   Handle the release of left or right movement command for the {@link Alien} entity.
   *   This method signals the model to update the {@link Alien}'s movement state,
   *   allowing the Alien to stop moving when the user releases the movement key.
   * </p>
   */
  public void handleReleaseMovementCommand();
}
