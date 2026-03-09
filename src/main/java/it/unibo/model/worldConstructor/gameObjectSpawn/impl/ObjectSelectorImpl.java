package it.unibo.model.worldConstructor.gameObjectSpawn.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.model.gameObj.api.GameObject;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.ObjectSelector;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;

public class ObjectSelectorImpl implements ObjectSelector {

    @Override
    public <X extends GameObject> Optional<X> selector(double chance, double x, double y, List<Pair<Double,Function<Vector2d,X>>> addOns) {
        for (final var addOn : addOns) {
            if (chance <= addOn.getX()) {
                return Optional.of(addOn.getY().apply(new Vector2dImpl(x, y)));
            }
        }
        return Optional.empty();
    }

}
