package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

/**
 * Tests for {@link Inventory} interface.
 */
public class InventoryTest {

    private static final String DEFAULT_SKIN = "s_basic";
    private Inventory inventory;
    private ShopItemFactory factory;

    /**
     * Initializes inventory.
     */
    @BeforeEach
    void setUp() {
        factory = new ShopItemFactoryImpl();
        inventory = new InventoryImpl(factory);
    }

    /**
     * Verifies at the start the base skin is already present and selected.
     */
    @Test
    void testInitialization() {
        assertTrue(inventory.hasItem(DEFAULT_SKIN));
        assertTrue(inventory.getSelectedSkin().isPresent());
        assertEquals(DEFAULT_SKIN, inventory.getSelectedSkin().get());
    }

    /**
     * Tests item addition and get owned.
     */
    @Test
    void testAddAndHasItems() {
        inventory.addItem("s_astro");
        assertTrue(inventory.hasItem("s_astro"));
        assertFalse(inventory.hasItem("s_primitive"));
        assertEquals(2, inventory.getOwnedItems().size());
    }

    /**
     * Tests equipment and deselection logic for skin item (manually and automatically).
     */
    @Test
    void testSkinEquipment() {
        inventory.addItem("s_astro");
        inventory.equipSkin("s_astro");

        assertTrue(inventory.getSelectedSkin().isPresent());
        assertEquals("s_astro", inventory.getSelectedSkin().get());

        inventory.deselectSkin();
        assertTrue(inventory.getSelectedSkin().isPresent());
        assertEquals(DEFAULT_SKIN, inventory.getSelectedSkin().get());

        inventory.addItem("s_primitive");
        assertEquals("s_primitive", inventory.getSelectedSkin().get());
    }

    /**
     * Tests the temporary power up, equipment and expiration.
     */
    @Test
    void testConsumableAndDuration() {
        inventory.addConsumable("pt_jump1", 2);
        assertTrue(inventory.hasItem("pt_jump1"));
        assertTrue(inventory.getActiveConsumables().contains("pt_jump1"));
        
        inventory.updateConsumables();
        assertTrue(inventory.hasItem("pt_jump1"));

        inventory.updateConsumables();
        assertFalse(inventory.hasItem("pt_jump1"));
        assertFalse(inventory.getActiveConsumables().contains("pt_jump1"));
    }

    /**
     * Tests integrity and immutability of the map of consumables.
     */
    @Test
    void testConsumableStatus() {
        inventory.addConsumable("pt_jump1", 3);
        inventory.addConsumable("pt_speed1", 5);

        Map<String, Integer> status = inventory.getConsumablesStatus();
        assertEquals(2, status.size());
        assertEquals(3, status.get("pt_jump1"));
        assertEquals(5, status.get("pt_speed1"));

        inventory.updateConsumables();
        Map<String, Integer> updatedStatus = inventory.getConsumablesStatus();
        assertEquals(2, updatedStatus.get("pt_jump1"));
        assertEquals(4, updatedStatus.get("pt_speed1"));

        assertThrows(UnsupportedOperationException.class, () -> {
            status.put("hacker_item", 999);
        }, "The map cannot be modified from outside");
    }

    /**
     * Tests the behaviour of permanent power up that is different from temporary.
     */
    @Test
    void testPermanentPowerUp() {
        String permUpgradeId = "pp_speed_1";
        inventory.addItem(permUpgradeId);
        assertEquals(1, inventory.getSelectedSpeedLevel());

        for (int i = 0; i < 10; i++) {
            inventory.updateConsumables();
        }
        assertTrue(inventory.hasItem(permUpgradeId), "Permanent upgrade should not be removed with updates");
        assertTrue(inventory.getOwnedItems().contains(permUpgradeId));
    }

    /**
     * Tests that only one temporary power up for type can be active at same time.
     */
    @Test 
    void testExclusiveTempPowerUp() {
        inventory.addConsumable("pt_jump1", 3);
        inventory.addConsumable("pt_speed1", 5);
        inventory.addConsumable("pt_coin_1", 3);

        Set<String> active = inventory.getActiveConsumables();
        assertEquals(3, active.size());
        assertTrue(active.contains("pt_jump1"));
        assertTrue(active.contains("pt_speed1"));
        assertTrue(active.contains("pt_coin_1"));

        inventory.addConsumable("pt_jump2", 5);
        Set<String> newActive = inventory.getActiveConsumables();
        assertTrue(newActive.contains("pt_jump2"));
        assertFalse(newActive.contains("pt_jump1"));
        assertTrue(active.contains("pt_speed1"));
        assertTrue(active.contains("pt_coin_1"));
    }

    @Test
    void testLoadSave() {
        inventory.setSelectedJumpLevel(2);
        SaveState state = new SaveState(0, 0, Set.of("s_astro"), Map.of("pp_speed1", 0), "s_astro");
        inventory.loadState(state);

        assertTrue(inventory.hasItem("s_astro"));
        assertTrue(inventory.hasItem(DEFAULT_SKIN));
        assertEquals("s_astro", inventory.getSelectedSkin().get());
        assertTrue(inventory.getConsumablesStatus().containsKey("pp_speed1"));
        assertEquals(0, inventory.getSelectedJumpLevel());
        assertEquals(0, inventory.getSelectedSpeedLevel());
        assertTrue(inventory.getActiveConsumables().isEmpty());
    }

}
