package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.worldConstructor.api.Pair;
import it.unibo.model.worldConstructor.impl.PairImpl;

public class PairTest {

    private Pair<PlatformImpl> pairPlatform;

    private void setUpPair(final double chance, final Function<Vector2d, PlatformImpl> function) {
        this.pairPlatform = new PairImpl<>(chance, function);
    }

    @Test
    public void getChanceTest() {
        double chance = 1;
        setUpPair(chance, (p) -> new PlatformImpl(p));
        assertEquals(chance, this.pairPlatform.getChance());
    }

    @Test
    public void getPlatformTest() {
        Vector2d pos = new Vector2dImpl(10, 15);
        double chance = 1;
        setUpPair(chance, (p) -> new PlatformImpl(p));
        assertEquals(pos.getX(), pairPlatform.createGameObj(pos).getPosX());
        assertEquals(pos.getY(), pairPlatform.createGameObj(pos).getPosY());
    }

}
