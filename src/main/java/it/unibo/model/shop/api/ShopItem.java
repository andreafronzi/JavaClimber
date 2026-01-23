package it.unibo.model.shop.api;

import it.unibo.model.gameObj.api.Alien;
import java.util.Map;

/**
 * Interface representing an item available in the shop.
 */
public interface ShopItem {

    /**
     * Get the unique identifier of the shop item.
     * 
     * @return the ID of the shop item
     */
    String getId();

    /**
     * Get the name of the shop item.
     * 
     * @return the name of the shop item
     */
    String getName();

    /**
     * Get the description of the shop item.
     * 
     * @return the description of the shop item
     */
    String getDescription();

    /**
     * Get the price of the shop item.
     * 
     * @return the price of the shop item
     */
    int getPrice();

    /**
     * Get the type of the shop item.
     * 
     * @return the type of the shop item
     */
    ShopItemType getType();

    /**
     * Get the statistics boosted by the shop item.
     * 
     * @return a map of statistics and their corresponding boost values
     */
    Map<ShopItemStat, Double> getStats();

    /**
     * Apply the effect of the shop item to the given Alien.
     * 
     * @param alien the Alien to which the effect will be applied
     */
    void applyEffect(Alien alien);
}
