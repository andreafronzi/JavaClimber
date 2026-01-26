package it.unibo.model.camera.api;

/**
 * Camera that coordinates player tracking and world generation.
 */
public interface Camera {

    /**
     * Update camera position.
     * @param targetY current Y of the player
     */
    void update(double targetY);

    /**
     * Check if camera has reached a specific point for generate world in the upper world.
     * @return true if generation is needed
     */
    boolean shouldGenerateWorld();

    /**
     * reset generation after world spawn.
     */
    void resetGeneration();

    /**
     * 
     * @return the horizontal coordinate of corner top-left
     */
    double getX();

    /**
     * 
     * @return the vertical coordinate of corner top-left
     */
    double getY();

    /**
     * 
     * @return the width of visible game area.
     */
    double getViewportWidth();

    /**
     * 
     * @return the height of visible game area.
     */
    double getViewportHeight();

    /**
     * 
     * @return the Y coordinate of "death line".
     */
    double getLowerLimit();
}
