package it.unibo.model.persistence.api;

import java.util.Map;
import java.util.Set;

/**
 * The entire game state to be saved.
 */
public class SaveState {

    private final int coins;
    private final int highestScore;
    private final Set<String> ownedItems;
    private final Map<String, Integer> consumables;
    private final String selectedSkin;

    public SaveState(int coins, int highestScore, Set<String> ownedItems, Map<String, Integer> consumables, String selectedSkin) {
        this.coins = coins;
        this.highestScore = highestScore;
        this.ownedItems = ownedItems;
        this.consumables = consumables;
        this.selectedSkin = selectedSkin;
    }

    /**
     * 
     * @return number of coin collected
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * 
     * @return the highest score of the player
     */
    public int getHighestScore() {
        return this.highestScore;
    }

    /**
     * 
     * @return a set of owned item IDs
     */
    public Set<String> getOwnedItems() {
        return this.ownedItems;
    }

    /**
     * 
     * @return a map of consumable item IDs to their remaining matches
     */
    public Map<String, Integer> getConsumables() {
        return this.consumables;
    }

    /**
     * 
     * @return an Optional containing the selected skin ID, or empty if none is selected
     */
    public String getSelectedSkin() {
        return this.selectedSkin;
    }
    
}
