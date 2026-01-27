package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.impl.InventoryImpl;

/**
 * Tests for {@link Inventory} interface.
 */
public class InventoryTest {

    private Inventory inventory;

    /**
     * Initializes inventory.
     */
    @BeforeEach
    void setUp() {
        inventory = new InventoryImpl();
    }

    /**
     * Tests item addition and get owned.
     */
    @Test
    void testAddAndHasItems() {
        inventory.addItem("skin_redAlien");
        assertTrue(inventory.hasItem("skin_redAlien"));
        assertFalse(inventory.hasItem("skin_greenAlien"));
        assertEquals(1, inventory.getOwnedItems().size());
    }

    /**
     * Tests equipment and deselection logic for skin item.
     */
    @Test
    void testSkinEquipment() {
        assertTrue(inventory.getSelectedSkin().isEmpty());
        inventory.addItem("skin_blueAlien");
        inventory.equipSkin("skin_blueAlien");

        assertTrue(inventory.getSelectedSkin().isPresent());
        assertEquals("skin_blueAlien", inventory.getSelectedSkin().get());

        inventory.deselectSkin();
        assertTrue(inventory.getSelectedSkin().isEmpty());
    }

    /**
     * Tests the temporary power up, equipment and expiration.
     */
    @Test
    void testConsumableAndDuration() {
        inventory.addConsumable("powerup_jump", 2);
        assertTrue(inventory.hasItem("powerup_jump"));

        inventory.updateConsumables();
        assertTrue(inventory.hasItem("powerup_jump"));

        inventory.updateConsumables();
        assertFalse(inventory.hasItem("powerup_jump"));
    }

    /**
     * Tests integrity and immutability of the map of consumables.
     */
    @Test
    void testConsumableStatus() {
        inventory.addConsumable("potion_speed", 3);
        inventory.addConsumable("jump_boost", 5);

        Map<String, Integer> status = inventory.getConsumablesStatus();
        assertEquals(2, status.size());
        assertEquals(3, status.get("potion_speed"));
        assertEquals(5, status.get("jump_boost"));

        inventory.updateConsumables();
        Map<String, Integer> updatedStatus = inventory.getConsumablesStatus();
        assertEquals(2, updatedStatus.get("potion_speed"));
        assertEquals(4, updatedStatus.get("jump_boost"));

        assertThrows(UnsupportedOperationException.class, () -> {
            status.put("hacker_item", 999);
        }, "The map cannot be modified from outside");
    }

    /**
     * Tests the behaviour of permanent power up that is different from temporary.
     */
    @Test
    void testPermanentPowerUp() {
        String permUpgradeId = "permanent_speed_boost";
        inventory.addItem(permUpgradeId);

        for (int i = 0; i < 10; i++) {
            inventory.updateConsumables();
        }
        assertTrue(inventory.hasItem(permUpgradeId),
                "Permanent upgrade should not be removed with updates");
        assertTrue(inventory.getOwnedItems().contains(permUpgradeId));
    }

}
