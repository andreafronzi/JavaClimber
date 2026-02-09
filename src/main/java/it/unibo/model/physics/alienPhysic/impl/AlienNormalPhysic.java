package it.unibo.model.physics.alienPhysic.impl;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.Boundary;
import it.unibo.model.physics.alienPhysic.api.AlienPhysic;
import it.unibo.model.physics.alienPhysic.api.TemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.api.ActiveUpgrades;


/**
 * A concrete implementation of the {@link AlienPhysic} interface.
 * This class applies gravitational force on the vertical axis and
 * manages boundary behavior with a Pacman effect.
 */
public class AlienNormalPhysic extends TemplatePhysic implements AlienPhysic {

  private static final double GRAVITY = 50.0;

  /**
   * Constructs an instance of {@code AlienNormalPhysic}.
   */
  public AlienNormalPhysic() {}

  /**
   * Update alien position and speed applying gravity.
   *
   * @param alien the alien to update
   * @param dt the time step
   * @param boundary the boundary of the world
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  @Override
  protected void moveAlien(final Alien alien, final double dt, final Boundary boundary, final ActiveUpgrades activeUpgrades) {
    double speedY = alien.getSpeedY();

    final double newVelY = speedY + (GRAVITY * dt);
    alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), newVelY));
    alien.setPosition(new Vector2dImpl(alien.getPosX()  + alien.getSpeedX() * dt * activeUpgrades.getSpeedMultiplier(), alien.getPosY() + alien.getSpeedY() * dt * activeUpgrades.getSpeedMultiplier()));
  }

  @Override
  public void hitPlatform(final Alien alien, final Platform p, final Boundary boundary) {
    final double pTollerance = 10;
    final boolean falling = alien.getSpeedY() > 0;
    final boolean above = (alien.getPosY() + alien.getHeight()) <= (p.getHeight() + pTollerance);

    if (falling && !above) {
      final double vx = 0;
      final double vy = -10;
      p.onTouch(boundary);
      alien.setPosition(new Vector2dImpl(alien.getPosX(), p.getPosY() - alien.getHeight()));
      alien.setSpeed(new Vector2dImpl(vx, vy));
    }
  }

  @Override
  public void hitEnemy(final Alien alien, final Enemy e, final ActiveUpgrades activeUpgrades) {
    final double eTollerance = 10;
    final boolean falling = alien.getSpeedY() > 0;
    final boolean above = (alien.getPosY() + alien.getHeight()) <= (e.getHeight() + eTollerance);

    if (falling && !above) {
      final double vx = 0;
      final double vy = -10 * activeUpgrades.getJumpMultiplier();
      e.die();
      alien.setSpeed(new Vector2dImpl(vx, vy));
    }
  }

  @Override
  public void hitGadget(final Alien alien, final Gadget g) {
    final double vx = 0;
    final double vy = -10;
    g.onCollect(alien);
    alien.setSpeed(new Vector2dImpl(vx, vy));
  }

  @Override
  public void hitCoin(final Coin coin, final ActiveUpgrades activeUpgrades) {
    coin.collectCoin(activeUpgrades.getCoinMultiplier());
  }
}
