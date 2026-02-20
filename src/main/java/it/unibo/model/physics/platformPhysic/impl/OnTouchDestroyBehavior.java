package it.unibo.model.physics.platformPhysic.impl;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.platformPhysic.api.OnTouchBehaviour;
import it.unibo.model.world.impl.RealWorld;

/**
 * Represents the behavior of a touched platform.
 * When a platform is touched, this behavior removes it from the {@link RealWorld}.
 */
public class OnTouchDestroyBehavior implements OnTouchBehaviour {

  /**
   * {@inheritDoc}
   */
  @Override
  public void onTouch(final Platform platform, final Boundary boundary, final RealWorld realWorld) {
    realWorld.removePlatform(platform);
  }
}
