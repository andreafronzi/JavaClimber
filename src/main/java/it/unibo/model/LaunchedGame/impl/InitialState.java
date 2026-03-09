package it.unibo.model.LaunchedGame.impl;

import java.util.List;

import it.unibo.model.LaunchedGame.api.*;
import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.camera.impl.CameraImpl;
import it.unibo.model.gameObj.impl.AlienImpl;
import it.unibo.model.physics.collision.impl.CollisionManagerImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.impl.SpawnPoolEasy;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.Distance;
import it.unibo.model.worldConstructor.worldGenerator.impl.WorldConstructorImpl;
import it.unibo.model.worldConstructor.worldGenerator.impl.WorldDifficultImpl;

/**
 * Represents the initial state of a launched game.
 * Used for setup and initialization before the game starts running.
 */
public class InitialState extends BaseLaunchedState {

    /**
     * Constructs a new InitialState.
     * 
     * @param launchedGame the game context
     */
    public InitialState(final LaunchedGame launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     * Performs initialization tasks.
     */
    @Override
    public void onEnter() {
        var boundX = new Boundary(0, 600);
        var boundY = new BoundY(0, 1000);
        var boundary = new BoundWorldImpl(boundY, boundX);
        var upperWorld = new UpperWorld(boundary);
        var alien = new AlienImpl(new Vector2dImpl(300, 900), new Vector2dImpl(0, -1000.0), 100, 100, new ActiveUpgradesImpl(this.launchedGame.getMenu().getInventory()));
        var realWorld = new RealWorld(alien, boundary);
        var world = new World(upperWorld, realWorld);
        this.launchedGame.setWorld(world);
        var distanceEasy = new Distance(200, 100, 300);
        var spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
        var addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, 0.5);
        var spawnPoolEasy = new SpawnPoolEasy(100, 30, this.launchedGame.getMenu().getScoreManager());
        var difficultList = List.of(new Difficult(
                0,
                distanceEasy,
                addOnPoolEasy,
                spawnPoolEasy));
        spawnPoolCreator.setSpawnPool(spawnPoolEasy);
        addOnPoolEasy.setSpawnPoolCreator(spawnPoolCreator);
        var worldConstructor = new WorldConstructorImpl(upperWorld, difficultList.getFirst(), spawnPoolCreator);
        var worldDifficult = new WorldDifficultImpl(alien, difficultList);
        worldDifficult.registerObserver(worldConstructor);
        var altitudeManager = new AltitudeManager(alien, 300);
        altitudeManager.addObserver(worldDifficult);
        var camera = new CameraImpl(boundX.x1() - boundX.x1(), boundY.maxY() - boundY.minY(), world, worldConstructor);
        altitudeManager.addObserver(camera);
        altitudeManager.addObserver(worldConstructor);
        var scoreManager = new ScoreManagerImpl();
        altitudeManager.addObserver(scoreManager);
        var collisionManager = new CollisionManagerImpl(boundX);
        worldConstructor.fillWorld();
        camera.update(0);
        launchedGame.setState(new RunningState(launchedGame, world, collisionManager, altitudeManager, scoreManager));
    }
}
