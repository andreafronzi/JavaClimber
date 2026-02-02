package it.unibo.model.world.api;

import java.util.List;

import it.unibo.model.world.Gadjet;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.PowerUp;
import it.unibo.model.world.Trap;

public interface BaseWorld {
    
    public List<Platform> getPlatforms();
    public List<Monster> getMonsters();
    public List<Gadjet> getGadgets();
    public List<Money> getMoneys();
    public List<PowerUp> getPowerUps();
    public List<Trap> getTraps();

    public boolean addPlatform(Platform platform);
    public boolean addMonster(Monster monster);
    public boolean addGadgets(Gadjet gadget);
    public boolean addMoney(Money money);
    public boolean addPowerUp(PowerUp powerUp);
    public boolean addTrap(Trap trap);
    
    public void setPlatforms(List<Platform> platforms);
    public void setMonsters(List<Monster> monsters);
    public void setGadgets(List<Gadjet> gadgets);
    public void setMoneys(List<Money> moneys);
    public void setPowerUps(List<PowerUp> powerUps);
    public void setTraps(List<Trap> traps);
}
