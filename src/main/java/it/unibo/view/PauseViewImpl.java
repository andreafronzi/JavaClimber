package it.unibo.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.api.PauseController;

public class PauseViewImpl extends JPanel implements PauseView {

    final private PauseController controller;
    final private JButton resume;
    final private JButton menu;
    final private JLabel pauseTitle;

    public PauseViewImpl(final PauseController controller) {
        this.controller = controller;
        this.setBackground(Color.BLACK);

        this.pauseTitle = new JLabel("PAUSA");
        this.pauseTitle.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        this.pauseTitle.setForeground(Color.WHITE);
        this.pauseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.resume = new JButton("Resume");
        this.resume.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.menu = new JButton("Menu");
        this.menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalGlue());
        this.add(pauseTitle);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(this.resume);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.menu);
        this.add(Box.createVerticalGlue());

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
        this.controller.resume();
    }

    @Override
    public void menu() {
        this.controller.menu();
    }
}
