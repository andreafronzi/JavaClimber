package it.unibo.model.worldConstructor.data;

import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.api.AddOnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.api.SpawnPool;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.Distance;

/**
 * Record representing the configuration for a specific difficulty level.
 * 
 * @param height the height at which this difficulty becomes active
 * @param maxDistanceY maximum vertical distance between platforms
 * @param minDistanceY minimum vertical distance between platforms
 * @param maxDistanceX maximum horizontal distance for platform placement
 * @param chanceAddOn probability of an add-on appearing on a platform
 * @param coinChance probability of a coin spawning
 * @param maxMoney maximum amount of money allowed
 * @param monsterChance probability of a monster spawning
 * @param maxMonster maximum number of monsters allowed
 * @param gadgetChance probability of a gadget spawning
 * @param maxGadget maximum number of gadgets allowed
 * @param trapChance probability of a trap spawning
 * @param maxTrap maximum number of traps allowed
 * @param platformPool the pool of objects available for this difficulty
 */
public record Difficult(double height,
        Distance distance,
        AddOnPool addOnPool,
        SpawnPool platformPool) {
}
