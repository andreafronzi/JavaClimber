package it.unibo.model.gameobj.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.GameObj;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.alienphysic.impl.AlienEliCapPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * <p>
 * Represents the EliCap gadget. When the alien collects it, it will have a
 * vertical speed for a certain time interval, then it will return to normal
 * physic.
 * </p>
 */
public class EliCap extends GameObj implements Gadget {

  /**
   * Represents the duration time of the gadget effect.
   */
  private static final double TIME_INTERVALL = 3;

  /**
   * Represents the vertical speed of the gadget effect.
   */
  private static final double VERTICAL_SPEED = -500.0;

  /**
   * <p>
   * Constructor of the EliCap gadget.
   * </p>
   *
   * @param height   the height of the gadget
   * @param width    the width of the gadget
   * @param position the position of the gadget
   */
  public EliCap(final double height, final double width, final Vector2d position) {
    super(height, width, position);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCollect(final Alien alien, final GameWorld gameWorld) {
    alien.setPhysic(new AlienEliCapPhysic(TIME_INTERVALL, VERTICAL_SPEED));
    gameWorld.removeGadget(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final GameWorld gameWorld,
      final LaunchedGame launchedGame, final ActiveUpgrades activeUpgrades) {
    physic.hitGadget(alien, this, gameWorld);
  }
}
