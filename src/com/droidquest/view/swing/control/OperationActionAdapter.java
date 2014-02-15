package com.droidquest.view.swing.control;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;

import com.droidquest.operation.Operation;

/**
 * An adapter which transforms an operation into a Swing Action.
 */
public class OperationActionAdapter extends AbstractAction {
    private final Operation operation;

    public OperationActionAdapter(String actionName, Operation moveOperation) {
        operation = moveOperation;

        putValue(Action.SHORT_DESCRIPTION, actionName);
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
