package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.operation.api.KeyRepeatTracker;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Operation which moves a player avatar in a given direction.
 */
public class MoveOperation extends OperationAdapter {
    private final Item avatar;
    private final Distance distance;
    private final Direction direction;
    private final KeyRepeatTracker keyRepeatTracker;

    public MoveOperation(Item avatar, Direction direction, Distance distance, KeyRepeatTracker keyRepeatTracker) {
        this.avatar = avatar;
        this.direction = direction;
        this.distance = distance;
        this.keyRepeatTracker = keyRepeatTracker;
    }

    @Override
    public String getName() {
        return distance.getLabel() + " " + direction.toString();
    }

    @Override
    public boolean canExecute() {
        return avatar.getCarriedBy() == null;
    }

    @Override
    public void execute() {
        keyRepeatTracker.reset();

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
