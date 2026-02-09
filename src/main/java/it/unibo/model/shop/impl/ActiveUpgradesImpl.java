package it.unibo.model.shop.impl;

import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;

public class ActiveUpgradesImpl implements ActiveUpgrades {

    private final Inventory inventory;
    private final ShopItemFactory factory;
    private double currentSpeedMultiplier;
    private double currentJumpMultiplier;
    private int currentCoinMultiplier;

    public ActiveUpgradesImpl(Inventory inventory, ShopItemFactory factory) {
        this.inventory = inventory;
        this.factory = factory;
        this.updateValues();
    }

    @Override
    public void updateValues() {
        this.currentJumpMultiplier = 1.0;
        this.currentSpeedMultiplier = 1.0;
        this.currentCoinMultiplier = 1;

        for (String itemId : inventory.getActiveConsumables()) {
            factory.getItemById(itemId).ifPresent(item -> {
                item.getStats().forEach((stat, value) -> {
                    switch (stat) {
                        case SPEED:
                            this.currentSpeedMultiplier = Math.max(this.currentSpeedMultiplier, value);
                            break;
                        case JUMP_HEIGHT:
                            this.currentJumpMultiplier = Math.max(this.currentJumpMultiplier, value);
                            break;
                        case COIN_MULTIPLIER:
                            this.currentCoinMultiplier = Math.max(this.currentCoinMultiplier, value.intValue());
                            break;
                        default:
                            break;
                    }
                });
            });
        }
    }

    @Override
    public double getSpeedMultiplier() {
        return this.currentSpeedMultiplier;
    }

    @Override
    public double getJumpMultiplier() {
        return this.currentJumpMultiplier;
    }

    @Override
    public int getCoinMultiplier() {
        return this.currentCoinMultiplier;
    }

}
