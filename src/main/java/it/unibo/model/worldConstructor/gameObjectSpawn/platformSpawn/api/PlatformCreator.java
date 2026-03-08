package it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api;

import it.unibo.model.physics.api.Vector2d;

public interface PlatformCreator {

    /**
     * Creates a platform based on the given chance and position.
     * 
     * @param chance the random value used to select the platform type
     * @param pos    the position where the platform should be created
     */
    public void createPlatform(double chance, Vector2d pos);

    void setPlatformPool(PlatformPool platformPool);
}
