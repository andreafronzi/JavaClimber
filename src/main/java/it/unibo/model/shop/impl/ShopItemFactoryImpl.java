package it.unibo.model.shop.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

public class ShopItemFactoryImpl implements ShopItemFactory {

    private final List<ShopItem> skins;
    private final List<ShopItem> powerUps;

    public ShopItemFactoryImpl() {
        this.skins = createSkins();
        this.powerUps = createPowerUps();
    }

    private List<ShopItem> createSkins(){
        return List.of(
            new SkinItemImpl("s_basic", "Basic Alien", "Original alien", 0, Map.of()),
            new SkinItemImpl("s_primitive", "Primitive Alien", "Directly from the Flinstones", 100, Map.of(ShopItemStat.SPEED, 1.1)),
            new SkinItemImpl("s_sport", "Sportive Alien", "From the NBA", 250, Map.of(ShopItemStat.JUMP_HEIGHT, 1.2)),
            new SkinItemImpl("s_atro", "Astronaut alien", "From the space", 500, Map.of(ShopItemStat.JUMP_HEIGHT, 1.5))
        );
    }

    private List<ShopItem> createPowerUps() {
        return List.of(
            new PowerUpItemImpl("pt_jump1", "Power Jump 1", "Jump higher for 3 matches", 50, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.3), 3),
            new PowerUpItemImpl("pt_speed1", "Speed Boost 1", "Speed boost for 5 matches", 150, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, 1.5), 5),
            new PowerUpItemImpl("pt_coin_x1.5", "Coin Multiplier x1.5", "Coin multiplier for 10 matches", 300, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, 1.5), 10),

            new PowerUpItemImpl("pp_speed1", "Speed Boost 1", "Permanent Speed boost", 500, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, 1.3), 0),
            new PowerUpItemImpl("pp_jump1", "Power Jump 1", "Permanent Jump higher", 600, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.2), 0)
        );
    }

    @Override
    public List<ShopItem> getSkins() {
        return List.copyOf(this.skins);
    }

    @Override
    public List<ShopItem> getPowerUps() {
        return List.copyOf((this.powerUps));
    }

    @Override
    public List<ShopItem> getAllItems() {
        return Stream.concat(skins.stream(), powerUps.stream()).toList();
    }

    @Override
    public Optional<ShopItem> getItemById(String itemId) {
        return getAllItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

}
