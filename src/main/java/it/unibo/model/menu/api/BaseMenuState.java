package it.unibo.model.menu.api;

/**
 * Abstract base class for menu states.
 * Provides a common structure and holds a reference to the Menu context.
 */
public abstract class BaseMenuState implements StateOfMenu {

    protected final Menu menu;

    /**
     * Constructs a new BaseMenuState.
     * 
     * @param menu the Menu context 
     */
    public BaseMenuState(final Menu menu) {
        this.menu = menu;
    }
}
