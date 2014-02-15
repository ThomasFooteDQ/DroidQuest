package com.droidquest.view.swing;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import com.droidquest.Game;

/**
 * Control Panel which holds the control widgets
 */
public class ControlPanel extends JPanel {
    private final Game game;

    public ControlPanel(Game game) {
        this.game = game;

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(220, 384));
    }

    public Component add(Component c) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = getComponentCount();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(c, gbc);

        return c;
    }
}
