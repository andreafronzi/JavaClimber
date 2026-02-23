package JavaClimber.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldConstructor.impl.Difficult;
import it.unibo.model.worldConstructor.impl.PlatformPositionGeneratorImpl;

public class PlatformPositionGeneratorTest {

    PlatformPositionGeneratorImpl platformPositionGenerator;
    BoundY boundY;
    Boundary boundX;

    @BeforeEach
    void setUp() {
        boundY = new BoundY(0, 800);
        boundX = new Boundary(0, 400);
        platformPositionGenerator = new PlatformPositionGeneratorImpl(boundX,
                boundY, null);
    }

    @Test
    void testGeneratePosition() {
        platformPositionGenerator.setDifficult(new Difficult(0, 70, 30, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, null));
        var position = platformPositionGenerator.generatePosition(10, 10);
        assert(position.getX() >= boundX.x0() && position.getX() <= boundX.x1());
        assert(position.getY() >= boundY.minY() && position.getY() <= boundY.maxY());
    }

}
