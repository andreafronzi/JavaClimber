package it.unibo.model.shop.impl;

import java.util.Collections;
import java.util.Map;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Abstract implementation of {@link ShopItem} interface.
 */
public abstract class AbstractShopItem implements ShopItem{
    private final String id;
    private final String name;
    private final String description;
    private final int price;
    private final ShopItemType type;
    private final Map<ShopItemStat, Double> stats;

    /**
     * Constructor for AbstractShopItem.
     * 
     * @param id          the unique identifier of the shop item
     * @param name        the name of the shop item
     * @param description the description of the shop item
     * @param price       the price of the shop item
     * @param type        the type of the shop item
     * @param stats       the statistics boosted by the shop item
     */
    public AbstractShopItem(String id, String name, String description, int price, ShopItemType type,
            Map<ShopItemStat, Double> stats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.stats = Collections.unmodifiableMap(stats);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public ShopItemType getType() {
        return this.type;
    }

    @Override
    public Map<ShopItemStat, Double> getStats() {
        return this.stats;
    }

    @Override
    public abstract void applyEffect(Alien alien);
    
}
    

    
