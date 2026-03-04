package it.unibo.model.gameObj.api;

import it.unibo.model.world.api.GameWorld;

/**
 * <p>Represents a gadget, which can be collected by the {@link Alien}. When a gadget is collected change the behavior of the {@link Alien}.</p>
 */
public interface Gadget extends StaticEntity{

  /**
   * <p>When a gadget is collected change the behavior of the {@link Alien}.</p>
   *
   * @param alien the alien that collects the gadget
   * @param gameWorld the {@link GameWorld} from which the gadget should be removed upon collection
   */
  void onCollect(Alien alien, GameWorld gameWorld);
}
