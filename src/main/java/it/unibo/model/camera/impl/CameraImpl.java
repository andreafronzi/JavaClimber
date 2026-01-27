package it.unibo.model.camera.impl;

import it.unibo.model.camera.api.Camera;

/**
 * Implementation of {@link Camera} interface.
 */
public class CameraImpl implements Camera {

    private final double width;
    private final double height;
    private double x;
    private double y;
    private double highestYReached;
    private boolean needGeneration;
    private double lastGeneratedY;

    private static final double DISTANCE_FOR_GENERATION = 300.0;

    /**
     * 
     * @param width width of the viewport
     * @param height height of the viewport
     */
    public CameraImpl(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.highestYReached = 0;
        this.needGeneration = true;
        this.lastGeneratedY = 0;
    }

    @Override
    public void update(double targetY) {
        final double targetCameraY = targetY - (height * 0.5);
        if (targetCameraY < highestYReached) {
            highestYReached = targetCameraY;
            this.y = highestYReached;

            if (Math.abs(this.y - lastGeneratedY) > DISTANCE_FOR_GENERATION) {
                this.needGeneration = true;
                this.lastGeneratedY = this.y;
            }
        }
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
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getViewportWidth() {
        return this.width;
    }

    @Override
    public double getViewportHeight() {
        return this.height;
    }

    @Override
    public double getLowerLimit() {
        return this.y + this.height;
    }

}
