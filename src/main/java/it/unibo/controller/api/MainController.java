package it.unibo.controller.api;

import it.unibo.view.MainView;

public interface MainController {

    void setView(MainView view);

    void openMenuView();

    void openGameLaunchedView();

    void openInventoryView();

    void openShopView();

    void openEndView();

    void openPauseView();
}