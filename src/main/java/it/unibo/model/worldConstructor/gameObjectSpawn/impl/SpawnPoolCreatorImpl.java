package it.unibo.model.worldConstructor.gameObjectSpawn.impl;

import java.util.List;
import java.util.function.Function;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPositionSetter;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPositionSetterImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.ObjectSelector;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;

/**
 * Implementation of PlatformPoolCreator.
 * Iterates through the configured pools to find a matching object for a given spawn chanwce.
 */
public class SpawnPoolCreatorImpl implements SpawnPoolCreator {

    private List<Pair<Double,Function<Vector2d,Platform>>> platforms;
    private List<Pair<Double,Function<Vector2d,Enemy>>> monsters;
    private List<Pair<Double,Function<Vector2d,Gadget>>> gadgets;
    private List<Pair<Double,Function<Vector2d,Coin>>> moneys;
    private final BaseWorld world;
    private final AddOnPositionSetter addOnPositionSetter;
    private double width;
    private final ObjectSelector objectSelector;
    // private List<Pair<Double,Function<Vector2d,Trap>>> traps;

    public SpawnPoolCreatorImpl(final BaseWorld world) {
        this.addOnPositionSetter = new AddOnPositionSetterImpl();
        this.objectSelector = new ObjectSelectorImpl();
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    public void setSpawnPool(final SpawnPool platformPool) {
        this.platforms = platformPool.getPlatformPool();
        this.monsters = platformPool.getMonsterPool();
        this.gadgets = platformPool.getGadgetPool();
        this.moneys = platformPool.getMoneyPool();
        this.width = platformPool.getWidth();
        // this.traps = platformPool.getTrapPool();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPlatform(final double chance, final Vector2d pos) {
        objectSelector.selector(chance, pos, this.platforms).ifPresent(world::addPlatform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createMonster(final double chance, final Vector2d pos) {
        objectSelector.selector(chance, pos, this.monsters).ifPresent(monster -> world.addMonster(addOnPositionSetter.generatePosition(monster, this.width)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createGadget(final double chance, final Vector2d pos) {
        objectSelector.selector(chance, pos, this.gadgets).ifPresent(gadget -> world.addGadget(addOnPositionSetter.generatePosition(gadget, this.width)));
    }

    /**
     * {@inheritDoc}chance
     */
    @Override
    public void createMoney(final double chance, final Vector2d pos) {
        objectSelector.selector(chance, pos, this.moneys).ifPresent(money -> world.addMoney(addOnPositionSetter.generatePosition(money, this.width)));
    }

    /*
     * @Override
     * public Trap createTrap(final double chance, final Vector2d pos) {
     * for (final var p : this.traps) {
     * if (chance <= p.getChance()) {
     * return p.createGameObj(pos);
     * }
     * }
     * return null;
     * }
     */
}
