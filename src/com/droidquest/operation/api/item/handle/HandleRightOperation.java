package com.droidquest.operation.api.item.handle;

import com.droidquest.items.Handle;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Pulls the handle right.
 */
public class HandleRightOperation extends OperationAdapter {
    private final Handle handle;

    public HandleRightOperation(Handle handle) {
        this.handle = handle;
    }

    @Override
    public boolean canExecute() {
        return false;
    }

    @Override
    public void execute() {
        handle.pullWallRight();
    }
}
