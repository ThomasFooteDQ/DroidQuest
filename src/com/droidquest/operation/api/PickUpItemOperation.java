package com.droidquest.operation.api;

import com.droidquest.items.Item;
import com.droidquest.items.Train;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which lets the current avatar pick up an item.
 */
public class PickUpItemOperation implements Operation {
    private final Item avatar;
    private final Level level;

    public PickUpItemOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }

    /**
     * There's a lot of weirdness around the Train object, mostly because Train.CanBePickedUp seems to have weird
     * side-effects.  Just saying.
     */
    @Override
    public boolean canExecute() {
        if (avatar.getCarrying() != null) {
            return true;
        }

        Item nearestItem = level.FindNearestItem(avatar);
        if (nearestItem != null) {  // Don't call CanBePickedUp() because some impl's have side effects?!
            return true;
        }

        return false;
    }

    /**
     * There's a lot of weirdness around the Train object, mostly because Train.CanBePickedUp seems to have weird
     * side-effects.  Just saying.
     */
    @Override
    public void execute() {
        Item item = level.FindNearestItem(avatar);
        if (item instanceof Train)
        {
            item.CanBePickedUp(avatar);
            return;
        }

        if (avatar.getCarrying() != null)
            avatar.Drops();
        else
        {
            if (item != null && item.CanBePickedUp(avatar)) {
                avatar.PicksUp(item);
            }
        }

        avatar.setOutline(null);
    }
}
