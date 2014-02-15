package com.droidquest.operation.api.item.handle;

import com.droidquest.items.Handle;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Drops the handle.
 */
public class HandleDropOperation extends OperationAdapter {
    private final Handle handle;

    public HandleDropOperation(Handle handle) {
        this.handle = handle;
    }

    @Override
    public boolean canExecute() {
        return false;
    }

    @Override
    public void execute() {
        handle.dropHandle();
    }
}
