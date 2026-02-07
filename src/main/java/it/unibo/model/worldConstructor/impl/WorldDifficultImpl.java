package it.unibo.model.worldConstructor.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.gameObj.api.Alien;
import it.unibo.model.worldConstructor.api.Observer;
import it.unibo.model.worldConstructor.api.Subject;
import it.unibo.model.worldConstructor.api.WorldDifficult;

public class WorldDifficultImpl implements WorldDifficult, Subject {

    private final Alien alien;
    private final List<Observer> observers;
    private Difficult difficult;
    private List<Difficult> difficultList;

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

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers(final Difficult difficult) {
        for (var o : observers) {
            o.update(difficult);
        }
    }

}
