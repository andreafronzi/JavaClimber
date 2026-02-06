package it.unibo.model.worldConstructor.impl;

import java.util.function.Function;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.api.Pair;

public class PairImpl<X> implements Pair<X> {

    private final double chance;
    private final Function<Vector2d,X> function;

    public PairImpl(final double chance, Function<Vector2d,X> function){
        this.chance = chance;
        this.function = function;
    }

    @Override
    public double getChance() {
        return this.chance;
    }

    @Override
    public X createGameObj(final Vector2d pos) {
        return this.function.apply(pos);    
    }
    
}
