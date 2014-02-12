package com.droidquest.operation.api.mode;

import com.droidquest.avatars.PaintBrush;
import com.droidquest.operation.Operation;

/**
 * Toggles the "color" of the paint painted by the paintbrush.
 */
public class TogglePaintColorOperation implements Operation {
    private final PaintBrush avatar;

    public TogglePaintColorOperation(PaintBrush avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return false;
    }

    @Override
    public void execute() {
        avatar.setPaintIndex(avatar.getPaintIndex() <= 3 ? avatar.getPaintIndex() + 1 : 0);
        avatar.setCurrentIcon(avatar.icons[avatar.getPaintIndex()].getImage());
    }
}
