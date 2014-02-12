package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which summons or banishes the toolbox, as appropriate.
 */
public class ToggleToolboxOperation implements Operation {
    private final Level level;
    private final Item avatar;

    public ToggleToolboxOperation(Level level, Item avatar) {
        this.level = level;
        this.avatar = avatar;
    }


    @Override
    public boolean canExecute() {
        return level.toolbox == null ||   // I don't know why the first matters, but that's the way it was before I got here...
                avatar.getCarrying() == null;
    }

    @Override
    public void execute() {
        if (level.toolbox == null)
        {
            if (avatar.getCarrying() != null)
                avatar.Drops();
            level.toolbox = new ToolBox(avatar.getX(), avatar.getY() + 8, avatar.getRoom());
            level.items.addElement(level.toolbox);
            level.toolbox.Toggle();
            avatar.PicksUp(level.toolbox);
        }

        if (level.toolbox.room != avatar.getRoom())
        {
            // Summon Toolbox
            if (avatar.getCarrying() != null) {
                return;
            }
            if (level.toolbox.open) {
                level.toolbox.Toggle();
            }
            level.toolbox.room = avatar.getRoom();
            level.toolbox.x = avatar.getX() + 28;
            level.toolbox.y = avatar.getY() + 6;
            avatar.PicksUp(level.toolbox);
        }
        else
            level.toolbox.Toggle();
    }
}
