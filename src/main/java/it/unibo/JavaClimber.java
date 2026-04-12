package it.unibo;

import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.view.MainView;
import it.unibo.view.MainViewImpl;

/**
 * Main class of the application.
 */
public final class JavaClimber {

    /**
     * private constructor.
     */
    private JavaClimber() {
    }

    /**
     * Main method that starts the application.
     * 
     * @param args ignore
     */
    public static void main(final String... args) {
        final MainView mainView = new MainViewImpl();
        new MainControllerImpl(mainView);
    }

}
