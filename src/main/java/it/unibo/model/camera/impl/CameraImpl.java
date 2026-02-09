package it.unibo.model.camera.impl;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.api.Camera;
import it.unibo.model.world.impl.World;

/**
 * Implementation of {@link Camera} interface.
 */
public class CameraImpl implements Camera, AltitudeObserver {

    private final double width;
    private final double height;
    private double y;
    private double totAbbass;
    private final World world;
    private boolean needGeneration;
    private final double distanceForGeneration;

    /**
     * 
     * @param width width of the viewport
     * @param height height of the viewport
     */
    public CameraImpl(double width, double height, World world) {
        this.width = width;
        this.height = height;
        this.y = 0;
        this.world = world;
        this.totAbbass = 0;
        this.needGeneration = true;
        this.distanceForGeneration = height / 2.0;
    }

    @Override
    public boolean shouldGenerateWorld() {
        return this.needGeneration;
    }

    @Override
    public void resetGeneration() {
        this.needGeneration = false;
    }

    @Override
    public void update(double delta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getLowerLimit() {
        return this.y + this.height;
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
