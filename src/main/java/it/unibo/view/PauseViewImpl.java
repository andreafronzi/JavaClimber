package it.unibo.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PauseViewImpl extends JPanel implements PauseView {

    final private MainView view;
    final private JButton resume;
    final private JButton menu;

    public PauseViewImpl(MainView view) {
        this.view = view;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.resume = new JButton("Resume");
        this.menu = new JButton("Menu");
        this.add(this.resume);
        this.add(this.menu);
        this.setResumeListener();
        this.setMenuListener();
    }

    private void setResumeListener() {
        this.resume.addActionListener(e -> this.resume());
    }

    private void setMenuListener() {
        this.menu.addActionListener(e -> this.menu());
    }

    @Override
    public void resume() {
        view.resume();
    }

    @Override
    public void menu() {
        view.menu();
    }
    
}
