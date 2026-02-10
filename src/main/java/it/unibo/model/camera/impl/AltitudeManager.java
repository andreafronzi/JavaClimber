package it.unibo.model.camera.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.api.AltitudeSubject;
import it.unibo.model.gameObj.api.Alien;

/**
 * Manages the calculation of the player's altitude. Implements {@link AltitudeSubject}.
 */
public class AltitudeManager implements AltitudeSubject {

    private final List<AltitudeObserver> observerList = new ArrayList<>();
    private double highestY;
    private final Alien alien;

    /**
     * 
     * @param alien the player to monitor
     */
    public AltitudeManager(Alien alien) {
        this.alien = alien;
        this.highestY = alien.getPosY();
    }

    /**
     * Checks the current position of the alien with the highest point reached.
     * If the alien has moved up, it calculates the difference, updates the highest point and notifies the observer
     */
    public void verifiedAltitude() {
        double currentY = alien.getPosY();
        if (currentY < highestY) {
            double delta = highestY - currentY;
            highestY = currentY;
            notifyObserver(delta);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(AltitudeObserver observer) {
        observerList.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(AltitudeObserver observer) {
        observerList.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObserver(double delta) {
        for (AltitudeObserver altitudeObserver : observerList) {
            altitudeObserver.update(delta);
        }
    }

}
