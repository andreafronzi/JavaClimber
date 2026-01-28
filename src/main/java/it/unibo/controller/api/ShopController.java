package it.unibo.controller.api;

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
     * Exit from the Shop and back to the Menù.
     */
    void exit();
}