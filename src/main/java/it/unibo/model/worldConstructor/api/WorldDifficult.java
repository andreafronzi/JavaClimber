package it.unibo.model.worldConstructor.api;

/**
 * Interface for managing world difficulty.
 * Responsible for adjusting the game difficulty based on player progression.
 */
public interface WorldDifficult {
    
    /**
     * Checks and updates the current difficulty level of the world.
     * Typically called periodically or based on events.
     */
    public void createDifficult();

}
