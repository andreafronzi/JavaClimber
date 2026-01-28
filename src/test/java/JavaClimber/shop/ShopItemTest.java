package JavaClimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

/**
 * Tests for {@link ShopItem} and {@link ShopItemFactory}.
 */
public class ShopItemTest {

    private final ShopItemFactory itemFactory = new ShopItemFactoryImpl();

    /**
     * Verifies the separation of lists in the factory.
     */
    @Test
    void testFactoryLists() {
        assertEquals(4, itemFactory.getSkins().size());
        assertEquals(3, itemFactory.getPowerUpsTemporary().size());
        assertEquals(10, itemFactory.getPowerUpsPermanent().size());
        
        assertEquals(17, itemFactory.getAllItems().size());
    }

    /**
     * Verifies a skin item
     */
    @Test
    void testSkinItem() {
        Optional<ShopItem> skin_astronaut = itemFactory.getItemById("s_astro");    
        assertTrue(skin_astronaut.isPresent());
        ShopItem item = skin_astronaut.get();

        assertEquals("s_astro", item.getId());
        assertEquals("Astronaut alien", item.getName());
        assertEquals(ShopItemType.SKIN, item.getType());
        assertEquals("From the space", item.getDescription());
        assertEquals(500, item.getPrice());
        assertEquals(0, item.getInitialDuration());
        assertTrue(item.getStats().containsKey(ShopItemStat.JUMP_HEIGHT));
        assertEquals(1.5, item.getStats().get(ShopItemStat.JUMP_HEIGHT));
    }

    /**
     * Verifies a temporary power up item
     */
    @Test
    void testTemporaryPowerUpItem() {
        Optional<ShopItem> coinPwr = itemFactory.getItemById("pt_coin_x1.5");    
        assertTrue(coinPwr.isPresent());
        ShopItem item = coinPwr.get();

        assertEquals("pt_coin_x1.5", item.getId());
        assertEquals("Coin Multiplier x1.5", item.getName());
        assertEquals(ShopItemType.TEMPORARY_UPGRADE, item.getType());
        assertEquals("Coin multiplier for 10 matches", item.getDescription());
        assertEquals(300, item.getPrice());
        assertEquals(10, item.getInitialDuration());
        assertTrue(item.getStats().containsKey(ShopItemStat.COIN_MULTIPLIER));
        assertEquals(1.5, item.getStats().get(ShopItemStat.COIN_MULTIPLIER));
    }

    /**
     * Verifies a permanent power up item
     */
    @Test
    void testPermanentPowerUpItem() {
        Optional<ShopItem> permSpeed = itemFactory.getItemById("pp_speed_2");
        assertTrue(permSpeed.isPresent());
        ShopItem item = permSpeed.get();

        assertEquals("pp_speed_2", item.getId());
        assertEquals("Speed Boost 2", item.getName());
        assertEquals(ShopItemType.PERMANENT_UPGRADE, item.getType());
        assertEquals("Permanent Speed boost", item.getDescription());
        assertEquals(500, item.getPrice());
        assertEquals(0, item.getInitialDuration());
        assertTrue(item.getStats().containsKey(ShopItemStat.SPEED));
        assertEquals(1.3, item.getStats().get(ShopItemStat.SPEED));
    }
}
