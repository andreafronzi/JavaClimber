package it.unibo.model.worldconstructor.worldgenerator.api;

import it.unibo.model.worldconstructor.data.Difficult;

/**
 * Interface for the Observer pattern.
 * Implementing classes can receive updates about changes in difficulty.
 */
public interface Observer {

    /**
     * Called when the subject updates the difficulty.
     * 
     * @param difficult the new difficulty settings
     */
    void update(Difficult difficult);
}
