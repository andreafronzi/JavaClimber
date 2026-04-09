package javaclimber.worldConstructor.gameObjectSpawn.addOnSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.addOnSpawn.impl.AddOnPositionSetterImpl;

public class AddOnPositionSetterTest {

    private AddOnPositionSetterImpl addOnPositionGenerator = new AddOnPositionSetterImpl();

    private static final double ADDON_HEIGHT = 5;
    private static final double ADDON_WIDTH = 5;

    private static final double PLATFORM_WIDTH = 20;

    private static final double POS_X = 400;
    private static final double POS_Y = 700;

    private Gadget createGadget(final Vector2d pos) {
        Gadget gadget = new EliCap(ADDON_WIDTH, ADDON_HEIGHT, pos);
        return gadget;
    }

    @Test
    public void generatePosition() {
        Gadget gadget = createGadget(new Vector2dImpl(POS_X, POS_Y));
        var gadgetModPos = addOnPositionGenerator.generatePosition(gadget, PLATFORM_WIDTH);
        assertEquals(gadgetModPos, gadget);
        assertEquals(POS_X + ((PLATFORM_WIDTH - ADDON_WIDTH) / 2), gadgetModPos.getPosX());
        assertEquals(POS_Y + ADDON_HEIGHT, gadgetModPos.getPosY());
    }

}
