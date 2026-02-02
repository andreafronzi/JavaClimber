package it.unibo.model.world.api;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.world.Gadjet;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.PowerUp;
import it.unibo.model.world.Trap;

public abstract class AbstractBaseWorld implements BaseWorld {

    private List<Platform> platforms;
    private List<Money> moneys;
    private List<Monster> monsters;
    private List<Gadjet> gadgets;
    private List<PowerUp> powerUps;
    private List<Trap> traps;

    public AbstractBaseWorld() {
        this.platforms = new LinkedList<>();
        this.moneys = new LinkedList<>();
        this.gadgets = new LinkedList<>();
        this.monsters = new LinkedList<>();
        this.powerUps = new LinkedList<>();
        this.traps = new LinkedList<>();
    }

    @Override
    public boolean addGadgets(final Gadjet gadget) {
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
    public boolean addPowerUp(final PowerUp powerUp) {
        if (this.powerUps.add(powerUp)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Gadjet> getGadgets() {
        return this.gadgets;
    }

    @Override
    public List<Money> getMoneys() {
        return this.moneys;
    }

    @Override
    public List<Monster> getMonsters() {
        return this.monsters;
    }

    @Override
    public List<Platform> getPlatforms() {
        return this.platforms;
    }

    @Override
    public List<Trap> getTraps() {
        return this.traps;
    }

    @Override
    public List<PowerUp> getPowerUps() {
        return this.powerUps;
    }

    @Override
    public void setGadgets(final List<Gadjet> gadgets) {
        this.gadgets = gadgets;
    }

    @Override
    public void setMoneys(final List<Money> moneys) {
        this.moneys = moneys;
    }

    @Override
    public void setMonsters(final List<Monster> monsters) {
        this.monsters = monsters;
    }

    @Override
    public void setPlatforms(final List<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public void setTraps(final List<Trap> traps) {
        this.traps = traps;
    }

    @Override
    public void setPowerUps(final List<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }
}
