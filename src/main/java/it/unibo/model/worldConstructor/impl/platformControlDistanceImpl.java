package it.unibo.model.worldConstructor.impl;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.PlatformImpl;
import it.unibo.model.worldConstructor.api.Difficult;
import it.unibo.model.worldConstructor.api.PlatformControlDistance;

public class platformControlDistanceImpl implements PlatformControlDistance {

    private Difficult difficult;
    private PlatformImpl previousPlatform;

    @Override
    public Vector2d generatePosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generatePosition'");
    }

    @Override
    public void setDifficult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDifficult'");
    }
    
}
