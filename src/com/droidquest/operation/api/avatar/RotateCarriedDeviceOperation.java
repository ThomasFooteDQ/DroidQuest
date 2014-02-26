package com.droidquest.operation.api.avatar;

import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Operation that rotates the device being carried by the given avatar.
 */
public class RotateCarriedDeviceOperation extends OperationAdapter {
    private final Item avatar;
    private final Rotation direction;

    public RotateCarriedDeviceOperation(Item avatar, Rotation direction) {
        this.avatar = avatar;
        this.direction = direction;
    }

    @Override
    public boolean canExecute() {
        return avatar.getCarrying() != null && avatar.getCarrying().isDevice();
    }

    @Override
    public void execute() {
        ((Device) avatar.getCarrying()).rotate(direction.getDirection());
    }
}
