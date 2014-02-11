package com.droidquest.operation.api.mode;

import com.droidquest.devices.GenericChip;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which switches to the Help avatar.
 */
public class HelpOperation implements Operation {
    private final Item avatar;
    private final Level level;

    public HelpOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return (avatar.getCarrying() instanceof GenericChip) ||
                level.helpCam != null;
    }

    @Override
    public void execute() {
        if (avatar.getCarrying() instanceof GenericChip) {
            ((GenericChip) avatar.getCarrying()).ShowText(true);
            return;
        }

        level.player = level.helpCam;
        level.currentViewer = level.helpCam;
    }
}
