package it.unibo.model.worldConstructor.api;

import java.util.List;

import it.unibo.model.world.Gadget;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.world.Trap;

public interface PlatformPool {

    public List<Pair<PlatformImpl>> getPlatformPool();

    public List<Pair<Monster>> getMonsterPool();

    public List<Pair<Gadget>> getGadgetPool();

    public List<Pair<Money>> getMoneyPool();

    public List<Pair<Trap>> getTrapPool();

}