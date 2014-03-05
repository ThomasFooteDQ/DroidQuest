package com.droidquest.operation.api.avatar;

import java.awt.Dimension;

import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Operation which exits the item we are currently inside (e.g. chip or robot).
 */
public class ExitItemOperation extends OperationAdapter {
    private final Level level;
    private final Item avatar;

    public ExitItemOperation(Level level, Item avatar) {
        super("Exit", "images/icon/load_upload.png");
        this.level = level;
        this.avatar = avatar;
    }

    @Override
    public boolean canExecute() {
        return avatar.getRoom() != null &&
                avatar.getRoom().getPortalItem() != null;
    }

    @Override
    public void execute() {
        Room room = avatar.getRoom();
        final Item portalItem = room.getPortalItem();
        Dimension d = portalItem.GetXY();
        int newX = d.width
                + portalItem.getWidth()/2
                - avatar.getWidth() / 2;
        int newY = d.height
                + portalItem.getHeight()/4*2
                - avatar.getHeight() / 2;
        avatar.setX(newX);
        avatar.setY(newY);
        avatar.SetRoom(portalItem.getRoom());
        level.setCurrentViewer(level.getPlayer());
    }
}
