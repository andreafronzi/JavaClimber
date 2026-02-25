package it.unibo.controller.impl;

import it.unibo.controller.api.MainController;
import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.impl.SaveManagerImpl;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopManager;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.shop.impl.ShopManagerImpl;
import it.unibo.view.MainView;

public class MainControllerImpl implements MainController {

    private final static String FILE_PATH = "saves.json";
    private MainView mainView;
    private final SaveManager saveManager;
    private final ScoreManager scoreManager;
    private final ShopItemFactory shopItemFactory;
    private final ShopManager shopManager;
    private final Inventory inventory;

    public MainControllerImpl(){
        this.saveManager = new SaveManagerImpl(FILE_PATH);
        this.scoreManager = new ScoreManagerImpl();
        this.shopItemFactory = new ShopItemFactoryImpl();
        this.inventory = new InventoryImpl(shopItemFactory);
        this.shopManager = new ShopManagerImpl(shopItemFactory, inventory, scoreManager, saveManager);
    }

    @Override
    public void setView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void openMenuView() {
        MenuControllerImpl menuController = new MenuControllerImpl(this, scoreManager);
        mainView.setMenuView(menuController);
    }

    @Override
    public void openGameLaunchedView() {
        GameLaunchedControllerImpl gameLaunchedController = new GameLaunchedControllerImpl(this);
        mainView.setGameLaunchedView(gameLaunchedController);
    }

    @Override
    public void openInventoryView() {
        InventoryControllerImpl inventoryController = new InventoryControllerImpl(this, inventory, shopItemFactory);
        mainView.setInventoryView(inventoryController);
    }

    @Override
    public void openShopView() {
        ShopControllerImpl shopController = new ShopControllerImpl(this, shopManager);
        mainView.setShopView(shopController);
    }

    @Override
    public void openEndView() {
        EndControllerImpl endController = new EndControllerImpl(this);
        mainView.setEndView(endController);
    }

    @Override
    public void openPauseView() {
        PauseControllerImpl pauseController = new PauseControllerImpl(this);
        mainView.setPauseView(pauseController);
    }

}