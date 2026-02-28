package it.unibo.controller.api;

import it.unibo.view.menu.api.MenuView;

public interface MenuController {

    public void setView(MenuView view);

    public void start();

    public void shop();

    public void inventory();

    public void exit();

    public void openViewMenu();
    
    public int getHighScore();
}