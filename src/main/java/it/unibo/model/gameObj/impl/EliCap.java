package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.alienPhysic.impl.AlienEliCapPhysic;
import it.unibo.model.physics.api.Vector2d;

/**
 * Represents the EliCap gadget. When the alien collects it, it will have a vertical speed for a certain time interval, then it will return to normal physic.
 */
public class EliCap extends GameObj implements Gadget{

    /**
     * Represents the duration time of the gadget effect.
     */
  private static final double TIME_INTERVALL = 2;

  /**
   * Represents the vertical speed of the gadget effect.
   */
  private static final double VERTICAL_SPEED = -10;

  /**
   * Constructor of the EliCap gadget.
   *
   * @param height the height of the gadget
   * @param width the width of the gadget
   * @param position the position of the gadget
   */
  public EliCap(final double height, final double width, final Vector2d position) {
        super(height, width, position);
    }

    @Override
    public void onCollect(final Alien alien) {
        alien.setPhysic(new AlienEliCapPhysic(TIME_INTERVALL, VERTICAL_SPEED));
    }

}
