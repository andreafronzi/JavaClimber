package it.unibo.model.shop.impl;

import java.util.List;

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

    /**
     * Constructor for ShopManagerImpl with required factory and inventory.
     * @param itemFactory the factory for shop items
     * @param inventory the inventory to manage purchases and ownership
     */
    public ShopManagerImpl(ShopItemFactory itemFactory, Inventory inventory) {
        this.itemFactory = itemFactory;
        this.inventory = inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getCatalog() {
        return this.itemFactory.getAllItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(ShopItem item) {
        if (canBuyItem(item)) {
            boolean transactionSuccess = inventory.spendCoins(item.getPrice());  
            if (!transactionSuccess) {
                return false;
            }

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
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyOwned(ShopItem item) {
        return inventory.hasItem(item.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBuyItem(ShopItem item) {
        boolean hasEnough = inventory.getTotalCoins() >= item.getPrice();

        if (item.getType() == ShopItemType.SKIN || item.getType() == ShopItemType.PERMANENT_UPGRADE) {
            if (hasEnough && !isAlreadyOwned(item)) {
                if (item.getType() == ShopItemType.PERMANENT_UPGRADE) {
                    return checkSequentialPermanentUpgrade(item);
                }
                return true;
            }
            return false;
        }
        return hasEnough;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Check if we're following the right sequence of purchasing of progressive Id for permanent power ups.
     * @param item the object to check
     * @return true if the sequence is correct, false otherwise
     */
    private boolean checkSequentialPermanentUpgrade(ShopItem item) {
        String id = item.getId();
        int lastUnderscore = id.lastIndexOf("_");
        if (lastUnderscore == -1) {
            return true;
        }
        try {
            String prefix = id.substring(0, lastUnderscore + 1);
            String levelPart = id.substring(lastUnderscore + 1);
            int level = Integer.parseInt(levelPart);
            if (level > 1) {
                String prevLevel = prefix + (level - 1);
                return inventory.hasItem(prevLevel);
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getSkins() {
        return this.itemFactory.getSkins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPermanentUpgrades() {
        return this.itemFactory.getPowerUpsPermanent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getTemporaryUpgrades() {
        return this.itemFactory.getPowerUpsTemporary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return inventory.getTotalCoins();
    }

}
