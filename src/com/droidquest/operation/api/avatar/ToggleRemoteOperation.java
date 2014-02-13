package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.Remote;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which toggles the remote's state.
 */
public class ToggleRemoteOperation implements Operation {
    private final Level level;
    private final Item avatar;

    public ToggleRemoteOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return level.remote != null;
    }

    @Override
    public void execute() {
        if (!(avatar instanceof Remote)) {
            if (level.isElectricityEnabled()) {
                hideRemote();
            } else {
                summonRemote();
            }
        }

        level.setElectricityEnabled(!level.isElectricityEnabled());
    }

    private void hideRemote() {
        level.remote.carriedBy = null;
        level.remote.room = null;
    }

    private void summonRemote() {
        level.remote.x = 28;
        level.remote.y = -20;
        level.remote.carriedBy = level.getPlayer();
        level.remote.room = level.getPlayer().room;
    }
}
