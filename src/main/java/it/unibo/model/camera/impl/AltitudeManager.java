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
    private final double thresholdY;;
    private final Alien alien;

    /**
     * Constructs an AltitudeManager with the given alien and thresholdY.
     * @param alien alien the player to monitor
     * @param thresholdY the threshold Y value that triggers the altitude update when the alien moves above it
     */
    public AltitudeManager(Alien alien, double thresholdY) {
        this.alien = alien;
        this.thresholdY = thresholdY;
    }

    /**
     * Checks the current position of the alien with the thresholdY.
     * If the alien's Y position is below the thresholdY, it calculates the delta and notifies the observers.
     */
    public void verifiedAltitude() {
        double currentY = alien.getPosY();
        if (currentY < thresholdY) {
            double delta = thresholdY - currentY;
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
