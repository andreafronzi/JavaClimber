package it.unibo.controller.api;

import it.unibo.model.gameObj.api.*;

import java.util.List;

/**
 * <p>Rapresent a controller which give to the view the data to render.</p>
 */
public interface GameLaunchedController {

  /**
   * <p>Provide to the view the {@link Alien} entity to render.</p>
   *
   * @return the {@link Alien} entity
   */
  public Alien getAlien();

  /**
   * <p>Provide to the view the {@link Coin} entities to render.</p>
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
}
