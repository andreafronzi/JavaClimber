package JavaClimber.worldGenerator.gameObjectSpawn.platformSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameObj.PlatformBuilder.impl.PlatformBuilderImpl;
import it.unibo.model.gameObj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PairImpl;

public class PairTest {

    private Pair<Double,Function<Vector2d, Platform>> pairPlatform;

    @BeforeEach
    private void setUpPair(final double chance, final Function<Vector2d, Platform> function) {
        this.pairPlatform = new PairImpl<>(chance, function);
    }

    private Platform createPlatform(final Vector2d pos) {
        PlatformBuilderImpl platformBuilder = new PlatformBuilderImpl();
        Platform platform = platformBuilder.at(pos).build();
        return platform;
    }

    @Test
    public void getChanceTest() {
        double chance = 1;
        setUpPair(chance, (p) -> createPlatform(p));
        assertEquals(chance, this.pairPlatform.getX());
    }

    @Test
    public void getPlatformTest() {
        Vector2d pos = new Vector2dImpl(10, 15);
        double chance = 1;
        setUpPair(chance, (p) -> createPlatform(p));
        assertEquals(pos.getX(), pairPlatform.getY().apply(pos).getPosX());
        assertEquals(pos.getY(), pairPlatform.getY().apply(pos).getPosY());
    }

}
