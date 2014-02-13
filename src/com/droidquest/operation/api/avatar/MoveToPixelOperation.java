package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;

/**
 * Operation that moves the avatar to a given pixel location
 */
public class MoveToPixelOperation implements Operation {
    protected final Item avatar;
    protected final int x, y;

    public MoveToPixelOperation(Item avatar, int x, int y) {
        this.avatar = avatar;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean canExecute() {
        return avatar.getCarriedBy() == null;
    }

    @Override
    public void execute() {

        avatar.autoMoveToLocation(getDestinationX(), getDestinationY());
    }

    protected int getDestinationY() {
        int autoY = y - avatar.getHeight() / 2;
        autoY -= autoY % 2;
        return autoY;
    }

    protected int getDestinationX() {
        int autoX = x - avatar.getWidth() / 2;
        autoX -= autoX % 2; // Even numbered pixel only!
        return autoX;
    }
}
