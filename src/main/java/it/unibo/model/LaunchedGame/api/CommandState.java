package it.unibo.model.LaunchedGame.api;

import java.util.Optional;

public interface CommandState<X> {

    void addCommand(X command);

    Optional<X> pollCommand();

}
