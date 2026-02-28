package it.unibo.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


/**
 * Manages the loading and retrieval of sprites for the game.
 */
public class SpriteManager {
    
    /**
     * A map that associates each {@link SpriteEnum} with its corresponding BufferedImage.
     */
    private final Map<SpriteEnum, BufferedImage> sprites = new HashMap<>();

    /**
     * Constructor for the SpriteManager. It initializes the sprite map by loading the resources.
     */
    public SpriteManager() {
        loadResources();
    }
    
    /**
     * Loads the sprite resources into the sprites map.
     */
    public void loadResources() {
        sprites.put(SpriteEnum.DOODLER_LEFT, this.load("doodle_left.png"));
        sprites.put(SpriteEnum.DOODLER_RIGHT, this.load("doodle_right.png"));
        sprites.put(SpriteEnum.ENEMY, this.load("enemy.png"));
        sprites.put(SpriteEnum.GADGET, this.load("gadget.png"));
        sprites.put(SpriteEnum.PLATFORM, this.load("platform.png"));
        sprites.put(SpriteEnum.PLATFORM_GREEN, this.load("platform_green.png"));
        sprites.put(SpriteEnum.COIN, this.load("coin.png"));
        sprites.put(SpriteEnum.SUB_RIGHT, this.load("sub_right.png"));
        sprites.put(SpriteEnum.SUB_LEFT, this.load("sub_left.png"));
        sprites.put(SpriteEnum.SPORT_RIGHT, this.load("sport_right.png"));
        sprites.put(SpriteEnum.SPORT_LEFT, this.load("sport_left.png"));
        sprites.put(SpriteEnum.ASTRO_RIGHT, this.load("astro_right.png"));
        sprites.put(SpriteEnum.ASTRO_LEFT, this.load("astro_left.png"));
        sprites.put(SpriteEnum.NINJA_RIGHT, this.load("ninja_right.png"));
        sprites.put(SpriteEnum.NINJA_LEFT, this.load("ninja_left.png"));
    
    }

    /**
     * Loads an image from the specified path and returns it as a BufferedImage.
     * 
     * @param path the image path
     * @return the loaded {@link BufferedImage}, or null if an error occurs
     */
    private BufferedImage load(final String path) {
        try {
            return ImageIO.read(new File("src/main/resources/" + path));
        } catch (final IOException e) {
            e.printStackTrace();
            return null; // O gestisci errore
        }
    }

    /**
     * Gets the sprite associated with the given {@link SpriteEnum} key.
     * 
     * @param key the {@link SpriteEnum} key for the desired sprite
     * @return the corresponding {@link BufferedImage}, or null if the key is not found
     */
    public BufferedImage get(final SpriteEnum key) {
        return sprites.get(key);
    }
}