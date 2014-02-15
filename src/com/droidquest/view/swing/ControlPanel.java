package com.droidquest.view.swing;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import com.droidquest.Game;

/**
 * Control Panel which holds the control widgets
 */
public class ControlPanel extends JPanel {
    private final Game game;

    public ControlPanel(Game game) {
        this.game = game;

        setBackground(Color.black);
        setPreferredSize(new Dimension(220, 384));
    }
}
