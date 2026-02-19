package it.unibo.model.shop.api;

public interface ActiveUpgrades {
    
    void updateValues();

    double getSpeedMultiplier();

    double getJumpMultiplier();

    int getCoinMultiplier();
}
