package com.droidquest.operation.api.mode;

import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which toggles the remote's state.
 */
public class ToggleRemoteOperation implements Operation {
    private final Level level;

    public ToggleRemoteOperation(Level level) {
        this.level = level;
    }

    @Override
    public boolean canExecute() {
        return level.remote != null;
    }

    @Override
    public void execute() {
        if (level.remote.carriedBy == null)
        { // Summon Remote
            level.remote.x = 28;
            level.remote.y = -20;
            level.remote.carriedBy = level.player;
            level.remote.room = level.player.room;
            level.electricity = true;
        }
        else
        { // Hide Remote
            level.remote.carriedBy = null;
            level.remote.room = null;
            level.electricity = false;
        }
        //	     if (carrying != null)
        //	       Drops();
        //	     level.remote.x = x;
        //	     level.remote.y = y;
        //	     level.remote.room = room;
        //	     room = null;
        //	     if (level.currentViewer == level.player)
        //	       level.currentViewer=level.remote;
        //	     level.player = level.remote;
    }
}
