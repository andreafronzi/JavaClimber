package it.unibo.model.worldConstructor.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.world.Gadget;
import it.unibo.model.world.Money;
import it.unibo.model.world.Monster;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.world.Trap;
import it.unibo.model.worldConstructor.api.Pair;
import it.unibo.model.worldConstructor.api.PlatformPool;
import it.unibo.model.worldConstructor.api.Director;

public class PlatformPoolEasy implements PlatformPool {

    private final List<Pair<PlatformImpl>> platformPool;
    private final List<Pair<Monster>> monsterPool;
    private final List<Pair<Gadget>> gadgetPool;
    private final List<Pair<Money>> moneyPool;
    private final List<Pair<Trap>> trapPool;
    private final Director director;

    public PlatformPoolEasy() {

        this.platformPool = new LinkedList<>();
        this.monsterPool = new LinkedList<>();
        this.gadgetPool = new LinkedList<>();
        this.moneyPool = new LinkedList<>();
        this.trapPool = new LinkedList<>();
        this.director = new DirectorImpl(0,0);

        initializePlatformPool();
        initializeMonsterPool();
        initializeGadgetPool();
        initializeMoneyPool();
        initializeTrapPool();
    }

    @Override
    public List<Pair<PlatformImpl>> getPlatformPool() {
        return List.copyOf(this.platformPool);
    }

    @Override
    public List<Pair<Monster>> getMonsterPool() {
        return List.copyOf(this.monsterPool);
    }

    @Override
    public List<Pair<Gadget>> getGadgetPool() {
        return List.copyOf(this.gadgetPool);
    }

    @Override
    public List<Pair<Money>> getMoneyPool() {
        return List.copyOf(this.moneyPool);  
    }

    @Override
    public List<Pair<Trap>> getTrapPool() {
        return List.copyOf(this.trapPool);
    }

    private void initializePlatformPool() {
        //qui devo mettere le varie piattaforme con le loro chance
    }
    
    private void initializeMonsterPool() {
        //qui devo mettere i vari mostri con le loro chance
    }

    private void initializeGadgetPool() {
        //qui devo mettere i vari gadget con le loro chance
    }

    private void initializeMoneyPool() {
        //qui devo mettere i vari soldi con le loro chance
    }

    private void initializeTrapPool() {
        //qui devo mettere le varie trappole con le loro chance
    }
}
