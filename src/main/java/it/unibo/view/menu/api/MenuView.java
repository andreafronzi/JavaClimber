package it.unibo.view.menu.api;

/**
 * Interface for the menu view.
 */
public interface MenuView {
    
    /**
     * Update the displayed high score in the menu view.
     * @param score the current high score to be displayed
     */
    public void updateHighScore(int score);
    
    /**
     * Display the menu view.
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
    
}
