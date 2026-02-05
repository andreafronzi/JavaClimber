package it.unibo.view.api;

import java.util.List;

import it.unibo.model.shop.api.ShopItem;

public interface InventoryView {

    void display();
    
    void updateInventory(List<ShopItem> ownedSkins, String equippedSkinId, int selectedJump, int maxJump, int selectedSpeed, int maxSpeed, List<ShopItem> ownedTemp, List<Boolean> tempStatus);
    
    void updateCoins(int coins);
    
    void close();
}
