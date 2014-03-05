package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.operation.api.KeyRepeatTracker;
import com.droidquest.operation.api.OperationAdapter;

/**
 * The Repeated (KeyDown) version of the move operation.
 */
public class MoveRepeatOperation extends OperationAdapter {
    private final Item avatar;
    private final Direction direction;
    private final Distance distance;
    private final KeyRepeatTracker keyRepeatTracker;

    public MoveRepeatOperation(Item avatar, Direction direction, Distance distance, KeyRepeatTracker keyRepeatTracker) {
        this.avatar = avatar;
        this.direction = direction;
        this.distance = distance;
        this.keyRepeatTracker = keyRepeatTracker;
    }

    @Override
    public boolean canExecute() {
        return avatar.getCarriedBy() == null;
    }

    @Override
    public void execute() {
        if (!keyRepeatTracker.isStarted()) {
            keyRepeatTracker.start();
            return;
        }

        if (!keyRepeatTracker.isRepeating()) {
            return;
        }

        switch (direction) {
            case Up:
                avatar.MoveUp(distance == Distance.Nudge);
                break;
            case Right:
                avatar.MoveRight(distance == Distance.Nudge);
                break;
            case Down:
                avatar.MoveDown(distance == Distance.Nudge);
                break;
            case Left:
                avatar.MoveLeft(distance == Distance.Nudge);
                break;
        }
    }
}
