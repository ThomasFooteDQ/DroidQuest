package com.droidquest.view.swing.event;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.droidquest.operation.api.Operation;

/**
 * Mouse listener that executes an operation on right click
 */
public class RightClickMouseListener extends MouseAdapter {
    protected final Operation operation;

    public RightClickMouseListener(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            if (operation.canExecute()) {
                operation.execute();
                mouseEvent.consume();
            }
        }
    }
}
