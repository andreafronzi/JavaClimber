package it.unibo.model.gameobj.api;

import it.unibo.model.world.api.GameWorld;

/**
 * <p>Represents an enemy entity in a two-dimensional game environment.
 * An enemy is a game object that has the ability to be "killed" during gameplay.</p>
 */
public interface Enemy extends StaticEntity {

  /**
   * <p>Triggers the death of the enemy.</p>
   * <p>This method handles the removal of the enemy from the game world</p>
   * 
   * @param gameWorld the {@link GameWorld} from which the enemy should be removed upon death
   */
  void die(GameWorld gameWorld);
}
