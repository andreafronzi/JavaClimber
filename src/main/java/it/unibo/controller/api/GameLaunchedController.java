package it.unibo.controller.api;

import it.unibo.model.gameObj.api.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>Rapresent a controller which give to the view the data to render.</p>
 */
public interface GameLaunchedController {

  /**
   * <p>Provide to the view the {@link Alien} entity to render if present.</p>
   *
   * @return the {@link Alien} entity
   */
  public Optional<Alien> getAlien();

  /**
   * <p>Provide to the view the {@link Coin} entities to render if present.</p>
   *
   * @return the {@link Coin} entities
   */
  public Optional<List<Coin>> getCoins();

  /**
   * <p>Provide to the view the {@link Enemy} entities to render if present.</p>
   *
   * @return the {@link Enemy} entities
   */
  public Optional<List<Enemy>> getEnemy();

  /**
   * <p>Provide to the view the {@link Gadget} entities to render if present.</p>
   *
   * @return the {@link Gadget} entities
   */
  public Optional<List<Gadget>> getGadgets();

  /**
   * <p>Provide to the view the {@link Platform} entities to render if present.</p>
   *
   * @return the {@link Platform} entities
   */
  public Optional<List<Platform>> getPlatforms();

  /**
   * <p>Run {@link LaunchedGame}.</p>
   */
  public void runGame(); 
}
