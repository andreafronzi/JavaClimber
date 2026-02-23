package it.unibo.controller;

import java.util.List;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.gameObj.api.Coin;
import it.unibo.model.gameObj.api.Enemy;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.world.api.GameWorld;

public class GameLaunchedController {

    private final GameWorld gameWorld;

    public GameLaunchedController(final GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public List<Platform> getPlatforms() {
        return this.gameWorld.getPlatforms();
    }

    public Alien getAlien() {
        return this.gameWorld.getAlien();
    }

    public List<Enemy> getMonsters() {
        return this.gameWorld.getMonsters();
    }

    public List<Coin> getCoins() {
        return this.gameWorld.getMoneys();
    }

    public List<Gadget> getGadgets() {
        return this.gameWorld.getGadgets();
    }
}