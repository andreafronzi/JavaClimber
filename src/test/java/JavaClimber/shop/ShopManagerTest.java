package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.Inventory;
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

    private ShopItemFactory itemFactory = new ShopItemFactoryImpl();
    private Inventory inventory = new InventoryImpl(itemFactory);
    private ShopManager shopManager = new ShopManagerImpl(itemFactory, inventory);

    /**
     * Tests a successfull skin purchase.
     * Verifies:
     * Skin is owned in the inventory
     * Skin is automatically selected
     * ScoreManager has correct coin balance
     */
    @Test
    void testSuccessfulSkinPurchase() {
        inventory.addCoins(1000);
        ShopItem skin = itemFactory.getItemById("s_astro").get();
        assertTrue(shopManager.buyItem(skin));
        assertEquals(500, shopManager.getCoins());
        assertTrue(shopManager.isAlreadyOwned(skin));
        assertEquals("s_astro", shopManager.getInventory().getSelectedSkin());
    }

    /**
     * Tests a successfull temporary power up purchase.
     * Verifies that the item is added to the map with correct initial duration
     */
    @Test
    void testSuccessfulTemporaryPWRPurchase() {
        inventory.addCoins(500);
        ShopItem temp_pwr = itemFactory.getItemById("pt_jump1").get();
        assertTrue(shopManager.buyItem(temp_pwr));
        assertEquals(450, shopManager.getCoins());
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
        inventory.addCoins(1000);
        ShopItem perm_pwr = itemFactory.getItemById("pp_speed_1").get();
        assertTrue(shopManager.buyItem(perm_pwr));
        assertEquals(700, shopManager.getCoins());
        assertTrue(shopManager.isAlreadyOwned(perm_pwr));
        assertTrue(shopManager.getInventory().getOwnedItems().contains("pp_speed_1"));
        assertFalse(shopManager.getInventory().getConsumablesStatus().containsKey("pp_speed_1"));
    }

    /**
     * Tests the correct sequential for permanent power up.
     * Verifies that an higher level upgrade can't be purchased if the previus level isn't owned
     */
    @Test
    void testSequentialPermanentUpgrade() {
        inventory.addCoins(1000);
        ShopItem speedLevel1 = itemFactory.getItemById("pp_speed_1").get();
        ShopItem speedLevel2 = itemFactory.getItemById("pp_speed_2").get();

        assertFalse(shopManager.canBuyItem(speedLevel2));
        assertFalse(shopManager.buyItem(speedLevel2));

        assertTrue(shopManager.buyItem(speedLevel1));
        
        assertTrue(shopManager.canBuyItem(speedLevel2));
        assertTrue(shopManager.buyItem(speedLevel2));
        assertEquals(200, shopManager.getCoins());
    }

    /**
     * Tests the failure purchase for insufficient coins.
     * Verifies that no transaction occurs
     */
    @Test
    void testFailedPurchaseInsufficientCoins() {
        inventory.addCoins(10);
        ShopItem item = itemFactory.getItemById("s_astro").get();
        assertFalse(shopManager.buyItem(item));
        assertEquals(10, shopManager.getCoins());
        assertFalse(shopManager.isAlreadyOwned(item));
    }

    /**
     * Tests that can't be bought the same skin twice.
     * Verifies that the purchase is blocked
     */
    @Test
    void testCannotBuySkinTwice() {
        inventory.addCoins(100);
        ShopItem item = itemFactory.getItemById("s_sub").get();
        assertTrue(shopManager.buyItem(item));
        
        assertFalse(shopManager.canBuyItem(item));
        assertFalse(shopManager.buyItem(item));
    }
}
