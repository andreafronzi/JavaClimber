package it.unibo.model.worldConstructor.impl;

import it.unibo.model.worldConstructor.api.PlatformPool;

public record Difficult(double height,
        double maxDistanceY,
        double minDistanceY,
        double maxDistanceX,
        double chanceAddOn,
        double coinChance,
        int maxMoney,
        double monsterChance,
        int maxMonster,
        double gadgetChance,
        int maxGadget,
        double trapChance,   
        int maxTrap,
        PlatformPool platformPool) {
}
