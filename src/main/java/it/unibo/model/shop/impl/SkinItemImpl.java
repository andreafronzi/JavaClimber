package it.unibo.model.shop.impl;

import java.util.Map;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Implementation for skins that provide permenent upgrades.
 */
public class SkinItemImpl extends AbstractShopItem {

    public SkinItemImpl(String id, String name, String description, int price, ShopItemType type,
            Map<ShopItemStat, Double> stats) {
        super(id, name, description, price, ShopItemType.SKIN, stats);
    }

    @Override
    public void applyEffect(Alien alien) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

    @Override
    public int getInitialDuration() {
        return 0;
    }

}
