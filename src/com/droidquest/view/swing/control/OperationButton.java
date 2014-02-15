package com.droidquest.view.swing.control;

import javax.swing.JButton;

import java.awt.Dimension;

import com.droidquest.operation.Operation;

/**
 * Standard toolbar button for Operations
 */
public class OperationButton extends JButton {
    public OperationButton(String actionName, Operation operation) {
        super(new OperationActionAdapter(actionName, operation));

        setPreferredSize(new Dimension(40, 40));
    }
}
