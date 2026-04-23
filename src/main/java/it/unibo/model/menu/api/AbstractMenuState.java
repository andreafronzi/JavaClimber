package it.unibo.model.menu.api;

/**
 * Abstract base class for menu states.
 * Provides a common structure and holds a reference to the Menu context.
 */
public abstract class AbstractMenuState implements StateOfMenu {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule is disabled because this class is a template and classes that extend
    // it need to access the menu field to change the state of the menu.

    /**
     * The menu context that this state belongs to.
     */
    protected final Menu menu;

    // CHECKSTYLE: VisibilityModifier ON

    /**
     * Constructs a new AbstractMenuState.
     * 
     * @param menu the Menu context
     */
    
    public AbstractMenuState(final Menu menu) {
        this.menu = menu;
    }
}
