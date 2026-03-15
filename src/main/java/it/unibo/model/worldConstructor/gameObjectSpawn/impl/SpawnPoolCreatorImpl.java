package it.unibo.model.worldConstructor.gameObjectSpawn.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPositionSetter;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPositionSetterImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPoolCreator;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.gameObj.api.GameObject;
import it.unibo.model.gameObj.api.Platform;

/**
 * Implementation of PlatformPoolCreator.
 * Iterates through the configured pools to find a matching object for a given
 * spawn chance.
 */
public class SpawnPoolCreatorImpl implements SpawnPoolCreator {

    private SpawnPool spawnPool;
    private final BaseWorld world;
    private final AddOnPositionSetter addOnPositionSetter;
    // private List<Pair<Double,Function<Vector2d,Trap>>> traps;

    public SpawnPoolCreatorImpl(final BaseWorld world) {
        this.addOnPositionSetter = new AddOnPositionSetterImpl();
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    public void setSpawnPool(final SpawnPool spawnPool) {
        this.spawnPool = spawnPool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createStaticPlatform(final double chance, final Vector2d pos) {
        this.selector(chance, pos.getX(), pos.getY(), this.spawnPool.getStaticPlatformPool())
                .ifPresent(world::addStaticPlatform);
    }

    @Override
    public void createMovingPlatform(final double chance, final Vector2d pos) {
        this.selector(chance, pos.getX(), pos.getY(), this.spawnPool.getMovingPlatformPool())
                .ifPresent(world::addMovingPlatform);
    }

    @Override
    public void createOnTouchPlatform(final double chance, final Vector2d pos) {
        this.selector(chance, pos.getX(), pos.getY(), this.spawnPool.getOnTouchPlatformPool())
                .ifPresent(world::addOnTouchPlatform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createMonster(final double chance, final Platform platform) {
        this.selector(chance, platform.getPosX(), platform.getPosY(), this.spawnPool.getMonsterPool()).ifPresent(
                monster -> world.addMonster(addOnPositionSetter.generatePosition(monster, platform.getWidth())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createGadget(final double chance, final Platform platform) {
        this.selector(chance, platform.getPosX(), platform.getPosY(), this.spawnPool.getGadgetPool()).ifPresent(
                gadget -> world.addGadget(addOnPositionSetter.generatePosition(gadget, platform.getWidth())));
    }

    /**
     * {@inheritDoc}chance
     */
    @Override
    public void createMoney(final double chance, final Platform platform) {
        this.selector(chance, platform.getPosX(), platform.getPosY(), this.spawnPool.getMoneyPool())
                .ifPresent(money -> world.addMoney(addOnPositionSetter.generatePosition(money, platform.getWidth())));
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

    public <X extends GameObject> Optional<X> selector(double chance, double x, double y,
            List<Pair<Double, Function<Vector2d, X>>> addOns) {
        for (final var addOn : addOns) {
            if (chance <= addOn.getX()) {
                return Optional.of(addOn.getY().apply(new Vector2dImpl(x, y)));
            }
        }
        return Optional.empty();
    }
}
