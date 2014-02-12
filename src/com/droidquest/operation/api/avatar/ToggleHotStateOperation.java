package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.LabCursor;
import com.droidquest.operation.Operation;

/**
 * Operation which toggles the "hot" state of the LabCursor.
 */
public class ToggleHotStateOperation implements Operation {
    private final LabCursor avatar;

    public ToggleHotStateOperation(LabCursor avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        avatar.setHot(!avatar.isHot());
        if (avatar.isHot())
            avatar.setCurrentIcon(avatar.icons[1].getImage());
        else
            avatar.setCurrentIcon(avatar.icons[0].getImage());
    }
}
