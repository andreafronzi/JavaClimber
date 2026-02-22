package it.unibo.model.worldConstructor.impl;

import java.util.List;

import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.worldConstructor.api.Pair;

public class AddOnSelectorPoolEasy<X> {

    List<Pair<X>> addOnPool;
    
    AddOnSelectorPoolEasy(){
        intitializePool();
    }

    void intitializePool(){ 
        addOnPool.add(new PairImpl<>(0.5, PlatformPoolCreatorImpl::createMoney));
    }
}
