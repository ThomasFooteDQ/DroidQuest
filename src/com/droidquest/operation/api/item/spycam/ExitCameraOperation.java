package com.droidquest.operation.api.item.spycam;

import com.droidquest.items.SpyCam;
import com.droidquest.operation.Operation;

/**
 * Spy cam exit operation.
 */
public class ExitCameraOperation implements Operation {
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
