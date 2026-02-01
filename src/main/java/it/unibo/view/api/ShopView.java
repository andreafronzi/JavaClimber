package it.unibo.view.api;

import java.util.List;

import it.unibo.model.shop.api.ShopItem;

/**
 * Interface representic the Shop View
 */
public interface ShopView {

    void display();

    void updateCoins(int coins);

    void updateItems(List<ShopItem> skins, List<ShopItem> permUpgrades, List<ShopItem> tempUpgrades);

    void close();
}
