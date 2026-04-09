package it.unibo.controller.api;

import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.gameobj.api.*;

import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

/**
 * <p>
 * Rapresent a controller which give to the view the data to render.
 * </p>
 */
public interface GameLaunchedController {

  /**
   * <p>
   * Provide to the view the active skin to render.
   * </p>
   *
   * @return the id of the active skin
   */
  String getActiveSkin();

  /**
   * <p>
   * Provide to the view the {@link Alien} entity to render if present.
   * </p>
   *
   * @return the {@link Alien} entity
   */
  Optional<Alien> getAlien();

  /**
   * <p>
   * Provide to the view the {@link Coin} entities to render if present.
   * </p>
   *
   * @return the {@link Coin} entities
   */
  Optional<List<Coin>> getCoins();

  /**
   * <p>
   * Provide to the view the {@link Enemy} entities to render if present.
   * </p>
   *
   * @return the {@link Enemy} entities
   */
  Optional<List<Enemy>> getEnemy();

  /**
   * <p>
   * Provide to the view the {@link Gadget} entities to render if present.
   * </p>
   *
   * @return the {@link Gadget} entities
   */
  Optional<List<Gadget>> getGadgets();

  /**
   * <p>
   * Provide to the view the moving {@link Platform} entities to render if
   * present.
   * </p>
   * 
   * @return the {@link Platform} entities
   */
  Optional<List<Platform>> getMovingPlatforms();

  /**
   * <p>
   * Provide to the view the {@link Platform} which have onTouch behavoioyr
   * entities to render if present.
   * </p>
   *
   * @return the {@link Platform} entities
   */
  Optional<List<Platform>> getOnTouchPlatform();

  /**
   * <p>
   * Provide to the view the static {@link Platform} entities to render if
   * present.
   * </p>
   *
   * @return the {@link Platform} entities
   */
  Optional<List<Platform>> getStaticPlatforms();

  /**
   * <p>
   * Run {@link LaunchedGame}.
   * </p>
   */
  void runGame();

  /**
   * Set the panel for the game.
   * 
   * @param panel the panel for the game
   */
  void setPanel(JPanel panel);

  /**
   * Return the current score of the player.
   * 
   * @return the current score of the player
   */
  int getCurrentScore();

  /**
   * Return the number of coins collected by the player.
   * 
   * @return the number of coins collected by the player
   */
  int getCollectedCoins();
}
