package it.unibo.model.camera.impl;

import java.util.List;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.api.Camera;
import it.unibo.model.gameObj.api.GameObject;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldConstructor.api.WorldConstructor;

/**
 * Implementation of {@link Camera} interface.
 */
public class CameraImpl implements Camera, AltitudeObserver {

    private final double width;
    private final double height;
    private double totAbbass;
    private final World world;
    private WorldConstructor worldConstructor;
    private final double distanceForGeneration;
    private final double y;

    /**
     * 
     * @param width width of the viewport
     * @param height height of the viewport
     */
    public CameraImpl(double width, double height, World world, WorldConstructor wc) {
        this.worldConstructor = worldConstructor;
        this.width = width;
        this.height = height;
        this.world = world;
        this.totAbbass = 0;
        this.distanceForGeneration = height / 2.0;
        this.y = 0;
    }

    @Override
    public void update(double delta) {
        this.totAbbass += delta;

        moveWorldList(world.getRealWorld().getPlatforms(), delta);
        moveWorldList(world.getRealWorld().getMoneys(), delta);
        moveWorldList(world.getRealWorld().getGadgets(), delta);
        moveWorldList(world.getRealWorld().getMonsters(), delta);


        moveWorldList(world.getUpperWorld().getPlatforms(), delta);
        moveWorldList(world.getUpperWorld().getGadgets(), delta);
        moveWorldList(world.getUpperWorld().getMoneys(), delta);
        moveWorldList(world.getUpperWorld().getMonsters(), delta);

        transferGameObj();
        cleanRealWorld();

        checkAndGenerateUpperWorld();
    }

    private void moveWorldList(List<? extends GameObject> objects, double delta) {
        for (GameObject obj : objects) {
            double newY = obj.getPosY() + delta;
            obj.setPosition(new Vector2dImpl(obj.getPosX(), newY));
        }
    }

    private void transferGameObj() {
        double dynamicMargin = this.height / 6.0; 
        double viewLimit = this.y - dynamicMargin;

        while (!world.getUpperWorld().getPlatforms().isEmpty() && world.getUpperWorld().getPlatforms().get(0).getPosY() > viewLimit) {
            world.getUpperWorld().removeFirstPlatform().ifPresent(p -> {
                world.getRealWorld().addPlatform(p);
            });
        }

        while (!world.getUpperWorld().getGadgets().isEmpty() && world.getUpperWorld().getGadgets().get(0).getPosY() > viewLimit) {
            world.getUpperWorld().removeFirstGadget().ifPresent(g -> {
                world.getRealWorld().addGadget(g);
            });
        }

        while (!world.getUpperWorld().getMoneys().isEmpty() && world.getUpperWorld().getMoneys().get(0).getPosY() > viewLimit) {
            world.getUpperWorld().removeFirstMoney().ifPresent(m -> {
                world.getRealWorld().addMoney(m);
            });
        }

        while (!world.getUpperWorld().getMonsters().isEmpty() && world.getUpperWorld().getMonsters().get(0).getPosY() > viewLimit) {
            world.getUpperWorld().removeFirstMonster().ifPresent(m -> {
                world.getRealWorld().addMonster(m);
            });
        }
    }

    private void cleanRealWorld() {
        double limit = this.height;
        world.getRealWorld().getPlatforms().removeIf(obj -> obj.getPosY() > limit);
        world.getRealWorld().getGadgets().removeIf(obj -> obj.getPosY() > limit);
        world.getRealWorld().getMonsters().removeIf(obj -> obj.getPosY() > limit);
        world.getRealWorld().getMoneys().removeIf(obj -> obj.getPosY() > limit);
    }

    private void checkAndGenerateUpperWorld() {
        if (this.totAbbass >= this.distanceForGeneration) {
            worldConstructor.fillWorld();
            this.totAbbass = 0;
        }
    }

    @Override
    public double getViewportWidth() {
        return this.width;
    }

    @Override
    public double getViewportHeight() {
        return this.height;
    }

}
