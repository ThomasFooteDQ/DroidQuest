package com.droidquest.operation.api.mode;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;

/**
 * Operation which switches to the SolderingPen avatar.
 */
public class SolderingPenOperation implements Operation {
    private final Level level;
    private final Item currentAvatar;
    private final Operation saveChipOperation;

    public SolderingPenOperation(Level level, Item currentAvatar, Operation saveChipOperation) {
        this.level = level;
        this.currentAvatar = currentAvatar;
        this.saveChipOperation = saveChipOperation;
    }

    @Override
    public boolean canExecute() {
        if (getCurrentLevel().solderingPen == null) {
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

        getCurrentLevel().solderingPen.x = currentAvatar.getX();
        getCurrentLevel().solderingPen.y = currentAvatar.getY();
        getCurrentLevel().solderingPen.room = currentAvatar.getRoom();
        currentAvatar.setRoom(null);

        if (getCurrentLevel().currentViewer == getCurrentLevel().player) {
            getCurrentLevel().currentViewer = getCurrentLevel().solderingPen;
        }

        getCurrentLevel().player = getCurrentLevel().solderingPen;
        if (getCurrentLevel().remote != null && getCurrentLevel().remote.carriedBy != null) {
            getCurrentLevel().remote.carriedBy = getCurrentLevel().player;
        }
    }

    private Level getCurrentLevel() {
        return level;
    }
}
