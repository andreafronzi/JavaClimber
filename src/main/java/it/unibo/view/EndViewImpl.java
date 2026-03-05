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

import it.unibo.controller.api.EndController;


public class EndViewImpl extends JPanel implements EndView {

    final private EndController controller;
    final private JButton restart;
    final private JButton menu;
    final private JLabel titoloMorte;

    public EndViewImpl(EndController controller) {
        this.controller = controller;
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

        this.add(Box.createRigidArea(new Dimension(0, 15)));
        
        if (this.controller.isNewHighScore()) {
            JLabel newRecordLabel = new JLabel("NEW HIGH SCORE: " + this.controller.getScore());
            newRecordLabel.setFont(new Font(Font.SERIF, Font.BOLD, 36));
            newRecordLabel.setForeground(new Color(255, 215, 0)); 
            newRecordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(newRecordLabel);
        } else {
            JLabel scoreLabel = new JLabel("Score: " + this.controller.getScore());
            scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(scoreLabel);
        }

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
        this.controller.restart();
    }

    @Override
    public void menu() {
        this.controller.menu();
    }

}