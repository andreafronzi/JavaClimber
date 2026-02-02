package it.unibo.controller.impl;

import java.util.Comparator;
import java.util.List;

import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopManager;

/**
 * Implementation of {@link ShopController} interface.
 */
public class ShopControllerImpl implements ShopController {

    private final ShopManager shopManager;
    
    /**
     * Construct new ShopControllerImpl with specified shop manager.
     * @param shopManager the model manager
     */
    public ShopControllerImpl(final ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @Override
    public void upgradeJump() {
        buyNextLevel("pp_jump");
    }

    @Override
    public void upgradeSpeed() {
        buyNextLevel("pp_speed");
    }

    @Override
    public void buyTemporaryItem(int index) {
        List<ShopItem> tempItems = shopManager.getTemporaryUpgrades();
        if (index >= 0 && index < tempItems.size()) {
            shopManager.buyItem(tempItems.get(index));
        }
    }

    @Override
    public void buySkin(int index) {
        List<ShopItem> skins = shopManager.getSkins();
        if (index >= 0 && index < skins.size()) {
            shopManager.buyItem(skins.get(index));
        }
    }

    @Override
    public boolean isOwned(ShopItem item) {
        return shopManager.isAlreadyOwned(item);
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub(back to menu)
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }

    /**
     * Identifies and attemps to purchase the next avaiable level for a specific type of permanent power ups.
     * @param prefix the Id prefix for the power up type
     */
    private void buyNextLevel(String prefix){
        shopManager.getPermanentUpgrades().stream()
                .filter(item -> item.getId().startsWith(prefix))
                .filter(item -> !shopManager.isAlreadyOwned(item))
                .sorted(Comparator.comparing(ShopItem::getId))
                .findFirst()
                .ifPresent(shopManager::buyItem);
    }
    
}
