package it.unibo.model.gameObj.api;

/**
 * Represents a gadget, which can be collected by the {@link Alien}. When a gadget is collected change the behavior of the {@link Alien}.
 */
public interface Gadget extends GameObject{

  /**
   * When a gadget is collected change the behavior of the {@link Alien}.
   * 
   * @param alien the alien that collects the gadget
   */
  void onCollect(Alien alien);
}
