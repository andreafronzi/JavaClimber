package it.unibo.controller.impl;

import java.util.Optional;

import it.unibo.controller.api.MainController;
import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
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

    private MainView mainView;
    private final SaveManager saveManager;
    private final ScoreManager scoreManager;
    private final ShopItemFactory shopItemFactory;
    private final ShopManager shopManager;
    private final Inventory inventory;

    public MainControllerImpl(){
        this.saveManager = new SaveManagerImpl();
        this.scoreManager = new ScoreManagerImpl();
        this.shopItemFactory = new ShopItemFactoryImpl();
        this.inventory = new InventoryImpl(shopItemFactory);
        this.shopManager = new ShopManagerImpl(shopItemFactory, inventory, scoreManager, saveManager);
        this.loadGame();
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

    @Override
    public void saveProgress() {
        SaveState currentState = new SaveState(inventory.getTotalCoins(), scoreManager.getHighScore(), inventory.getOwnedItems(), inventory.getConsumablesStatus(), inventory.getSelectedSkin(), inventory.getSelectedJumpLevel(), inventory.getSelectedSpeedLevel());
        this.saveManager.save(currentState);
    }

    private void loadGame() {
        Optional<SaveState> loadedState = this.saveManager.load();
        if (loadedState.isPresent()) {
            this.inventory.loadState(loadedState.get());
            this.scoreManager.loadState(loadedState.get());
        } else {
            SaveState initialState = new SaveState(0, 0, this.inventory.getOwnedItems(), this.inventory.getConsumablesStatus(), this.inventory.getSelectedSkin(), this.inventory.getSelectedJumpLevel(), this.inventory.getSelectedSpeedLevel());
            this.saveManager.save(initialState);
        }
    }
}