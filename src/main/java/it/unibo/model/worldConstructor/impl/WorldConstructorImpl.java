package it.unibo.model.worldConstructor.impl;

import java.util.Random;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.worldConstructor.api.Observer;
import it.unibo.model.worldConstructor.api.WorldConstructor;

/**
 * Implementation of the WorldConstructor interface.
 * Generates the game world by creating platforms and add-ons (coins, monsters,
 * gadgets)
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
    public WorldConstructorImpl(final Difficult difficult) {
        this.difficult = difficult;
        this.random = new Random();
        this.world = new UpperWorld();
        this.platformControlDistance = new PlatformPositionGeneratorImpl();
        this.platformPoolCreator = new PlatformPoolCreatorImpl();
        this.addOnPositionGenerator = new AddOnPositionGeneratorImpl();
        platformPoolCreator.setSpawnPool(difficult.platformPool());
        this.boundY = new BoundY();
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
            createAddOn();
        }
    }

    private void createAddOn() {
        Vector2d pos = new Vector2dImpl(0, 0);
        double chance = random.nextDouble(1.0);
        double choseAddOn = random.nextDouble(1.0);
        if (choseAddOn < difficult.coinChance()) {
            Coin coin = platformPoolCreator.createMoney(chance, pos);
            coin.setPosition(addOnPositionGenerator.generatePosition(this.platform.getPosX(),
                    this.platform.getPosY(),
                    this.platform.getWidth(),
                    coin.getHeight(),
                    coin.getWidth()));
            world.addMoney(coin);
        } else if (choseAddOn < difficult.monsterChance()) {
            Enemy enemy = platformPoolCreator.createMonster(chance, pos);
            enemy.setPosition(addOnPositionGenerator.generatePosition(this.platform.getPosX(),
                    this.platform.getPosY(),
                    this.platform.getWidth(),
                    enemy.getHeight(),
                    enemy.getWidth()));
            world.addMonster(enemy);
        } else if (choseAddOn < difficult.gadgetChance()) {
            Gadget gadget = platformPoolCreator.createGadget(chance, pos);
            gadget.setPosition(addOnPositionGenerator.generatePosition(this.platform.getPosX(),
                    this.platform.getPosY(),
                    this.platform.getWidth(),
                    gadget.getHeight(),
                    gadget.getWidth()));
            world.addGadget(gadget);
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
