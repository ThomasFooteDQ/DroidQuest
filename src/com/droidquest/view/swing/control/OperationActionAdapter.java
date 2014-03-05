package com.droidquest.view.swing.control;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

import com.droidquest.operation.api.Operation;

/**
 * An adapter which transforms an operation into a Swing Action.
 */
public class OperationActionAdapter extends AbstractAction {
    private final Operation operation;

    public OperationActionAdapter(Operation operation, KeyStroke acceleratorKey) {
        this.operation = operation;

        putValue(Action.SHORT_DESCRIPTION, operation.getName());
        putValue(Action.ACCELERATOR_KEY, acceleratorKey);
    }

    @Override
    public boolean isEnabled() {
        return operation.canExecute();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (operation.canExecute()) {
            operation.execute();
        }
    }
}
