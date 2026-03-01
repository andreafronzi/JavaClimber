package it.unibo.controller.api;

import it.unibo.view.menu.api.MenuView;

/**
 * Controller for the menu view, responsible for handling user interactions and updating the menu state accordingly.
 */
public interface MenuController {

    /**
     * Set the view for this controller and refresh it with current data.
     * @param view the menu view to set
     */
    public void setView(MenuView view);

    /**
     * Open the game view to start a new game session.
     */
    public void start();

    /**
     * Open the shop view.
     */
    public void shop();

    /**
     * Open the inventory view.
     */
    public void inventory();

    /**
     * Exit the application.
     */
    public void exit();

    /**
     * Open the menu view.
     */
    public void openViewMenu();
    
    /**
     * Get the current high score.
     * @return the current high score
     */
    public int getHighScore();
}