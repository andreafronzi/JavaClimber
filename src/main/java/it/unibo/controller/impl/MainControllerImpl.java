package it.unibo.controller.impl;

import java.util.Optional;

import it.unibo.controller.api.MainController;
import it.unibo.model.LaunchedGame.api.StateOfLaunchedGame;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.persistence.impl.SaveManagerImpl;
import it.unibo.view.MainView;

/**
 * Implementation of the {@link MainController} interface.
 */
public final class MainControllerImpl implements MainController {

    private MainView mainView;
    private final Menu menu;
    private final SaveManager saveManager;
    private StateOfLaunchedGame runningState;

    /**
     * Constructs a new {@code MainControllerImpl}.
     * Initializes the save manager and the menu, attempts to load a previously
     * saved game,
     * and sets the initial menu state.
     * 
     * @param mainView the main view of the application to be managed by this
     *                 controller
     */
    public MainControllerImpl(final MainView mainView) {
        this.mainView = mainView;
        this.saveManager = new SaveManagerImpl();
        this.menu = new MenuImpl(this);
        this.loadGame();
        this.menu.setState(new MenuState(this.menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(MainView view) {
        this.mainView = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openMenuView() {
        final MenuControllerImpl menuController = new MenuControllerImpl(this, this.menu.getScoreManager(), this.menu);
        mainView.setMenuView(menuController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launchGame() {
        final GameLaunchedControllerImpl gameLaunchedController = new GameLaunchedControllerImpl(
                this.menu.getLaunchedGame().get(), this.menu.getInventory());
        mainView.setGameLaunchedView(gameLaunchedController, gameLaunchedController);
        runningState = this.menu.getLaunchedGame().get().getState();
        gameLaunchedController.runGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventoryView() {
        final InventoryControllerImpl inventoryController = new InventoryControllerImpl(this, this.menu.getInventory(),
                this.menu.getInventory().getFactory());
        mainView.setInventoryView(inventoryController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShopView() {
        final ShopControllerImpl shopController = new ShopControllerImpl(this, this.menu.getShopManager());
        mainView.setShopView(shopController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openEndView() {
        final EndControllerImpl endController = new EndControllerImpl(this.menu.getLaunchedGame().get(), this.menu);
        mainView.setEndView(endController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openPauseView() {
        final PauseControllerImpl pauseController = new PauseControllerImpl(this.menu.getLaunchedGame().get(),
                this.menu,
                this.runningState, this);
        mainView.setPauseView(pauseController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveProgress() {
        final SaveState currentState = new SaveState(this.menu.getInventory().getTotalCoins(),
                this.menu.getScoreManager().getHighScore(), this.menu.getInventory().getOwnedItems(),
                this.menu.getInventory().getConsumablesStatus(), this.menu.getInventory().getSelectedSkin(),
                this.menu.getInventory().getSelectedJumpLevel(), this.menu.getInventory().getSelectedSpeedLevel());
        this.saveManager.save(currentState);
    }

    /**
     * Loads the game's state using the save manager.
     * If a saved state is present, it updates the inventory and score manager with
     * the loaded data.
     * Otherwise, it initializes a new default save state and saves it.
     */
    private void loadGame() {
        final Optional<SaveState> loadedState = this.saveManager.load();
        if (loadedState.isPresent()) {
            this.menu.getInventory().loadState(loadedState.get());
            this.menu.getScoreManager().loadState(loadedState.get());
        } else {
            final SaveState initialState = new SaveState(0, 0, this.menu.getInventory().getOwnedItems(),
                    this.menu.getInventory().getConsumablesStatus(), this.menu.getInventory().getSelectedSkin(),
                    this.menu.getInventory().getSelectedJumpLevel(), this.menu.getInventory().getSelectedSpeedLevel());
            this.saveManager.save(initialState);
        }
    }
}
