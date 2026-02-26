package it.unibo.model.menu.api;

import java.util.Optional;

import it.unibo.model.LaunchedGame.api.LaunchedGame;

/**
 * Interface representing the menu context in the State Pattern.
 * It manages the current state of the menu and allows transitions between different states.
 */
public interface Menu {

    /**
     * Updates the current state of the menu.
     * 
     * @param state the new state to be set
     */
    public void setState(StateOfMenu state);
    
    /**
     * Retrieves the current state of the menu.
     * 
     * @return the currently active StateOfMenu
     */
    public StateOfMenu getState();

    public Optional<LaunchedGame> getLaunchedGame();
    
    public void setLaunchedGame(LaunchedGame launchedGame);

}
