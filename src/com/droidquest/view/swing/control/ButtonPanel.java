package com.droidquest.view.swing.control;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * Panel which holds buttons in a bordered flow layout.
 */
public class ButtonPanel extends JPanel {
    public ButtonPanel(String name) {
        setOpaque(true);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), name));
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }
}
