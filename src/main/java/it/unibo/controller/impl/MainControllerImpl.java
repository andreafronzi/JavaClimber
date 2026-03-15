package it.unibo.controller.impl;

import java.util.Optional;

import it.unibo.controller.api.GameLaunchedController;
import it.unibo.controller.api.MainController;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.LaunchedGame.api.StateOfLaunchedGame;
import it.unibo.model.LaunchedGame.impl.RunningState;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.persistence.impl.SaveManagerImpl;
import it.unibo.view.MainView;

public class MainControllerImpl implements MainController {

    private MainView mainView;

    private final Menu menu;
    private final SaveManager saveManager;
    private StateOfLaunchedGame runningState;
    private boolean isGameRunning = false;

    public MainControllerImpl(final MainView mainView) {
        this.mainView = mainView;
        this.saveManager = new SaveManagerImpl();
        this.menu = new MenuImpl(this);
        this.loadGame();
        this.menu.setState(new MenuState(this.menu));
    }

    @Override
    public void setView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void openMenuView() {
        MenuControllerImpl menuController = new MenuControllerImpl(this, this.menu.getScoreManager(), this.menu);
        mainView.setMenuView(menuController);
    }

    @Override
    public void launchGame() {
        final GameLaunchedControllerImpl gameLaunchedController = new GameLaunchedControllerImpl(
                this.menu.getLaunchedGame().get(), this.menu.getInventory());
        mainView.setGameLaunchedView(gameLaunchedController, gameLaunchedController);
        runningState = this.menu.getLaunchedGame().get().getState();
        gameLaunchedController.runGame();
    }

    @Override
    public void openInventoryView() {
        InventoryControllerImpl inventoryController = new InventoryControllerImpl(this, this.menu.getInventory(),
                this.menu.getInventory().getFactory());
        mainView.setInventoryView(inventoryController);
    }

    @Override
    public void openShopView() {
        ShopControllerImpl shopController = new ShopControllerImpl(this, this.menu.getShopManager());
        mainView.setShopView(shopController);
    }

    @Override
    public void openEndView() {
        EndControllerImpl endController = new EndControllerImpl(this.menu.getLaunchedGame().get(), this.menu, this);
        mainView.setEndView(endController);
    }

    @Override
    public void openPauseView() {
        PauseControllerImpl pauseController = new PauseControllerImpl(this.menu.getLaunchedGame().get(), this.menu,
                this.runningState, this);
        mainView.setPauseView(pauseController);
    }

    @Override
    public void saveProgress() {
        SaveState currentState = new SaveState(this.menu.getInventory().getTotalCoins(),
                this.menu.getScoreManager().getHighScore(), this.menu.getInventory().getOwnedItems(),
                this.menu.getInventory().getConsumablesStatus(), this.menu.getInventory().getSelectedSkin(),
                this.menu.getInventory().getSelectedJumpLevel(), this.menu.getInventory().getSelectedSpeedLevel());
        this.saveManager.save(currentState);
    }

    private void loadGame() {
        Optional<SaveState> loadedState = this.saveManager.load();
        if (loadedState.isPresent()) {
            this.menu.getInventory().loadState(loadedState.get());
            this.menu.getScoreManager().loadState(loadedState.get());
        } else {
            SaveState initialState = new SaveState(0, 0, this.menu.getInventory().getOwnedItems(),
                    this.menu.getInventory().getConsumablesStatus(), this.menu.getInventory().getSelectedSkin(),
                    this.menu.getInventory().getSelectedJumpLevel(), this.menu.getInventory().getSelectedSpeedLevel());
            this.saveManager.save(initialState);
        }
    }
}
