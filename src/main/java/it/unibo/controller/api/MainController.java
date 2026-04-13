package it.unibo.controller.api;

import it.unibo.view.MainView;

/**
 * Interface for the main controller of the application, responsible for
 * handling the main interactions and navigation between different views.
 */
public interface MainController {

    /**
     * Sets the main view opened at a certain time.
     * 
     * @param view the view to be set as the main view at a certain time.
     */
    void setView(MainView view);

    /**
     * Opens the menu view.
     */
    void openMenuView();

    /**
     * Launches the game.
     */
    void launchGame();

    /**
     * Opens the inventory view.
     */
    void openInventoryView();

    /**
     * Opens the shop view.
     */
    void openShopView();

    /**
     * Opens the end view.
     */
    void openEndView();

    /**
     * Opens the pause view.
     */
    void openPauseView();

    /**
     * Saves the current progress.
     */
    void saveProgress();
}
