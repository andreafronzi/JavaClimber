package it.unibo.model.worldConstructor.impl;

import java.util.List;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.worldConstructor.api.PlatformPoolCreator;
import it.unibo.model.worldConstructor.api.Pair;
import it.unibo.model.worldConstructor.api.PlatformPool;

public class PlatformPoolCreatorImpl implements PlatformPoolCreator {

    private List<Pair<Platform>> platforms;
    private List<Pair<Enemy>> monsters;
    private List<Pair<Gadget>> gadgets;
    private List<Pair<Coin>> money;
    // private List<Pair<Trap>> traps;

    public void setSpawnPool(final PlatformPool platformPool) {
        this.platforms = platformPool.getPlatformPool();
        this.monsters = platformPool.getMonsterPool();
        this.gadgets = platformPool.getGadgetPool();
        this.money = platformPool.getMoneyPool();
        // this.traps = platformPool.getTrapPool();
    }

    @Override
    public Platform createPlatform(final double chance, final Vector2d pos) {
        for (final var p : this.platforms) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
    }

    @Override
    public Enemy createMonster(final double chance, final Vector2d pos) {
        for (final var p : this.monsters) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
    }

    @Override
    public Gadget createGadget(final double chance, final Vector2d pos) {
        for (final var p : this.gadgets) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
    }

    @Override
    public Coin createMoney(final double chance, final Vector2d pos) {
        for (final var p : this.money) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
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
