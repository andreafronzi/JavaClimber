package it.unibo.model.shop.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemStat;

/**
 * Implementation of {@link Inventory} interface
 */
public class InventoryImpl implements Inventory {

    private static final String DEFAULT_SKIN = "s_basic";
    private final ShopItemFactory factory;
    private final Set<String> ownedItems;
    private final Map<String,Integer> consumables;
    private String selectedSkin;
    private int selectedJumpLevel = 0;
    private int selectedSpeedLevel = 0;
    private Set<String> activeConsumables;

    /**
     * Initializes internal collections for tracking owned items, active consumables, and sets the default skin selection to none.
     */
    public InventoryImpl(ShopItemFactory factory) {
        this.factory = factory;
        this.ownedItems = new HashSet<>();
        this.consumables = new HashMap<>();
        this.selectedSkin = DEFAULT_SKIN;
        this.ownedItems.add(DEFAULT_SKIN);
        this.activeConsumables = new HashSet<>();
    }

    @Override
    public void addItem(String itemId) {
        this.ownedItems.add(itemId);

        factory.getItemById(itemId).ifPresent(item -> {
            switch (item.getType()) {
                case SKIN:
                    this.selectedSkin = itemId;
                    break;
                case PERMANENT_UPGRADE:
                    int level = extractLevel(itemId);
                    if (itemId.startsWith("pp_jump")) {
                        this.selectedJumpLevel = level;
                    } else if (itemId.startsWith("pp_speed")) {
                        this.selectedSpeedLevel = level;
                    }
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public boolean hasItem(String itemId) {
        return this.ownedItems.contains(itemId) || this.consumables.containsKey(itemId);
    }

    @Override
    public Set<String> getOwnedItems() {
        return Set.copyOf(this.ownedItems);
    }

    @Override
    public void equipSkin(String itemId) {
        if (this.ownedItems.contains(itemId)) {
            this.selectedSkin = itemId;
        }
    }

    @Override
    public void deselectSkin() {
        this.selectedSkin = DEFAULT_SKIN;
    }

    @Override
    public String getSelectedSkin() {
        return this.selectedSkin;
    }

    @Override
    public void addConsumable(String itemId, int matchesDuration) {
        this.consumables.put(itemId, matchesDuration);
        toggleConsumable(itemId, this.factory);
    }

    @Override
    public Map<String, Integer> getConsumablesStatus() {
        return Map.copyOf(this.consumables);
    }

    @Override
    public void updateConsumables() {
        final Iterator<Map.Entry<String, Integer>> it = this.consumables.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, Integer> entry = it.next();
            final String id = entry.getKey();
            if (activeConsumables.contains(id)) {
                final int remaining = entry.getValue() - 1;
                if (remaining <= 0) {
                    it.remove();
                } else {
                    entry.setValue(remaining);
                }
            }
        }
        activeConsumables.removeIf(id -> !consumables.containsKey(id));
    }

    @Override
    public void loadState(SaveState state) {
        this.activeConsumables.clear();
        this.ownedItems.clear();
        this.consumables.clear();

        this.ownedItems.addAll(state.getOwnedItems());
        this.consumables.putAll(state.getConsumables());
        this.ownedItems.add(DEFAULT_SKIN);
        this.selectedJumpLevel = state.getSelectedJumpLevel();
        this.selectedSpeedLevel = state.getSelectedSpeedLevel();

        if (state.getSelectedSkin() != null && !state.getSelectedSkin().isEmpty()) {
            this.selectedSkin = state.getSelectedSkin();
        } else {
            this.selectedSkin = DEFAULT_SKIN;
        }
    }

    @Override
    public int getSelectedJumpLevel() {
        return this.selectedJumpLevel;
    }

    @Override
    public void setSelectedJumpLevel(int level) {
        this.selectedJumpLevel = level;
    }

    @Override
    public int getSelectedSpeedLevel() {
        return this.selectedSpeedLevel;
    }

    @Override
    public void setSelectedSpeedLevel(int level) {
        this.selectedSpeedLevel = level;
    }

    @Override
    public void toggleConsumable(String itemId, ShopItemFactory factory) {
        if (activeConsumables.contains(itemId)) {
            activeConsumables.remove(itemId);
            return;
        }
        ShopItem item = factory.getItemById(itemId).orElseThrow();
        ShopItemStat stat_category = item.getStats().keySet().iterator().next();
        activeConsumables.removeIf(id -> {
            ShopItem activeItem = factory.getItemById(id).orElse(null);
            return activeItem != null && activeItem.getStats().containsKey(stat_category);
        });
        activeConsumables.add(itemId);
    }

    @Override
    public Set<String> getActiveConsumables() {
        return Set.copyOf(this.activeConsumables);
    }

    /**
     * Private method to extract the number of the power up from the id of the item.
     * @param itemId id of the item
     * @return number of current level
     */
    private int extractLevel(String itemId) {
        try {
            return Integer.parseInt(itemId.substring(itemId.lastIndexOf("_") + 1));
        } catch (Exception e) {
            return 0;
        }
    }

}
