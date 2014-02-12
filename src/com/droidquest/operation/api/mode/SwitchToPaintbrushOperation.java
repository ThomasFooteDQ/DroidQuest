package com.droidquest.operation.api.mode;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Switches the current avatar to the Paintbrush.
 */
public class SwitchToPaintbrushOperation implements Operation {
    private final Level level;
    private final Item avatar;

    public SwitchToPaintbrushOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return level.paintbrush != null;
    }

    @Override
    public void execute() {
        if (avatar.getCarrying() != null)
            avatar.Drops();

        if (avatar instanceof SolderingPen) {
            SolderingPen pen = (SolderingPen) avatar;
            if (pen.getPort(0).getWire() != null)
                pen.getPort(0).getWire().Remove();

            //  I don't know if this part is necessary, but it was here before I got here...
            level.paintbrush.setX(pen.getX());
            level.paintbrush.setY(pen.getY());
        }
        else {
            level.paintbrush.setX((avatar.getX() / 28) * 28);
            level.paintbrush.setY((avatar.getY() / 32) * 32);
        }

        level.paintbrush.setRoom(avatar.getRoom());
        avatar.setRoom(null);

        if (level.getCurrentViewer() == level.player)
            level.setCurrentViewer(level.paintbrush);
        level.setPlayer(level.paintbrush);
    }
}
