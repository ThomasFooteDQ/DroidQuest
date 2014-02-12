package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which moves an avatar an entire room away.  It's a cheat, of course.
 */
public class SetRoomOperation implements Operation {
    private final Item avatar;
    private final Level level;
    private final Direction direction;
    private final boolean cheatRequired;

    public SetRoomOperation(Level level, Item avatar, Direction direction) {
        this(level, avatar, direction, false);
    }

    public SetRoomOperation(Level level, Item avatar, Direction direction, boolean isCheatRequired) {
        this.level = level;
        this.avatar = avatar;
        this.direction = direction;
        this.cheatRequired = isCheatRequired;
    }

    @Override
    public boolean canExecute() {
        if (cheatRequired && !level.cheatmode) {
            return false;
        }

        switch (direction) {
            case Up:
                return avatar.getRoom().getUpRoom() != null;
            case Right:
                return avatar.getRoom().getRightRoom() != null;
            case Down:
                return avatar.getRoom().getDownRoom() != null;
            case Left:
                return avatar.getRoom().getLeftRoom() != null;
        }

        return false;
    }

    @Override
    public void execute() {
        switch (direction) {
            case Up:
                avatar.SetRoom(avatar.getRoom().getUpRoom());
                break;
            case Right:
                avatar.SetRoom(avatar.getRoom().getRightRoom());
                break;
            case Down:
                avatar.setRoom(avatar.getRoom().getDownRoom());
                break;
            case Left:
                avatar.setRoom(avatar.getRoom().getLeftRoom());
                break;
        }
    }
}
