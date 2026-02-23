package it.unibo.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class SpriteManager {
    
    private final Map<SpriteEnum, BufferedImage> sprites = new HashMap<>();

    public SpriteManager() {
        loadResources();
    }
    
    public void loadResources() {
        sprites.put(SpriteEnum.DOODLER_LEFT, this.load("doodler_left.png"));
        sprites.put(SpriteEnum.DOODLER_RIGHT, this.load("doodler_right.png"));
        sprites.put(SpriteEnum.PLATFORM_GREEN, this.load("platform_green.png"));
        sprites.put(SpriteEnum.JETPACK_ON, this.load("jetpack_on.png"));

    }

    private BufferedImage load(final String path) {
        try {
            return ImageIO.read(new File("src/main/resources/" + path));
        } catch (final IOException e) {
            e.printStackTrace();
            return null; // O gestisci errore
        }
    }

    public BufferedImage get(final SpriteEnum key) {
        return sprites.get(key);
    }
}