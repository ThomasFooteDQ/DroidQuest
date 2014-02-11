package com.droidquest.operation.api.move;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;

/**
 * Operation which moves a player avatar to the right.
 */
public class MoveOperation implements Operation {
    private final Item avatar;
    private final Distance distance;
    private final Direction direction;

    public MoveOperation(Item avatar, Direction direction, Distance distance) {
        this.avatar = avatar;
        this.direction = direction;
        this.distance = distance;
    }

    @Override
    public boolean canExecute() {
        return avatar.getCarriedBy() == null;
    }

    @Override
    public void execute() {
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
        avatar.setRepeating(0);
    }
}
