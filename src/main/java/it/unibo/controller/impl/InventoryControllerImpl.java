package it.unibo.controller.impl;

import java.util.List;

import it.unibo.controller.api.InventoryController;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;

/**
 * Implementation of {@link InventoryController} interface.
 */
public class InventoryControllerImpl implements InventoryController {

    private final Inventory inventory;
    private final ShopItemFactory factory;

    /**
     * Construct a InventoryControllerImpl with required model and factory.
     * @param inventory the model inventory
     * @param factory the factory for items
     */
    public InventoryControllerImpl(Inventory inventory, ShopItemFactory factory) {
        this.inventory = inventory;
        this.factory = factory;
    }

    @Override
    public void selectSkin(int index) {
        List<ShopItem> allSkins = factory.getSkins();
        if (isValidIndex(index, allSkins)) {
            String skinId = allSkins.get(index).getId();
            if (inventory.hasItem(skinId)) {
                inventory.equipSkin(skinId);
            }
        }
    }

    @Override
    public void plusJump() {
        int current = inventory.getSelectedJumpLevel();
        if (current < getMaxLevelOwned("pp_jump")) {
            inventory.setSelectedJumpLevel(current + 1);
        }
    }

    @Override
    public void minusJump() {
        int current = inventory.getSelectedJumpLevel();
        if (current > 0) {
            inventory.setSelectedJumpLevel(current - 1);
        }
    }

    @Override
    public void plusVelocity() {
        int current = inventory.getSelectedSpeedLevel();
        if (current < getMaxLevelOwned("pp_speed")) {
            inventory.setSelectedSpeedLevel(current + 1);
        }
    }

    @Override
    public void minusVelocity() {
        int current = inventory.getSelectedSpeedLevel();
        if (current > 0) {
            inventory.setSelectedSpeedLevel(current - 1);
        }
    }

    @Override
    public void toggleTemporaryItem(int index) {
        List<String> consumablesId = inventory.getConsumablesStatus().keySet().stream().toList();
        if (isValidIndex(index, consumablesId)) {
            inventory.toggleConsumable(consumablesId.get(index), factory);
        }
    }

    @Override
    public void openShop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openShop'");
    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub (to menù)
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }
    
    /**
     * Validate if the index is within the bounds of a list.
     * @param index the index to check
     * @param list the list to check
     * @return true if index is valid, false otherwise
     */
    private boolean isValidIndex(int index, List<?> list) {
        return index >= 0 && index < list.size();
    }

    /**
     * Calculate highest level currently owned for specific power ups.
     * Takes the level number from the item Id.
     * @param prefix the Id prefix for a type
     * @return the maximum level find, or 0 if no items are owned
     */
    private int getMaxLevelOwned(String prefix) {
        return inventory.getOwnedItems().stream()
                .filter(id -> id.startsWith(prefix))
                .mapToInt(id -> Integer.parseInt(id.substring(id.lastIndexOf("_") + 1)))
                .max()
                .orElse(0);
    }
}
