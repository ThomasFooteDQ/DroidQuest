package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which moves the player into an item (e.g. Chip or robot) that they are current overlapping.
 */
public class EnterItemOperation implements Operation {
    private final Item avatar;
    private final Level level;

    public EnterItemOperation(Level level, Item avatar) {
        this.avatar = avatar;
        this.level = level;
    }

    @Override
    public boolean canExecute() {
        Item item = level.FindNearestItem(avatar);
        if (item == null || item.getInternalRoom() == null) {
            return false;
        }

        return avatar.Overlaps(item) && !item.OverWall();
    }

    @Override
    public void execute() {
        Item item = level.FindNearestItem(avatar);
        int newX = 280; // 10 * 28
        int newY = 176; // 5.5 * 32
        avatar.setX(newX);
        avatar.setY(newY);

        avatar.SetRoom(item.getInternalRoom());
    }
}
