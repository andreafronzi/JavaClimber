package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.api.Gadget;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.gameObj.impl.EliCap;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.impl.AddOnPositionGeneratorImpl;

public class AddOnPositiongeneratorTest {

    private AddOnPositionGeneratorImpl addOnPositionGenerator = new AddOnPositionGeneratorImpl();
    private final static double ADDON_HEIGHT = 5;
    private final static double ADDON_WIDTH = 5;

    private Platform createPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).build();
        return platform;
    }

    private Gadget createGadget(final Vector2d pos) {
        Gadget gadget = new EliCap(ADDON_WIDTH, ADDON_HEIGHT, pos);
        return gadget;
    }

    @Test
    public void generatePosition() {
        Platform platform = createPlatform(new Vector2dImpl(800, 800));
        Gadget gadget = createGadget(new Vector2dImpl(0, 0));
        Vector2d pos = addOnPositionGenerator.generatePosition(platform.getPosX(), platform.getPosY(),
                platform.getWidth(), gadget.getHeight(), gadget.getWidth());
        gadget.setPosition(pos);
        assertEquals(platform.getPosX() + ((platform.getWidth() - ADDON_WIDTH) / 2), gadget.getPosX());
        assertEquals(platform.getPosY() + ADDON_HEIGHT, gadget.getPosY());
    }

}
