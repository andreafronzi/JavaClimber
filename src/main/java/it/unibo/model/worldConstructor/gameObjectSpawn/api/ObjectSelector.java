package it.unibo.model.worldConstructor.gameObjectSpawn.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.model.gameObj.api.GameObject;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

public interface ObjectSelector {
    
    public <X extends GameObject> Optional<X> selector(double chance, Vector2d pos, List<Pair<Double,Function<Vector2d,X>>> addOns);

}
