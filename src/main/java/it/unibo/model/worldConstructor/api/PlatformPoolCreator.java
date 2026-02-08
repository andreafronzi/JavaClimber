package it.unibo.model.worldConstructor.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.Gadget;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.world.Trap;

public interface PlatformPoolCreator {

    public void setSpawnPool(PlatformPool platformPool);
    
    public PlatformImpl createPlatform(double chance, Vector2d pos);

    public Monster createMonster(double chance, Vector2d pos);

    public Gadget createGadget(double chance, Vector2d pos);

    public Money createMoney(double chance, Vector2d pos);

    public Trap createTrap(double chance, Vector2d pos);

}
