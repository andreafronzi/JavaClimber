package it.unibo.view;

import it.unibo.controller.api.*;

public interface MainView {

    void setMenuView(MenuController menuController);

    void setGameLaunchedView(GameLaunchedController gameLaunchedController, GameLaunchedInputController gameLaunchedInputController);

    void setInventoryView(InventoryController inventoryController);

    void setShopView(ShopController shopController);

    void setEndView(EndController endController);

    void setPauseView(PauseController pauseController);
}
