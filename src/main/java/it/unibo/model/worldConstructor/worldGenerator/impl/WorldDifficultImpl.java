package it.unibo.model.worldConstructor.worldGenerator.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.worldConstructor.data.Difficult;
import it.unibo.model.worldConstructor.worldGenerator.api.Observer;
import it.unibo.model.worldConstructor.worldGenerator.api.Subject;
import it.unibo.model.worldConstructor.worldGenerator.api.WorldDifficult;

/**
 * Implementation of WorldDifficult.
 * Manages difficulty levels by observing the player's height (Alien) and
 * notifying observers when a new difficulty threshold is reached.
 */
public class WorldDifficultImpl implements WorldDifficult, Subject, AltitudeObserver {

    private final List<Observer> observers;
    private Difficult difficult;
    private List<Difficult> difficultList;
    private double height;

    /**
     * Constructs a new WorldDifficultImpl.
     * 
     * @param difficultList the list of available difficulty levels
     */
    public WorldDifficultImpl(final List<Difficult> difficultList) {
        this.observers = new LinkedList<>();
        this.difficultList = List.copyOf(difficultList);
        this.difficult = this.difficultList.getFirst();
        this.height = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDifficult(final double height) {
        this.height += height;
        if(this.height > difficult.height()){
            difficultList.stream().forEach((d) -> {
                if(this.height < d.height()){
                    notifyObservers(d);
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(final Difficult difficult) {
        for (var o : observers) {
            o.update(difficult);
        }
    }

    @Override
    public void update(double delta) {
        this.createDifficult(delta);
    }

}
