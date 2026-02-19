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
    private final List<ShopItem> powerUpsTemporary;
    private final List<ShopItem> powerUpsPermanent;

    public ShopItemFactoryImpl() {
        this.skins = createSkins();
        this.powerUpsTemporary = createPowerUpsTemporary();
        this.powerUpsPermanent = createPowerUpsPermanent();
    }

    private List<ShopItem> createSkins(){
        return List.of(
            new SkinItemImpl("s_basic", "Basic Alien", "Original alien", 0, Map.of()),
            new SkinItemImpl("s_primitive", "Primitive Alien", "Directly from the Flinstones", 100, Map.of(ShopItemStat.SPEED, 1.1)),
            new SkinItemImpl("s_sport", "Sportive Alien", "From the NBA", 250, Map.of(ShopItemStat.JUMP_HEIGHT, 1.2)),
            new SkinItemImpl("s_astro", "Astronaut alien", "From the space", 500, Map.of(ShopItemStat.JUMP_HEIGHT, 1.5))
        );
    }

    private List<ShopItem> createPowerUpsTemporary() {
        return List.of(
            /* PowerUp temporary */
            new PowerUpItemImpl("pt_jump1", "Power Jump 1", "Jump higher x1.3 for 3 matches", 50, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.3), 3),
            new PowerUpItemImpl("pt_jump2", "Power Jump 2", "Jump higher x1.3 for 5 matches", 70, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.3), 5),
            new PowerUpItemImpl("pt_jump3", "Power Jump 3", "Jump higher x1.5 for 5 matches", 150, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.5), 5),

            new PowerUpItemImpl("pt_speed1", "Speed Boost 1", "Speed boost x1.3 for 5 matches", 100, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, 1.3), 5),
            new PowerUpItemImpl("pt_speed2", "Speed Boost 2", "Speed boost x1.3 for 7 matches", 150, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, 1.3), 7),
            new PowerUpItemImpl("pt_speed3", "Speed Boost 3", "Speed boost x1.5 for 7 matches", 350, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, 1.5), 7),

            new PowerUpItemImpl("pt_coin_1", "Coin Multiplier 1", "Coin multiplier x2.0 for 3 matches", 300, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, 2.0), 3),
            new PowerUpItemImpl("pt_coin_2", "Coin Multiplier 2", "Coin multiplier x2.0 for 5 matches", 350, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, 2.0), 5),
            new PowerUpItemImpl("pt_coin_3", "Coin Multiplier 3", "Coin multiplier x3.0 for 5 matches", 500, ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, 3.0), 5)
        );
    }

    private List<ShopItem> createPowerUpsPermanent() {
        return List.of(
            /* Speed PowerUp permanent */
            new PowerUpItemImpl("pp_speed_1", "Speed Boost 1", "Permanent Speed boost 1.1", 300, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, 1.1), 0),
            new PowerUpItemImpl("pp_speed_2", "Speed Boost 2", "Permanent Speed boost 1.3", 500, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, 1.3), 0),
            new PowerUpItemImpl("pp_speed_3", "Speed Boost 3", "Permanent Speed boost 1.4", 600, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, 1.4), 0),
            new PowerUpItemImpl("pp_speed_4", "Speed Boost 4", "Permanent Speed boost 1.5", 700, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, 1.5), 0),
            new PowerUpItemImpl("pp_speed_5", "Speed Boost 5", "Permanent Speed boost 1.7", 1000, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, 1.7), 0),
            /* Jump PowerUp permanent */
            new PowerUpItemImpl("pp_jump_1", "Power Jump 1", "Permanent Jump higher 1.1", 300, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.1), 0),
            new PowerUpItemImpl("pp_jump_2", "Power Jump 2", "Permanent Jump higher 1.3", 500, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.3), 0),
            new PowerUpItemImpl("pp_jump_3", "Power Jump 3", "Permanent Jump higher 1.4", 600, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.4), 0),
            new PowerUpItemImpl("pp_jump_4", "Power Jump 4", "Permanent Jump higher 1.5", 700, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.5), 0),
            new PowerUpItemImpl("pp_jump_5", "Power Jump 5", "Permanent Jump higher 1.7", 1000, ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, 1.7), 0)
        );
    }

    @Override
    public List<ShopItem> getSkins() {
        return List.copyOf(this.skins);
    }

    @Override
    public List<ShopItem> getPowerUpsTemporary() {
        return List.copyOf((this.powerUpsTemporary));
    }

    @Override
    public List<ShopItem> getPowerUpsPermanent() {
        return List.copyOf((this.powerUpsPermanent));
    }

    @Override
    public List<ShopItem> getAllItems() {
        return Stream.of(skins, powerUpsTemporary, powerUpsPermanent)
                 .flatMap(List::stream)
                 .toList();
    }

    @Override
    public Optional<ShopItem> getItemById(String itemId) {
        return getAllItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

}
