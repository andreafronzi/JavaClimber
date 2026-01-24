package it.unibo.model.shop.impl;

import java.util.Map;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Implementation for stat upgrades, permanent or consumable.
 */
public class PowerUpItemImpl extends AbstractShopItem {

    private final int initialDuration;


    public PowerUpItemImpl(String id, String name, String description, int price, ShopItemType type,
            Map<ShopItemStat, Double> stats, final int duration) {
        super(id, name, description, price, type, stats);
        this.initialDuration = duration;
    }

    @Override
    public void applyEffect(Alien alien) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

    @Override
    public int getInitialDuration() {
        return this.initialDuration;
    }

}
