package it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl;

public record AddOnSpawnRate(
        double chanceAddOn,
        double coinChance,
        int maxMoney,
        double monsterChance,
        int maxMonster,
        double gadgetChance,
        int maxGadget,
        double trapChance,   
        int maxTrap) {
}