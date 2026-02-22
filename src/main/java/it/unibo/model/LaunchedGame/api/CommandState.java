package it.unibo.model.LaunchedGame.api;

public interface CommandState<X> {

    void addCommand(X command);

}
