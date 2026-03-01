package it.unibo.controller.impl;

import java.util.Comparator;
import java.util.List;

import it.unibo.controller.api.MainController;
import it.unibo.controller.api.ShopController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopManager;
import it.unibo.view.shop.api.ShopView;

/**
 * Implementation of {@link ShopController} interface.
 */
public class ShopControllerImpl implements ShopController {

    private final Menu menu;
    private final ShopManager shopManager;
    private final MainController mainController;
    private ShopView view;
    
    /**
     * Construct new ShopControllerImpl with specified shop manager.
     * @param mainController the main controller for managing view transitions and saving progress
     * @param shopManager the model manager
     */
    public ShopControllerImpl(final MainController mainController, final ShopManager shopManager) {
        this.mainController = mainController;
        this.shopManager = shopManager;
        this.menu = new MenuImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final ShopView view) {
        this.view = view;
        refreshView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeJump() {
        buyNextLevel("pp_jump");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeSpeed() {
        buyNextLevel("pp_speed");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyTemporaryItem(int index) {
        List<ShopItem> tempItems = shopManager.getTemporaryUpgrades();
        if (index >= 0 && index < tempItems.size()) {
            boolean success = shopManager.buyItem(tempItems.get(index));
            if (success) {
                this.mainController.saveProgress();
                refreshView();
            } else if (this.view != null) {
                this.view.showMessage("Non hai abbastanza monete per acquistare questo potenziamento temporaneo");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buySkin(int index) {
        List<ShopItem> skins = shopManager.getSkins();
        if (index >= 0 && index < skins.size()) {
            boolean success = shopManager.buyItem(skins.get(index));
            if (success) {
                this.mainController.saveProgress();
                refreshView();
            } else if (this.view != null) {
                this.view.showMessage("Non hai abbastanza monete per acquistare questa skin");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return shopManager.getCoins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPermanetUpgrades() {
        return shopManager.getPermanentUpgrades();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getSkins() {
        return shopManager.getSkins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getTemporaryUpgrades() {
        return shopManager.getTemporaryUpgrades();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOwned(ShopItem item) {
        return shopManager.isAlreadyOwned(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentLevel(String prefix) {
        return (int) shopManager.getPermanentUpgrades().stream()
                .filter(item -> item.getId().startsWith(prefix))
                .filter(shopManager::isAlreadyOwned)
                .count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShop() {
        mainController.openShopView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventory() {
        menu.setState(new InventoryState(menu));
        mainController.openInventoryView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        menu.setState(new MenuState(menu));
        mainController.openMenuView();
    }

    /**
     * Refresh the shop view with current data from the model.
     */
    private void refreshView() {
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
                        this.mainController.saveProgress();
                        refreshView();
                    }  else if (this.view != null) {
                        this.view.showMessage("Non hai abbastanza monete per acquistare questo potenziamento permanente");
                    }
                });
    }
    
}
