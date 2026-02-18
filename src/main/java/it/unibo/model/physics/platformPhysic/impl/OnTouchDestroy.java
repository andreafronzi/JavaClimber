package it.unibo.model.physics.platformPhysic.impl;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.platformPhysic.api.OnTouchBehaviour;
import it.unibo.model.world.impl.RealWorld;

public class OnTouchDestroy implements OnTouchBehaviour {

  @Override
  public void onTouch(final Platform platform, final Boundary boundary, final RealWorld realWorld) {
    realWorld.removePlatform(platform);
  }
}
