package it.unibo;

import it.unibo.controller.api.MainController;
import it.unibo.controller.impl.MainControllerImpl;
import it.unibo.view.MainView;
import it.unibo.view.MainViewImpl;

public class JavaClimber {

    public static void main(final String... args) {
        final MainView mainView = new MainViewImpl();
        final MainController mainController = new MainControllerImpl(mainView); 
    }
    
}