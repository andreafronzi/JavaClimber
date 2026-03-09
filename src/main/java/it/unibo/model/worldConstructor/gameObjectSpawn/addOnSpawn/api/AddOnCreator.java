package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api;

import it.unibo.model.gameObj.api.Platform;

public interface AddOnCreator {

    public void selectAddOn(Double chance, Platform platform);

    public void setAddOnPool(AddOnPool addOnPool);

}