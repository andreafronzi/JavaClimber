package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.api.Observer;
import it.unibo.model.worldConstructor.api.WorldConstructor;

public class WorldConstructorImpl implements WorldConstructor, Observer {

    private Difficult difficult;
    private final PlatformPositionGeneratorImpl platformControlDistance;
    private final PlatformPoolCreatorImpl platformPoolCreator;
    private final Random random;
    private final UpperWorld world;
    private Platform previousPlatform;
    private final BoundY boundY;

    public WorldConstructorImpl(final Difficult difficult) {
        this.difficult = difficult;
        this.random = new Random();
        this.world = new UpperWorld();
        this.platformControlDistance = new PlatformPositionGeneratorImpl();
        this.platformPoolCreator = new PlatformPoolCreatorImpl();
        platformPoolCreator.setSpawnPool(difficult.platformPool());
        this.boundY = new BoundY();
    }

    @Override
    public void fillWorld() {
        while (previousPlatform.getPosY() > boundY.maxY() - difficult.maxDistanceY()) {
            createPlatform();
        }
    }

    private void createPlatform() {
        double chance = random.nextDouble(1.0);
        double chanceAddOn = random.nextDouble(1.0);
        Vector2d pos = platformControlDistance.generatePosition();
        previousPlatform = platformPoolCreator.createPlatform(chance, pos);
        world.addPlatform(previousPlatform);
        if (chanceAddOn < difficult.chanceAddOn()) {
            createAddOn();
        }
    }

    private void createAddOn() {
        Vector2d pos = platformControlDistance.generatePosition();
        double choseAddOn = random.nextDouble(1.0);
        if (choseAddOn < difficult.coinChance()) {
            world.addMoney(platformPoolCreator.createMoney(choseAddOn, pos));
        } else if (choseAddOn < difficult.coinChance() + difficult.monsterChance()) {
            world.addMonster(platformPoolCreator.createMonster(choseAddOn, pos));
        } else if (choseAddOn < difficult.coinChance() + difficult.monsterChance() + difficult.gadgetChance()) {
            world.addGadget(platformPoolCreator.createGadget(choseAddOn, pos));
        }
    }

    @Override
    public void update(Difficult difficult) {
        this.difficult = difficult;
        platformControlDistance.setDifficult(difficult);
    }

}
