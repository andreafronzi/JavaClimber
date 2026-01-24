package it.unibo.model.shop.api;

import java.util.List;

/**
 * Interface representing the shop manager responsible for handling shop operations.
 */
public interface ShopManager {

    /**
     * Get the catalog of shop items.
     * 
     * @return a list of shop items available in the catalog
     */
    List<ShopItem> getCatalog();

    /**
     * Attempt to buy a shop item.
     * 
     * @param item the shop item to be purchased
     * @return true if the purchase was successful, false otherwise
     */
    boolean buyItem(ShopItem item);

    /**
     * Check if a shop item is already owned.
     * 
     * @param item the shop item to check
     * @return true if the item is already owned, false otherwise
     */
    boolean isAlreadyOwned(ShopItem item);

    /**
     * Check if the player can afford to buy a shop item.
     * 
     * @param item the shop item to check
     * @return true if the player has enough coins to buy the item, false otherwise
     */
    boolean canBuyItem(ShopItem item);

    /**
     * Get the player's inventory.
     * 
     * @return the player's {@link Inventory}
     */
    Inventory getInventory();
}
