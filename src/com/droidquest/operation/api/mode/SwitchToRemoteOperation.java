package com.droidquest.operation.api.mode;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Switches the current avatar to the remote.
 */
public class SwitchToRemoteOperation implements Operation {
    private final Item avatar;
    private final Level level;

    public SwitchToRemoteOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return level.remote != null;
    }

    @Override
    public void execute() {
        level.remote.setX(avatar.getX());
        level.remote.setY(avatar.getY());
        level.remote.setRoom(avatar.getRoom());
        avatar.setRoom(null);

        if (level.getCurrentViewer() == level.player)
            level.setCurrentViewer(level.remote);
        level.setPlayer(level.remote);
    }
}
