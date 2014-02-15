package com.droidquest.view.swing.control;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Dimension;

import com.droidquest.operation.api.Operation;

/**
 * Standard toolbar button for Operations
 */
public class OperationButton extends JButton {
    public OperationButton(Operation operation) {
        super(new OperationActionAdapter(operation));

        setPreferredSize(new Dimension(40, 40));
        setFocusable(false);
        if (operation.getIconFilename() != null) {
            setIcon(new ImageIcon(operation.getIconFilename()));
        }
    }
}
