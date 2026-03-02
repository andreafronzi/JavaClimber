package it.unibo.model.worldConstructor.worldGenerator.impl;

import java.util.Random;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPositionGenerator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPositionGeneratorImpl;
import it.unibo.model.worldConstructor.worldGenerator.api.Observer;
import it.unibo.model.worldConstructor.worldGenerator.api.WorldConstructor;

/**
 * Implementation of the WorldConstructor interface.
 * Generates the game world by creating platforms and add-ons (coins, monsters,
 * gadgets)
 * based on the current difficulty.
 */
public class WorldConstructorImpl implements WorldConstructor, Observer {

  private Difficult difficult;
  private final PlatformPositionGenerator platformPositionGenerator;
  private final SpawnPoolCreator platformPoolCreator;
  private final AddOnCreator addOnCreator;
  private final Random random;
  private final BoundWorld bound;
  private Vector2d platformPos;

  /**
   * Constructs a new WorldConstructorImpl.
   *
   * @param difficult the initial difficulty configuration
   */
  public WorldConstructorImpl(final UpperWorld world, final Difficult difficult, final SpawnPoolCreator spawnPoolCreator) {
    this.difficult = difficult;
    this.random = new Random();
    this.bound = world.getBoundWorld();
    this.platformPoolCreator = spawnPoolCreator;
    this.platformPos = new Vector2dImpl(bound.getBoundX().x1() / 2, bound.getBoundY().maxY() - 100);
    platformPoolCreator.createPlatform(0, platformPos);
    this.platformPositionGenerator = new PlatformPositionGeneratorImpl(bound, platformPos, difficult.distance());
    this.platformPositionGenerator.setDistance(difficult.distance());
    this.addOnCreator = new AddOnCreatorImpl(difficult.addOnPool());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillWorld() {
    while (platformPos.getY() > bound.getBoundY().minY() + difficult.distance().maxDistanceY()) {
      createPlatform();
    }
  }

  private void createPlatform() {
    double chance = random.nextDouble(1.0);
    double chanceAddOn = random.nextDouble(1.0);
    Vector2d pos = platformPositionGenerator.generatePosition(difficult.platformPool().getWidth(),
        difficult.platformPool().getHeight());
    platformPoolCreator.createPlatform(chance, pos);

    if (chanceAddOn < difficult.addOnPool().getChanceAddOn()) {
      createAddOn();
    }
  }

  private void createAddOn() {
    double chance = random.nextDouble(1.0);
    addOnCreator.selectAddOn(chance, platformPos);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(Difficult difficult) {
    this.platformPositionGenerator.setDistance(difficult.distance());
    this.platformPoolCreator.setSpawnPool(difficult.platformPool());
    this.addOnCreator.setAddOnPool(difficult.addOnPool());
    this.difficult = difficult;
  }

}
