package it.unibo.model.gameObj.api;

import it.unibo.model.physics.api.Position;

public abstract class GameObj {
  private Position position;

  public GameObj(final Position position) {
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(final Position position) {
    this.position = position;
  }

}
