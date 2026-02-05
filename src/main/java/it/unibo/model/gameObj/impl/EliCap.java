package it.unibo.model.gameObj.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.GameObj;
import it.unibo.model.physics.alienPhysic.impl.AlienEliCapPhysic;
import it.unibo.model.physics.api.Vector2d;

public class EliCap extends GameObj implements Gadget{

  private static final double TIME_INTERVALL = 2;
  private static final double VERTICAL_SPEED = -10;

  public EliCap(final double height, final double width, final Vector2d position) {
        super(height, width, position);
    }

    @Override
    public void onCollect(final Alien alien) {
        alien.setPhysic(new AlienEliCapPhysic(TIME_INTERVALL, VERTICAL_SPEED));
    }

}
