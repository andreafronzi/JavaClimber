package it.unibo.model.worldConstructor.api;

import it.unibo.model.worldConstructor.impl.Difficult;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Difficult difficult);
}
