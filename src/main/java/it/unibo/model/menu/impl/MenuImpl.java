package it.unibo.model.menu.impl;

import java.util.Optional;

import it.unibo.controller.api.MainController;
import it.unibo.model.LaunchedGame.api.LaunchedGame;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.StateOfMenu;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopManager;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.shop.impl.ShopManagerImpl;

/**
 * Concrete implementation of the Menu interface.
 * Represents the context of the initial game menu
 * where you can start the various functions of the game.
 */
public class MenuImpl implements Menu {

    private StateOfMenu currentState;
    private Optional<LaunchedGame> launchedGame;

    private final MainController mainController;
    private final ShopItemFactoryImpl shopItemFactory;
    private final InventoryImpl inventory;
    private final ShopManagerImpl shopManager;
    private final ScoreManagerImpl scoreManager;

    /**
     * Creates a new MenuImpl instance.
     */
    public MenuImpl(final MainController mainController) {
        this.mainController = mainController;
        this.shopItemFactory = new ShopItemFactoryImpl();
        this.inventory = new InventoryImpl(shopItemFactory);
        this.shopManager = new ShopManagerImpl(shopItemFactory, inventory);
        this.scoreManager = new ScoreManagerImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(StateOfMenu state) {
        this.currentState = state;
        this.currentState.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateOfMenu getState() {
        return this.currentState;
    }

    @Override
    public Optional<LaunchedGame> getLaunchedGame() {
        return this.launchedGame;
    }

    @Override
    public void setLaunchedGame(final LaunchedGame launchedGame) {
        this.launchedGame = Optional.of(launchedGame);
    }

    @Override
    public MainController getMainController() {
        return this.mainController;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public ShopManager getShopManager() {
        return this.shopManager;
    }

    @Override
    public ScoreManager getScoreManager() {
        return this.scoreManager;
    }
}
