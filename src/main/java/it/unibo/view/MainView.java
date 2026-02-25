package it.unibo.view;

import it.unibo.controller.api.EndController;
import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.InventoryController;
import it.unibo.controller.api.MenuController;
import it.unibo.controller.api.PauseController;
import it.unibo.controller.api.ShopController;

public interface MainView {

    void setMenuView(MenuController menuController);

    void setGameLaunchedView(GameLaunchedController gameLaunchedController);

    void setInventoryView(InventoryController inventoryController);

    void setShopView(ShopController shopController);

    void setEndView(EndController endController);

    void setPauseView(PauseController pauseController);
}
