package it.unibo.model.menu.api;

/**
 * Interface representing a specific state of the menu.
 * Each implementation encapsulates the logic and behavior for a particular menu screen or mode.
 */
public interface StateOfMenu {
    
    /**
     * Executes the logic associated with the current menu state.
     */
    public void execute();
}
