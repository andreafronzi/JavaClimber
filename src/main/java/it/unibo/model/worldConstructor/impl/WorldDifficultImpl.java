package it.unibo.model.worldConstructor.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.worldConstructor.api.Observer;
import it.unibo.model.worldConstructor.api.Subject;
import it.unibo.model.worldConstructor.api.WorldDifficult;

/**
 * Implementation of WorldDifficult.
 * Manages difficulty levels by observing the player's height (Alien) and
 * notifying observers when a new difficulty threshold is reached.
 */
public class WorldDifficultImpl implements WorldDifficult, Subject {

    private final Alien alien;
    private final List<Observer> observers;
    private Difficult difficult;
    private List<Difficult> difficultList;

    /**
     * Constructs a new WorldDifficultImpl.
     * 
     * @param alien the player character to monitor
     * @param difficultList the list of available difficulty levels
     */
    public WorldDifficultImpl(final Alien alien, final List<Difficult> difficultList) {
        this.alien = alien;
        this.observers = new LinkedList<>();
        this.difficultList = List.copyOf(difficultList);
        sortList();
        this.difficult = this.difficultList.getFirst();
    }

    private void sortList(){
        this.difficultList.sort( (d1, d2) -> Double.compare(d1.height(), d2.height()) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDifficult() {
        if(alien.getHeight() > difficult.height()){
            difficultList.stream().forEach((d) -> {
                if(alien.getHeight() < d.height()){
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

}
