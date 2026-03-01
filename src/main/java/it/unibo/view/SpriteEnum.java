package it.unibo.view;

public enum SpriteEnum {
    DOODLER_LEFT, 
    DOODLER_RIGHT,
    GREEN_PLATFORM,
    ENEMY,
    GADGET,
    HORIZONTAL_MOVE_PLATFORM,
    BROKEN_PLATFORM,
    COIN,
    SUB_RIGHT,
    SUB_LEFT,
    BASKET_RIGHT,
    BASKET_LEFT,
    ASTRO_RIGHT,
    ASTRO_LEFT,
    NINJA_RIGHT,
    NINJA_LEFT,
    SOCCER_RIGHT,
    SOCCER_LEFT,
    BUNNY_RIGHT,
    BUNNY_LEFT,
    FRANK_RIGHT,
    FRANK_LEFT,
    FROZEN_RIGHT,
    FROZEN_LEFT,
    GHOST_RIGHT,
    GHOST_LEFT,
    ICE_RIGHT,
    ICE_LEFT,
    JUNGLE_RIGHT,
    JUNGLE_LEFT;

    public static SpriteEnum fromSkinId(String skinId) {
        return fromSkinId(skinId, true);
    }

    public static SpriteEnum fromSkinId(String skinId, boolean isRight) {
        if (skinId == null || skinId.equals("s_basic")) {
            return isRight ? DOODLER_RIGHT : DOODLER_LEFT;
        }
        
        try {
            String suffix = isRight ? "_RIGHT" : "_LEFT";
            String enumName = skinId.replace("s_", "").toUpperCase() + suffix;
            return SpriteEnum.valueOf(enumName); 
        } catch (IllegalArgumentException e) {
            return isRight ? DOODLER_RIGHT : DOODLER_LEFT; 
        }
    }
}
