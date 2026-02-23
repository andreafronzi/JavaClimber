package it.unibo.model.physics.platformPhysic.impl;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.platformPhysic.api.OnTouchBehaviour;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents the behavior of a touched platform.
 * When a platform is touched, this behavior removes it from the {@link GameWorld}.
 */
public class OnTouchDestroyBehavior implements OnTouchBehaviour {

  /**
   * {@inheritDoc}
   */
  @Override
  public void onTouch(final Platform platform, final Boundary boundary, final GameWorld gameWorld) {
    gameWorld.removePlatform(platform);
  }
}
