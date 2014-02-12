package com.droidquest.operation.api.item.handle;

import com.droidquest.items.Handle;
import com.droidquest.operation.Operation;

/**
 * Drops the handle.
 */
public class HandleDropOperation implements Operation {
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
