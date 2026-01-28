package it.unibo.controller.api;

/**
 * Interface 
 */
public interface ShopController {

    void upgradeJump();

    void upgradeSpeed();

    void buyTemporaryItem(int index);

    void buySkin(int index);

    void exit();
}