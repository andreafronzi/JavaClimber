package it.unibo.model.physics.platformphysic.impl;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.platformphysic.api.OnTouchBehaviour;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * <p>Represents the behavior of a touched platform.
 * When a platform is touched, this behavior removes it from the {@link GameWorld}.
 * </p>
 */
public class OnTouchDestroyBehavior implements OnTouchBehaviour {

  /**
   * {@inheritDoc}
   */
  @Override
  public void onTouch(final Platform platform, final Boundary boundary, final GameWorld gameWorld) {
    gameWorld.removeOnTouchPlatform(platform);
  }
}
