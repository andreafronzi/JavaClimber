package it.unibo.controller.api;

/**
 * Controller interface for managing activation of power ups and skins.
 */
public interface InventoryController {

    /**
     * Select a skin from the list of skins.
     * @param index index in the list of skins
     */
    void selectSkin(int index);

    /**
     * Increases the level of jump to the next.
     */
    void plusJump();

    /**
     * Decreases the level of jump to the previus.
     */
    void minusJump();

    /**
     * Increases the level of speed to the next.
     */
    void plusVelocity();

    /**
     * Decreases the level of speed to the previus.
     */
    void minusVelocity();

    /**
     * Activate/Disactivate a temporary power up.
     * @param index index of temporary power ups in the inventory
     */
    void toggleTemporaryItem(int index);

    /**
     * Exit from the Inventory and back to the menù.
     */
    void exit();
}
