package it.unibo.controller.impl;

import java.util.List;
import java.util.Set;

import it.unibo.controller.api.InventoryController;
import it.unibo.controller.api.MainController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.model.menu.impl.ShoppingState;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.view.inventory.api.InventoryView;

/**
 * Implementation of {@link InventoryController} interface.
 */
public class InventoryControllerImpl implements InventoryController {

    private final MainController mainController;
    private final Inventory inventory;
    private InventoryView view;
    private final ShopItemFactory factory;
    private final Menu menu;

    /**
     * Construct a InventoryControllerImpl with required model and factory.
     * @param mainController the main controller for managing view transitions and saving progress
     * @param inventory the model inventory
     * @param factory the factory for items
     */
    public InventoryControllerImpl(final MainController mainController, final Inventory inventory, final ShopItemFactory factory) {
        this.mainController = mainController;
        this.inventory = inventory;
        this.factory = factory;
        this.menu = new MenuImpl(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(InventoryView view) {
        this.view = view;
        refreshView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectSkin(int index) {
        List<ShopItem> ownedSkin = this.getOwnedSkins();
        if (isValidIndex(index, ownedSkin)) {
            String skinId = ownedSkin.get(index).getId();
            inventory.equipSkin(skinId);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void plusJump() {
        int current = inventory.getSelectedJumpLevel();
        if (current < getMaxLevelOwned("pp_jump")) {
            inventory.setSelectedJumpLevel(current + 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void minusJump() {
        int current = inventory.getSelectedJumpLevel();
        if (current > 0) {
            inventory.setSelectedJumpLevel(current - 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void plusVelocity() {
        int current = inventory.getSelectedSpeedLevel();
        if (current < getMaxLevelOwned("pp_speed")) {
            inventory.setSelectedSpeedLevel(current + 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void minusVelocity() {
        int current = inventory.getSelectedSpeedLevel();
        if (current > 0) {
            inventory.setSelectedSpeedLevel(current - 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleTemporaryItem(int index) {
        List<String> consumablesId = inventory.getConsumablesStatus().keySet().stream().sorted().toList();
        if (isValidIndex(index, consumablesId)) {
            inventory.toggleConsumable(consumablesId.get(index), factory);
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEquippedSkin() {
        return inventory.getSelectedSkin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxJumpLevelOwned() {
        return getMaxLevelOwned("pp_jump");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxSpeedLevelOwned() {
        return getMaxLevelOwned("pp_speed");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getOwnedSkins() {
        return factory.getSkins().stream()
                .filter(i -> inventory.hasItem(i.getId()))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getOwnedTempItems() {
        return inventory.getConsumablesStatus().keySet().stream()
                .sorted()
                .map(id -> factory.getItemById(id).orElseThrow())
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedJumpLevel() {
        return inventory.getSelectedJumpLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedSpeedLevel() {
        return inventory.getSelectedSpeedLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> getTempItemsStatus() {
        Set<String> active = inventory.getActiveConsumables();
        return inventory.getConsumablesStatus().keySet().stream()
                .sorted()
                .map(id -> active.contains(id))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventory() {
        menu.setState(new InventoryState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShop() {
        menu.setState(new ShoppingState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        menu.setState(new MenuState(menu));
    }
    
    /**
     * Refresh the inventory view with current data from the model.
     */
    private void refreshView() {
        if (view != null) {
            List<ShopItem> ownedSkin = getOwnedSkins();
            String equippedSkin = getEquippedSkin();
            List<ShopItem> allPermItems = factory.getPowerUpsPermanent();
            int selectedJump = getSelectedJumpLevel();
            int maxJump = getMaxJumpLevelOwned();
            int selectedSpeed = getSelectedSpeedLevel();
            int maxSpeed = getMaxSpeedLevelOwned();
            List<ShopItem> ownedTempItems = getOwnedTempItems();
            List<Boolean> tempItemsStatus = getTempItemsStatus();
            this.view.updateInventory(ownedSkin, equippedSkin, allPermItems, selectedJump, maxJump, selectedSpeed, maxSpeed, ownedTempItems, tempItemsStatus);
            this.view.updateCoins(inventory.getTotalCoins());
        }
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
