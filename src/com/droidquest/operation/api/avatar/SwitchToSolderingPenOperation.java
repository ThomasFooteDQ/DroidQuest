package com.droidquest.operation.api.avatar;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which switches to the SolderingPen avatar.
 */
public class SwitchToSolderingPenOperation implements Operation {
    private final Level level;
    private final Item currentAvatar;
    private final Operation saveChipOperation;

    public SwitchToSolderingPenOperation(Level level, Item currentAvatar, Operation saveChipOperation) {
        this.level = level;
        this.currentAvatar = currentAvatar;
        this.saveChipOperation = saveChipOperation;
    }

    @Override
    public boolean canExecute() {
        if (getLevel().solderingPen == null) {
            return false;
        }

        return true;
    }

    @Override
    public void execute() {
        if (!canExecute()) {
            return;
        }

        if (currentAvatar.getCarrying() != null) {
            if (saveChipOperation != null && saveChipOperation.canExecute()) {
                saveChipOperation.execute();
            }
            currentAvatar.Drops();
        }

        getLevel().solderingPen.x = currentAvatar.getX();
        getLevel().solderingPen.y = currentAvatar.getY();
        getLevel().solderingPen.room = currentAvatar.getRoom();
        currentAvatar.setRoom(null);

        if (getLevel().currentViewer == getLevel().getPlayer()) {
            getLevel().setCurrentViewer(getLevel().solderingPen);
        }

        getLevel().setPlayer(getLevel().solderingPen);
        if (getLevel().remote != null && getLevel().remote.carriedBy != null) {
            getLevel().remote.carriedBy = getLevel().getPlayer();
        }
    }

    private Level getLevel() {
        return level;
    }
}
