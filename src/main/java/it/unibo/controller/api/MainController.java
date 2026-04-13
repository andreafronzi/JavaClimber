package it.unibo.controller.api;

import it.unibo.view.MainView;

/**
<<<<<<< HEAD
 * Interface for the main controller of the application, responsible for
 * handling the main interactions and navigation between different views.
=======
 * Interface for the main controller, which manages the main menu and the
 * transition between different views.
>>>>>>> Fronzi
 */
public interface MainController {

    /**
<<<<<<< HEAD
     * Sets the main view opened at a certain time.
     * 
     * @param view the view to be set as the main view at a certain time.
=======
     * Set the view for this controller and refresh it with current data.
     * 
     * @param view the main view to set
>>>>>>> Fronzi
     */
    void setView(MainView view);

    /**
<<<<<<< HEAD
     * Opens the menu view.
=======
     * Open the main menu view.
>>>>>>> Fronzi
     */
    void openMenuView();

    /**
<<<<<<< HEAD
     * Launches the game.
=======
     * Launch the game.
>>>>>>> Fronzi
     */
    void launchGame();

    /**
<<<<<<< HEAD
     * Opens the inventory view.
=======
     * Open the inventory view.
>>>>>>> Fronzi
     */
    void openInventoryView();

    /**
<<<<<<< HEAD
     * Opens the shop view.
=======
     * Open the shop view.
>>>>>>> Fronzi
     */
    void openShopView();

    /**
<<<<<<< HEAD
     * Opens the end view.
=======
     * Open the end view.
>>>>>>> Fronzi
     */
    void openEndView();

    /**
<<<<<<< HEAD
     * Opens the pause view.
=======
     * Open the pause view.
>>>>>>> Fronzi
     */
    void openPauseView();

    /**
<<<<<<< HEAD
     * Saves the current progress.
=======
     * Save the current game progress.
>>>>>>> Fronzi
     */
    void saveProgress();
}
