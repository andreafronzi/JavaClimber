package it.unibo.model.shop.api;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Interface representing the inventory of a player in the shop.
 */
public interface Inventory {

    /**
     * Add an item to the inventory.
     * @param itemId the ID of the item to add
     */
    void addItem(String itemId);

    /**
     * Check if an item is in the inventory.
     * @param itemId the ID of the item to check
     * @return true if the item is in the player inventory, false otherwise
     */
    boolean hasItem(String itemId);
    
    /**
     * Get the set of owned item IDs.
     * @return a set of owned item IDs
     */
    Set<String> getOwnedItems();

    /**
     * Sets a skin as selected.
     * @param itemId the ID of the skin to select
     */
    void equipSkin(String itemId);

    /**
     * Deselect the currently selected skin.
     */
    void deselectSkin();

    /**
     * Get the currently selected skin, if any.
     * @return an Optional containing the selected skin ID, or empty if none is selected
     */
    Optional<String> getSelectedSkin();

    /**
     * Add a consumable item with its duration in matches.
     * @param itemId the ID of the consumable item to add
     * @param matchesDuration the duration of the consumable in matches
     */
    void addConsumable(String itemId, int matchesDuration);

    /**
     * Get the remaining matches for each consumable item.
     * @return a map of consumable item IDs to their remaining matches
     */
    Map<String, Integer> getConsumablesStatus();

    /**
     * Update the status of consumables, reducing their remaining matches by one.
     */
    void updateConsumables();

}
