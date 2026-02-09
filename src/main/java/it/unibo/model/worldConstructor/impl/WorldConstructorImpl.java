package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.api.Observer;
import it.unibo.model.worldConstructor.api.WorldConstructor;

/**
 * Implementation of the WorldConstructor interface.
 * Generates the game world by creating platforms and add-ons (coins, monsters, gadgets)
 * based on the current difficulty.
 */
public class WorldConstructorImpl implements WorldConstructor, Observer {

    private Difficult difficult;
    private final PlatformPositionGeneratorImpl platformControlDistance;
    private final PlatformPoolCreatorImpl platformPoolCreator;
    private final AddOnPositionGeneratorImpl addOnPositionGenerator;
    private final Random random;
    private final UpperWorld world;
    private Platform platform;
    private final BoundY boundY;

    /**
     * Constructs a new WorldConstructorImpl.
     * 
     * @param difficult the initial difficulty configuration
     */
    public WorldConstructorImpl(final Difficult difficult, final BoundY boundY) {
        this.difficult = difficult;
        this.random = new Random();
        this.world = new UpperWorld();
        this.platformControlDistance = new PlatformPositionGeneratorImpl();
        this.platformPoolCreator = new PlatformPoolCreatorImpl();
        this.addOnPositionGenerator = new AddOnPositionGeneratorImpl();
        platformPoolCreator.setSpawnPool(difficult.platformPool());
        this.boundY = boundY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillWorld() {
        while (platform.getPosY() > boundY.maxY() - difficult.maxDistanceY()) {
            createPlatform();
        }
    }

    private void createPlatform() {
        double chance = random.nextDouble(1.0);
        double chanceAddOn = random.nextDouble(1.0);
        Vector2d pos = platformControlDistance.generatePosition();
        platform = platformPoolCreator.createPlatform(chance, pos);
        world.addPlatform(platform);
        if (chanceAddOn < difficult.chanceAddOn()) {
            createAddOn(pos);
        }
    }

    private void createAddOn(final Vector2d posPlatform) {
        Vector2d pos = addOnPositionGenerator.generatePosition(posPlatform);
        double choseTypeAddOn = random.nextDouble(1.0);
        double choseAddOn = random.nextDouble(1.0);
        if (choseAddOn < difficult.coinChance()) {
            world.addMoney(platformPoolCreator.createMoney(choseTypeAddOn, pos));
        } else if (choseAddOn < difficult.coinChance() + difficult.monsterChance()) {
            world.addMonster(platformPoolCreator.createMonster(choseTypeAddOn, pos));
        } else if (choseAddOn < difficult.coinChance() + difficult.monsterChance() + difficult.gadgetChance()) {
            world.addGadget(platformPoolCreator.createGadget(choseTypeAddOn, pos));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Difficult difficult) {
        this.difficult = difficult;
        platformControlDistance.setDifficult(difficult);
    }

}
