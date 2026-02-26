package it.unibo.controller.impl;

import java.util.Comparator;
import java.util.List;

import it.unibo.controller.api.MainController;
import it.unibo.controller.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopManager;
import it.unibo.view.shop.api.ShopView;

/**
 * Implementation of {@link ShopController} interface.
 */
public class ShopControllerImpl implements ShopController {

    private final ShopManager shopManager;
    private final MainController mainController;
    private ShopView view;
    
    /**
     * Construct new ShopControllerImpl with specified shop manager.
     * @param shopManager the model manager
     */
    public ShopControllerImpl(final MainController mainController, final ShopManager shopManager) {
        this.mainController = mainController;
        this.shopManager = shopManager;
    }

    public void setView(final ShopView view) {
        this.view = view;
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
            boolean success = shopManager.buyItem(tempItems.get(index));
            if (success) {
                refreshView();
            }
        }
    }

    @Override
    public void buySkin(int index) {
        List<ShopItem> skins = shopManager.getSkins();
        if (index >= 0 && index < skins.size()) {
            boolean success = shopManager.buyItem(skins.get(index));
            if (success) {
                refreshView();
            }
        }
    }

    @Override
    public int getCoins() {
        return shopManager.getCoins();
    }

    @Override
    public List<ShopItem> getPermanetUpgrades() {
        return shopManager.getPermanentUpgrades();
    }

    @Override
    public List<ShopItem> getSkins() {
        return shopManager.getSkins();
    }

    @Override
    public List<ShopItem> getTemporaryUpgrades() {
        return shopManager.getTemporaryUpgrades();
    }

    @Override
    public boolean isOwned(ShopItem item) {
        return shopManager.isAlreadyOwned(item);
    }

    @Override
    public int getCurrentLevel(String prefix) {
        return (int) shopManager.getPermanentUpgrades().stream()
                .filter(item -> item.getId().startsWith(prefix))
                .filter(shopManager::isAlreadyOwned)
                .count();
    }

    @Override
    public void openShop() {
        mainController.openShopView();
    }

    @Override
    public void openInventory() {
        mainController.openInventoryView();
    }

    @Override
    public void exit() {
        mainController.openMenuView();
    }

    public void refreshView() {
        if (view != null) {
            view.updateItems(getSkins(), getPermanetUpgrades(), getTemporaryUpgrades());
            view.updateCoins(getCoins());
        }
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
                .ifPresent(item -> {
                    boolean success = shopManager.buyItem(item);
                    if (success) {
                        refreshView();
                    }
                });
    }
    
}
