package it.unibo.model.worldConstructor.worldGenerator.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.PlatformImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.PlatformPositionGenerator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PlatformPositionGeneratorImpl;
import it.unibo.model.worldConstructor.worldGenerator.api.Observer;
import it.unibo.model.worldConstructor.worldGenerator.api.WorldConstructor;

/**
 * Implementation of the WorldConstructor interface.
 * Generates the game world by creating platforms and add-ons (coins, monsters,
 * gadgets)
 * based on the current difficulty.
 */
public class WorldConstructorImpl implements WorldConstructor, Observer{

  private Difficult difficult;
  private final PlatformPositionGenerator platformPositionGenerator;
  private final PlatformCreator platformCreator;
  private final AddOnCreator addOnCreator;
  private final Random random;
  private final BoundWorld bound;
  private Vector2d lastPlatformPos;
  private Platform lastStaticPlatform;
  private final QueueWorld world;

  /**
   * Constructs a new WorldConstructorImpl.
   *
   * @param difficult the initial difficulty configuration
   */
  public WorldConstructorImpl(final QueueWorld world, final Difficult difficult,
      final SpawnPoolCreator spawnPoolCreator) {
    this.world = world;
    this.difficult = difficult;
    this.random = new Random();
    this.bound = world.getBoundWorld();
    var pos = new Vector2dImpl(bound.getBoundX().x1() / 2, bound.getBoundY().maxY() - 100);
    var firstPlatform = new PlatformImpl(pos, difficult.platformPool().getWidth(),
        difficult.platformPool().getHeight(), Optional.empty(), Optional.empty());
    world.addStaticPlatform(firstPlatform);
    this.lastPlatformPos = pos;
    this.platformPositionGenerator = new PlatformPositionGeneratorImpl(bound, pos, difficult.distance());
    this.platformPositionGenerator.setDistance(difficult.distance());
    this.addOnCreator = new AddOnCreatorImpl(difficult.addOnPool());
    this.platformCreator = new PlatformCreatorImpl(difficult.platformPool());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillWorld() {
    lastPlatformPos = getLastPlatformPos();
    while (lastPlatformPos.getY() > bound.getBoundY().minY() + difficult.distance().maxDistanceY()) {
      createPlatform();
    }
  }

  private void createPlatform() {
    double chance = random.nextDouble(1.0);
    double chanceAddOn = random.nextDouble(1.0);
    Vector2d pos = platformPositionGenerator.generatePosition(difficult.platformPool().getWidth(),
        difficult.platformPool().getHeight(), lastPlatformPos);
    this.platformCreator.createPlatform(chance, pos);
    this.lastPlatformPos = pos;
    this.lastStaticPlatform = world.getStaticPlatforms().getLast();

    if (chanceAddOn < difficult.addOnPool().getChanceAddOn()
        && lastStaticPlatform.getPosY() == lastPlatformPos.getY()) {
      createAddOn();
    }
  }

  private void createAddOn() {
    double chance = random.nextDouble(1.0);
    addOnCreator.selectAddOn(chance, lastStaticPlatform);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(Difficult difficult) {
    this.platformPositionGenerator.setDistance(difficult.distance());
    this.platformCreator.setPlatformPool(difficult.platformPool());
    this.addOnCreator.setAddOnPool(difficult.addOnPool());
    this.difficult = difficult;
  }

  Vector2d getLastPlatformPos() {
    List<Platform> platforms = new LinkedList<>();
    Vector2d pos = new Vector2dImpl(0, 0);
    if (!world.getStaticPlatforms().isEmpty()) {
      platforms.add(world.getStaticPlatforms().getLast());
    }
    if (!world.getMovingPlatforms().isEmpty()) {
      platforms.add(world.getMovingPlatforms().getLast());

    }
    if (!world.getOnTouchPlatforms().isEmpty()) {
      platforms.add(world.getOnTouchPlatforms().getLast());
    }
    if (platforms.isEmpty()) {
      return new Vector2dImpl(bound.getBoundX().x1() / 2, bound.getBoundY().maxY());
    }
    pos = new Vector2dImpl(platforms.getFirst().getPosX(), platforms.getFirst().getPosY());
    for (Platform p : platforms) {
      if (p.getPosY() < pos.getY()) {
        pos = new Vector2dImpl(p.getPosX(), p.getPosY());
      }
    }
    return pos;
  }
}
