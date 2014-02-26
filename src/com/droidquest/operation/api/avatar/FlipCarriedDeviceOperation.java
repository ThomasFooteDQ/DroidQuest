package com.droidquest.operation.api.avatar;

import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Operation that flips the device being carried by the given avatar.
 */
public class FlipCarriedDeviceOperation extends OperationAdapter {
    private final Item avatar;

    public FlipCarriedDeviceOperation(Item avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return avatar.getCarrying() != null && avatar.getCarrying().isDevice();
    }

    @Override
    public void execute() {
        ((Device) avatar.getCarrying()).flip();
    }
}
