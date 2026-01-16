package it.unibo.model.menu.api;

public abstract class BaseMenuState implements StateOfMenu {

    private final Menu menu;

    public BaseMenuState(final Menu menu) {
        this.menu = menu;
    }
}
