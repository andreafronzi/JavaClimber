package it.unibo.model.shop.impl;

import java.util.List;

import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemType;
import it.unibo.model.shop.api.ShopManager;

/**
 * Implementation of {@link ScoreManager} interface.
 */
public class ShopManagerImpl implements ShopManager {

    private final ShopItemFactory itemFactory;
    private final Inventory inventory;
    private final ScoreManager scoreManager;
    private final SaveManager storage;

    public ShopManagerImpl(ShopItemFactory itemFactory, Inventory inventory, ScoreManager scoreManager, SaveManager storage) {
        this.itemFactory = itemFactory;
        this.inventory = inventory;
        this.scoreManager = scoreManager;
        this.storage = storage;
    }

    @Override
    public List<ShopItem> getCatalog() {
        return itemFactory.getAllItems();
    }

    @Override
    public boolean buyItem(ShopItem item) {
        if (canBuyItem(item)) {
            scoreManager.spend(item.getPrice());

            switch (item.getType()) {
                case SKIN:
                    inventory.addItem(item.getId());
                    inventory.equipSkin(item.getId());
                    break;
                case PERMANENT_UPGRADE:
                    inventory.addItem(item.getId());
                    break;
                case TEMPORARY_UPGRADE:
                    inventory.addConsumable(item.getId(), item.getInitialDuration());
                    break;
                default:
                    throw new IllegalStateException("Unknown item type: " + item.getType());
            }

            SaveState currentState = new SaveState(scoreManager.getCoins(), scoreManager.getHighScore(), inventory.getOwnedItems(), inventory.getConsumablesStatus(), inventory.getSelectedSkin().orElse(null));
            storage.save(currentState);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAlreadyOwned(ShopItem item) {
        return inventory.hasItem(item.getId());
    }

    @Override
    public boolean canBuyItem(ShopItem item) {
        boolean hasEnough = scoreManager.getCoins() >= item.getPrice();

        if (item.getType() == ShopItemType.SKIN || item.getType() == ShopItemType.PERMANENT_UPGRADE) {
            return hasEnough && !isAlreadyOwned(item);
        }

        return hasEnough;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

}
