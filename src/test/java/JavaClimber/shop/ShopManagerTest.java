package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopManager;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.shop.impl.ShopManagerImpl;

/**
 * Tests for {@link ScoreManager}.
 */
public class ShopManagerTest {

    private ScoreManager scoreManager = new ScoreManagerImpl(100);
    private ShopItemFactory itemFactory = new ShopItemFactoryImpl();
    private ShopManager shopManager = new ShopManagerImpl(itemFactory, new InventoryImpl(), scoreManager);

    /**
     * Tests a successfull skin purchase.
     * Verifies:
     * Skin is owned in the inventory
     * Skin is automatically selected
     * ScoreManager has correct coin balance
     */
    @Test
    void testSuccessfulSkinPurchase() {
        scoreManager.addCoins(1000);
        ShopItem skin = itemFactory.getItemById("s_astro").get();
        assertTrue(shopManager.buyItem(skin));
        assertEquals(500, scoreManager.getCoins());
        assertTrue(shopManager.isAlreadyOwned(skin));
        assertEquals("s_astro", shopManager.getInventory().getSelectedSkin().get());
    }

    /**
     * Tests a successfull temporary power up purchase.
     * Verifies that the item is added to the map with correct initial duration
     */
    @Test
    void testSuccessfulTemporaryPWRPurchase() {
        scoreManager.addCoins(500);
        ShopItem temp_pwr = itemFactory.getItemById("pt_jump1").get();
        assertTrue(shopManager.buyItem(temp_pwr));
        assertEquals(450, scoreManager.getCoins());
        Integer duration = shopManager.getInventory().getConsumablesStatus().get("pt_jump1");
        assertNotNull(duration);
        assertEquals(3, duration);
    }

    /**
     * Tests a successfull permanent power up purchase.
     * Verifies:
     * Item is owned in the inventory
     * Item is not treated as temporary consumable
     * ScoreManager has correct coin balance
     * Purchase has not effect to selected skin
     */
    @Test
    void testSuccessfulPermanentPWRPurchase() {
        scoreManager.addCoins(1000);
        ShopItem perm_pwr = itemFactory.getItemById("pp_speed1").get();
        assertTrue(shopManager.buyItem(perm_pwr));
        assertEquals(500, scoreManager.getCoins());
        assertTrue(shopManager.isAlreadyOwned(perm_pwr));
        assertTrue(shopManager.getInventory().getOwnedItems().contains("pp_speed1"));
        assertFalse(shopManager.getInventory().getConsumablesStatus().containsKey("pp_speed1"));
        assertTrue(shopManager.getInventory().getSelectedSkin().isEmpty());
    }

    /**
     * Tests the failure purchase for insufficient coins.
     * Verifies that no transaction occurs
     */
    @Test
    void testFailedPurchaseInsufficientCoins() {
        scoreManager.addCoins(10);
        ShopItem item = itemFactory.getItemById("s_primitive").get();
        assertFalse(shopManager.buyItem(item));
        assertEquals(10, scoreManager.getCoins());
        assertFalse(shopManager.isAlreadyOwned(item));
    }

    /**
     * Tests that can't be bought the same skin twice.
     * Verifies that the purchase is blocked
     */
    @Test
    void testCannotBuySkinTwice() {
        scoreManager.addCoins(100);
        ShopItem item = itemFactory.getItemById("s_primitive").get();
        assertTrue(shopManager.buyItem(item));
        
        assertFalse(shopManager.canBuyItem(item));
        assertFalse(shopManager.buyItem(item));
    }
}
