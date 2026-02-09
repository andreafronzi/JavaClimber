package it.unibo.model.worldConstructor.api;

import it.unibo.model.worldConstructor.impl.Difficult;

public interface Observer {
    void update(Difficult difficult);
}
