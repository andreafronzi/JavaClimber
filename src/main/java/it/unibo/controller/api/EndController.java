package it.unibo.controller.api;

/**
 * Interface representing the controller for the end screen of the game. It
 * defines
 * the methods that the end screen view will call to handle user interactions.
 */
public interface EndController {

    /**
     * Return to the menu.
     */
    public void menu();

    /**
     * Restart the game.
     */
    public void restart();

    /**
     * Return the score of the player.
     * 
     * @return the score of the player.
     */
    public int getScore();

    /**
     * Check if the player has achieved a new high score.
     * 
     * @return true if the player has achieved a new high score, false otherwise.
     */
    public boolean isNewHighScore();

}