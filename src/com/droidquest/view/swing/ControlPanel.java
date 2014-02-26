package com.droidquest.view.swing;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import com.droidquest.view.swing.control.OperationButton;

/**
 * Control Panel which holds the control widgets
 */
public class ControlPanel extends JPanel {
    public ControlPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(220, 384));
    }

    public void refresh() {
        for (Component component : getComponents()) {
            refreshEnableStates(component);
        }
    }

    private static void refreshEnableStates(Component component) {
        if (component instanceof OperationButton) {
            component.setEnabled(((OperationButton)component).getAction().isEnabled());
        }
        else if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                refreshEnableStates(child);
            };
        }
    }
}
