package com.droidquest.operation.api.avatar;

import com.droidquest.levels.Level;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Operation which returns directly to the game cursor.
 */
public class ReturnToGameCursorOperation extends OperationAdapter {
    private final Level level;

    public ReturnToGameCursorOperation(Level level) {
        this.level = level;
    }

    @Override
    public boolean canExecute() {
        return level.gameCursor != null;
    }

    @Override
    public void execute() {
        level.setPlayer(level.gameCursor);
        level.setCurrentViewer(level.gameCursor);
    }
}
