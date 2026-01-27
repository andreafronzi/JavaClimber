package it.unibo.model.shop.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.shop.api.Inventory;

/**
 * Implementation of {@link Inventory} interface
 */
public class InventoryImpl implements Inventory {

    private static final String DEFAULT_SKIN = "s_basic";
    private final Set<String> ownedItems;
    private final Map<String,Integer> consumables;
    private String selectedSkin;

    /**
     * Initializes internal collections for tracking owned items, active consumables, and sets the default skin selection to none.
     */
    public InventoryImpl() {
        this.ownedItems = new HashSet<>();
        this.consumables = new HashMap<>();
        this.selectedSkin = DEFAULT_SKIN;
        this.ownedItems.add(DEFAULT_SKIN);
    }

    @Override
    public void addItem(String itemId) {
        this.ownedItems.add(itemId);
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
    public Optional<String> getSelectedSkin() {
        return Optional.ofNullable(this.selectedSkin);
    }

    @Override
    public void addConsumable(String itemId, int matchesDuration) {
        this.consumables.put(itemId, matchesDuration);
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
            final int remaining = entry.getValue() - 1;
            if (remaining <= 0) {
                it.remove();
            } else {
                entry.setValue(remaining);
            }
        }
    }

    @Override
    public void loadState(SaveState state) {
        this.ownedItems.clear();
        this.consumables.clear();

        this.ownedItems.addAll(state.getOwnedItems());
        this.consumables.putAll(state.getConsumables());
        this.ownedItems.add(DEFAULT_SKIN);

        if (state.getSelectedSkin() != null && !state.getSelectedSkin().isEmpty()) {
            this.selectedSkin = state.getSelectedSkin();
        } else {
            this.selectedSkin = DEFAULT_SKIN;
        }
    }

}
