package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;
import it.unibo.model.shop.impl.PowerUpItemImpl;
import it.unibo.model.shop.impl.SkinItemImpl;

public class ShopItemTest {

    @Test
    void testSkinItemCreation() {
        Map<ShopItemStat, Double> stats = Map.of(ShopItemStat.SPEED, 1.2);
        ShopItem skin = new SkinItemImpl("skin_redAlien", "Red Skin", "Desc", 100, stats);

        assertEquals("skin_redAlien", skin.getId());
        assertEquals("Red Skin", skin.getName());
        assertEquals(ShopItemType.SKIN, skin.getType());
        assertEquals("Desc", skin.getDescription());
        assertEquals(100, skin.getPrice());
        assertEquals(0, skin.getInitialDuration());
        assertTrue(skin.getStats().containsKey(ShopItemStat.SPEED));
    }

    @Test
    void testPowerUpItemImplementation() {
        Map<ShopItemStat, Double> jumpStats = Map.of(ShopItemStat.JUMP_HEIGHT, 1.5);
        Map<ShopItemStat, Double> speedStats = Map.of(ShopItemStat.SPEED, 1.2);
        Map<ShopItemStat, Double> coinStats = Map.of(ShopItemStat.COIN_MULTIPLIER, 2.0);

        ShopItem tempPowerUp = new PowerUpItemImpl("temp_jump", "Jump Boost", "Temporary", 50, ShopItemType.TEMPORARY_UPGRADE, jumpStats, 3);

        assertEquals("temp_jump", tempPowerUp.getId());
        assertEquals("Jump Boost", tempPowerUp.getName());
        assertEquals(ShopItemType.TEMPORARY_UPGRADE, tempPowerUp.getType());
        assertEquals("Temporary", tempPowerUp.getDescription());
        assertEquals(50, tempPowerUp.getPrice());
        assertEquals(3, tempPowerUp.getInitialDuration());
        assertTrue(tempPowerUp.getStats().containsKey(ShopItemStat.JUMP_HEIGHT));

        ShopItem permPowerUp = new PowerUpItemImpl("perm_speed", "Speed Boost", "Permanent", 200, ShopItemType.PERMANENT_UPGRADE, speedStats, 0);

        assertEquals("perm_speed", permPowerUp.getId());
        assertEquals("Speed Boost", permPowerUp.getName());
        assertEquals(ShopItemType.PERMANENT_UPGRADE, permPowerUp.getType());
        assertEquals("Permanent", permPowerUp.getDescription());
        assertEquals(200, permPowerUp.getPrice());
        assertEquals(0, permPowerUp.getInitialDuration());
        assertTrue(permPowerUp.getStats().containsKey(ShopItemStat.SPEED));

        ShopItem coinPowerUp = new PowerUpItemImpl("coin_x2", "Double Coins", "Collect double money", 300, ShopItemType.TEMPORARY_UPGRADE, coinStats, 5);

        assertEquals("coin_x2", coinPowerUp.getId());
        assertEquals("Double Coins", coinPowerUp.getName());
        assertEquals(ShopItemType.TEMPORARY_UPGRADE, coinPowerUp.getType());
        assertEquals("Collect double money", coinPowerUp.getDescription());
        assertEquals(300, coinPowerUp.getPrice());
        assertEquals(5, coinPowerUp.getInitialDuration());
        assertTrue(coinPowerUp.getStats().containsKey(ShopItemStat.COIN_MULTIPLIER));
    }
}
