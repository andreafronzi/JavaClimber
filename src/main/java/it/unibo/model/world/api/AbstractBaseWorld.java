package it.unibo.model.world.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.model.world.Gadget;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.Trap;

public abstract class AbstractBaseWorld implements BaseWorld {

    private List<Platform> platforms;
    private List<Money> moneys;
    private List<Monster> monsters;
    private List<Gadget> gadgets;
    private List<Trap> traps;

    public AbstractBaseWorld() {
        this.platforms = new LinkedList<>();
        this.moneys = new LinkedList<>();
        this.gadgets = new LinkedList<>();
        this.monsters = new LinkedList<>();
        this.traps = new LinkedList<>();
    }

    @Override
    public boolean addGadget(final Gadget gadget) {
        if (this.gadgets.add(gadget)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addMoney(final Money money) {
        if (this.moneys.add(money)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addMonster(final Monster monster) {
        if (this.monsters.add(monster)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addPlatform(final Platform platform) {
        if (this.platforms.add(platform)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addTrap(final Trap trap) {
        if (this.traps.add(trap)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Gadget> removeGadget() {
        if (!this.gadgets.isEmpty()) {
            return Optional.of(this.gadgets.remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Money> removeMoney() {
        if (!this.moneys.isEmpty()) {
            return Optional.of(this.moneys.remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Monster> removeMonster() {
        if (!this.monsters.isEmpty()) {
            return Optional.of(this.monsters.remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Platform> removePlatform() {
        if (!this.platforms.isEmpty()) {
            return Optional.of(this.platforms.remove(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trap> removeTrap() {
        if (!this.traps.isEmpty()) {
            return Optional.of(this.traps.remove(0));
        }
        return Optional.empty();
    }

    @Override
    public List<Gadget> getGadgets() {
        return List.copyOf(this.gadgets);
    }

    @Override
    public List<Money> getMoneys() {
        return List.copyOf(this.moneys);
    }

    @Override
    public List<Monster> getMonsters() {
        return List.copyOf(this.monsters);
    }

    @Override
    public List<Platform> getPlatforms() {
        return List.copyOf(this.platforms);
    }

    @Override
    public List<Trap> getTraps() {
        return List.copyOf(this.traps);
    }

}