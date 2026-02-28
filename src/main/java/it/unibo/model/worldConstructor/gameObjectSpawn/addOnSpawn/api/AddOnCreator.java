package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import it.unibo.model.physics.api.Vector2d;

public interface AddOnCreator {

    public void createAddOn(Double chance, Vector2d position);

}