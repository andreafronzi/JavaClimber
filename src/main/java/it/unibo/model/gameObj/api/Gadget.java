package it.unibo.model.gameObj.api;

public interface Gadget extends GameObject{

  /**
   * When a gadget is collected change the behavior of the {@link Alien}.
   * 
   * @param alien the alien that collects the gadget
   */
  void onCollect(Alien alien);
}
