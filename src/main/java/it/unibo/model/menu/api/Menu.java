package it.unibo.model.menu.api;

import java.util.Optional;

import it.unibo.controller.api.MainController;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopManager;

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

    public MainController getMainController();

    public Inventory getInventory();

    public ShopManager getShopManager();

    public ScoreManager getScoreManager();
}
