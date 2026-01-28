package it.unibo.controller.impl;

import java.util.Comparator;
import java.util.List;

import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopManager;

public class ShopControllerImpl implements ShopController {

    private final ShopManager shopManager;
    

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
    public void exit() {
        // TODO Auto-generated method stub(back to menu)
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }

    private void buyNextLevel(String prefix){
        shopManager.getPermanentUpgrades().stream()
                .filter(item -> item.getId().startsWith(prefix))
                .filter(item -> !shopManager.isAlreadyOwned(item))
                .sorted(Comparator.comparing(ShopItem::getId))
                .findFirst()
                .ifPresent(shopManager::buyItem);
    }
    
}
