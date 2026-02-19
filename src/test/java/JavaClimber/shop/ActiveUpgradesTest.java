package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

public class ActiveUpgradesTest {

    private ShopItemFactory factory = new ShopItemFactoryImpl();
    private Inventory inventory = new InventoryImpl(factory);
    private ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(inventory, factory);


    @Test
    void testDefaults() {
        assertEquals(1.0, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.0, activeUpgrades.getJumpMultiplier());
        assertEquals(1, activeUpgrades.getCoinMultiplier());
    }

    @Test
    void testSkinApplication() {
        String skinId = "s_primitive";
        inventory.addItem(skinId);
        inventory.equipSkin(skinId);
        activeUpgrades.updateValues();
        assertEquals(1.1, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.0, activeUpgrades.getJumpMultiplier());
        assertEquals(1, activeUpgrades.getCoinMultiplier());
    }

    @Test
    void testConsumableApplication() {
        String itemId = "pt_jump1";
        inventory.addItem(itemId);
        inventory.addConsumable(itemId, 3);
        activeUpgrades.updateValues();
        assertEquals(1.0, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.3, activeUpgrades.getJumpMultiplier());
        assertEquals(1, activeUpgrades.getCoinMultiplier());

        String coinId = "pt_coin_1";
        inventory.addItem(coinId);
        inventory.addConsumable(coinId, 3);
        activeUpgrades.updateValues();
        assertEquals(1.0, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.3, activeUpgrades.getJumpMultiplier());
        assertEquals(2, activeUpgrades.getCoinMultiplier());
    }

    @Test
    void testPermanentUpgradeApplication() {
        inventory.addItem("pp_speed_1");
        activeUpgrades.updateValues();
        assertEquals(1.1, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.0, activeUpgrades.getJumpMultiplier());
        assertEquals(1, activeUpgrades.getCoinMultiplier());

        inventory.addItem("pp_jump_1");
        inventory.addItem("pp_jump_2");
        activeUpgrades.updateValues();
        assertEquals(1.1, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.3, activeUpgrades.getJumpMultiplier());
        assertEquals(1, activeUpgrades.getCoinMultiplier());
    }

    @Test
    void testConflictMaxLogic() {
        inventory.addItem("s_primitive");
        inventory.equipSkin("s_primitive");
        inventory.addItem("pt_speed3");
        inventory.addConsumable("pt_speed3", 5);
        
        activeUpgrades.updateValues();
        assertEquals(1.5, activeUpgrades.getSpeedMultiplier());
        assertEquals(1.0, activeUpgrades.getJumpMultiplier());
        assertEquals(1, activeUpgrades.getCoinMultiplier());
    }

    @Test
    void testRemoveAndReset() {
        String itemId = "pt_speed1";
        inventory.addItem(itemId);
        inventory.addConsumable(itemId, 5);
        activeUpgrades.updateValues();
        assertEquals(1.3, activeUpgrades.getSpeedMultiplier());

        inventory.toggleConsumable(itemId, factory);
        activeUpgrades.updateValues();
        assertEquals(1.0, activeUpgrades.getSpeedMultiplier());


        inventory.addItem("s_astro");
        inventory.equipSkin("s_astro");
        activeUpgrades.updateValues();
        assertEquals(1.5, activeUpgrades.getJumpMultiplier());
        inventory.deselectSkin();
        activeUpgrades.updateValues();
        assertEquals(1.0, activeUpgrades.getJumpMultiplier());
    }

}
