package it.unibo.controller.api;

import it.unibo.model.shop.api.ShopItem;

/**
 * Controller for managing shop transaction and upgrades.
 */
public interface ShopController {

    /**
     * Attemps to purchase the next level of permanent jump power up.
     */
    void upgradeJump();

    /**
     * Attems to purchase the next level of permanent speed power up.
     */
    void upgradeSpeed();

    /**
     * Purchase a temporary power up by its index in shop catalog. 
     * @param index the position of the item in the temporary items list
     */
    void buyTemporaryItem(int index);

    /**
     * Purchase a skin by its index in shop catalog.
     * @param index the position of the skin in the skins list
     */
    void buySkin(int index);

    /**
     * Checks if an item is already owned by the player.
     * @param item the item to check
     * @return true if owned, false otherwise
     */
    boolean isOwned(ShopItem item);

    /**
     * Exit from the Shop and back to the Menù.
     */
    void exit();
}