package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;

/**
 * Operation that sets the avatar moving in one of the cardinal directions
 * until it's forced to stop.
 */
public class MoveDirectionalOperation implements Operation {
    protected final Item avatar;
    protected final int x, y;

    public MoveDirectionalOperation(Item avatar, int x, int y) {
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
        avatar.autoMoveInDirection(getMoveDirection());
    }

    private Direction getMoveDirection() {
        int dx = getDeltaX();
        int dy = getDeltaY();
        if (Math.abs(dx) > Math.abs(dy)) {
            return (dx < 0) ? Direction.Left : Direction.Right;
        }
        else {
            return (dy < 0) ? Direction.Up : Direction.Down;
        }
    }

    protected int getDeltaX() {
        return x - avatar.getWidth() / 2 - avatar.getX();
    }

    private int getDeltaY() {
        return y - avatar.getHeight() / 2 - avatar.getY();
    }
}
