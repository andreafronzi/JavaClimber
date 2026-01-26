package it.unibo.model.physics.api;

import it.unibo.model.gameObj.impl.Boundary;

/**
 * Interface representing Platform's touch reaction.
 */
public interface OnTouchBehaviour {

  /**
   * Platform's touch reaction.
   * @param position position to update
   * @param width Platform's width
   * @param heigth Platform's height
   * @param boundary the boundary of the world
   */
  void onTouch(Vector2d position, double width, double heigth, Boundary boundary);
}
