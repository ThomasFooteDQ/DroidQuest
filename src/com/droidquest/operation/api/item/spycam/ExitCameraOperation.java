package com.droidquest.operation.api.item.spycam;

import com.droidquest.items.SpyCam;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Spy cam exit operation.
 */
public class ExitCameraOperation extends OperationAdapter {
    private final SpyCam spyCam;

    public ExitCameraOperation(SpyCam spyCam) {
        this.spyCam = spyCam;
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        spyCam.exitCamera();
    }
}
