package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Switches the current avatar to the regular game cursor.
 */
public class SwitchToGameCursorOperation implements Operation {
    private final Level level;
    private final Item avatar;

    public SwitchToGameCursorOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return level.gameCursor != null;
    }

    @Override
    public void execute() {
        if (avatar instanceof SolderingPen) {
            SolderingPen pen = (SolderingPen) avatar;
            if (pen.getPort(0).getWire() != null) {
                pen.getPort(0).getWire().Remove();
            }
        }
        level.gameCursor.setX(avatar.getX());
        level.gameCursor.setY(avatar.getY());
        level.gameCursor.setRoom(avatar.getRoom());
        avatar.setRoom(null);

        if (level.getCurrentViewer() == level.player)
            level.setCurrentViewer(level.gameCursor);
        level.setPlayer(level.gameCursor);

        if (level.remote != null && level.remote.getCarriedBy() != null) {
            level.remote.setCarriedBy(level.player);
        }
    }
}
