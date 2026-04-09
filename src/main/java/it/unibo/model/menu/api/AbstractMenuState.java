package it.unibo.model.menu.api;

/**
 * Abstract base class for menu states.
 * Provides a common structure and holds a reference to the Menu context.
 */
public abstract class AbstractMenuState implements StateOfMenu {

    /**
     * The menu context that this state belongs to.
     */
    protected final Menu menu;

    /**
     * Constructs a new AbstractMenuState.
     * 
     * @param menu the Menu context 
     */
    public AbstractMenuState(final Menu menu) {
        this.menu = menu;
    }
}
