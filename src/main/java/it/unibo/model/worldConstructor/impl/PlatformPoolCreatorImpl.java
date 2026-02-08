package it.unibo.model.worldConstructor.impl;

import java.util.List;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.Gadget;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.world.Trap;
import it.unibo.model.worldConstructor.api.PlatformPoolCreator;
import it.unibo.model.worldConstructor.api.Pair;
import it.unibo.model.worldConstructor.api.PlatformPool;

public class PlatformPoolCreatorImpl implements PlatformPoolCreator {

    private List<Pair<PlatformImpl>> platforms;
    private List<Pair<Monster>> monsters;
    private List<Pair<Gadget>> gadgets;
    private List<Pair<Money>> money;
    private List<Pair<Trap>> traps;

    public void setSpawnPool(final PlatformPool platformPool) {
        this.platforms = platformPool.getPlatformPool();
        this.monsters = platformPool.getMonsterPool();
        this.gadgets = platformPool.getGadgetPool();
        this.money = platformPool.getMoneyPool();
        this.traps = platformPool.getTrapPool();
    }

    @Override
    public PlatformImpl createPlatform(final double chance, final Vector2d pos) {
        for (final var p : this.platforms) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
    }

    @Override
    public Monster createMonster(final double chance, final Vector2d pos) {
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
    public Money createMoney(final double chance, final Vector2d pos) {
        for (final var p : this.money) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
    }

    @Override
    public Trap createTrap(final double chance, final Vector2d pos) {
        for (final var p : this.traps) {
            if (chance <= p.getChance()) {
                return p.createGameObj(pos);
            }
        }
        return null;
    }

}
