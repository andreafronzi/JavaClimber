package it.unibo.model.camera.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.api.AltitudeSubject;
import it.unibo.model.gameObj.api.Alien;

public class AltitudeManager implements AltitudeSubject {

    private final List<AltitudeObserver> observerList = new ArrayList<>();
    private double highestY;
    private final Alien alien;

    public AltitudeManager(Alien alien) {
        this.alien = alien;
        this.highestY = alien.getPosY();
    }

    public void verifiedAltitude() {
        double currentY = alien.getPosY();
        if (currentY < highestY) {
            double delta = highestY - currentY;
            highestY = currentY;
            notifyObserver(delta);
        }
    }

    @Override
    public void addObserver(AltitudeObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(AltitudeObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(double delta) {
        for (AltitudeObserver altitudeObserver : observerList) {
            altitudeObserver.update(delta);
        }
    }

}
