package it.unibo.view;

import java.awt.Color;
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.Font;

import javax.swing.Box; 
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EndViewImpl extends JPanel implements EndView {

    final private MainView view;
    final private JButton restart;
    final private JButton menu;
    final private JLabel titoloMorte;

    public EndViewImpl(MainView view) {
        this.view = view;
        this.setBackground(Color.BLACK);

        this.titoloMorte = new JLabel("SEI MORTO");
        this.titoloMorte.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        this.titoloMorte.setForeground(new Color(180, 0, 0));
        this.titoloMorte.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.restart = new JButton("Restart");
        this.restart.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.menu = new JButton("Menu");
        this.menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalGlue());
        this.add(titoloMorte);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(this.restart);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.menu);
        this.add(Box.createVerticalGlue());

        this.setRestartListener();
        this.setMenuListener();
    }

    private void setRestartListener() {
        this.restart.addActionListener(e -> this.restart());
    }

    private void setMenuListener() {
        this.menu.addActionListener(e -> this.menu());
    }

    @Override
    public void restart() {
        view.restart();
    }

    @Override
    public void menu() {
        view.menu();
    }

}