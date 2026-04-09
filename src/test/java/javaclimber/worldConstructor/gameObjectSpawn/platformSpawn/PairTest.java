package javaclimber.worldConstructor.gameObjectSpawn.platformSpawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.api.Pair;
import it.unibo.model.worldConstructor.gameObjectSpawn.platformSpawn.impl.PairImpl;

public class PairTest {

    private Pair<Integer, Integer> pairPlatform;

    private static final int X = 10;
    private static final int Y = 20;

    @BeforeEach
    private void setUpPair() {
        this.pairPlatform = new PairImpl<>(X, Y);
    }

    @Test
    public void getXTest() {
        assertEquals(X, this.pairPlatform.getX());
    }

    @Test
    public void getYTest() {
        assertEquals(Y, this.pairPlatform.getY());
    }

}
