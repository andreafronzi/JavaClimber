package it.unibo.view.api;

import java.awt.event.KeyEvent;

/**
 * Interface for handling user input in the launched game view.
 */
public interface LaunchedGameInputHandler {

    /**
     * Handles the event when a key is pressed.
     * 
     * @param event the KeyEvent representing the key press
     */
    void onKeyPressed(KeyEvent event);
    
    /**
     * Handles the event when a key is released.
     * 
     * @param event the KeyEvent representing the key release
     */
    void onKeyReleased(KeyEvent event);
}
