package it.unibo.model.gameObj.api;

public interface Platform {

  /** */
  void updatePosition();

  void addMovementBehaviour();
  void addJumpBehaviour();
  void addBreakBehaviour();

  void removeEnemy();
  void removeCoin();
  void removeGadget();

  boolean containsEnemy();
  boolean containsCoin();
  boolean containsGadget();

}
