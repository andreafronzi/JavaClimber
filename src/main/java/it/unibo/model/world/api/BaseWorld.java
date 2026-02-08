package it.unibo.model.world.api;

import java.util.List;
import java.util.Optional;

import it.unibo.model.world.Gadget;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.Trap;

public interface BaseWorld {
    
    public List<Platform> getPlatforms();
    public List<Monster> getMonsters();
    public List<Gadget> getGadgets();
    public List<Money> getMoneys();
    public List<Trap> getTraps();

    public boolean addPlatform(Platform platform);
    public boolean addMonster(Monster monster);
    public boolean addGadget(Gadget gadget);
    public boolean addMoney(Money money);
    public boolean addTrap(Trap trap);

    public Optional<Platform> removePlatform();
    public Optional<Monster> removeMonster();
    public Optional<Gadget> removeGadget();
    public Optional<Money> removeMoney();
    public Optional<Trap> removeTrap();
}