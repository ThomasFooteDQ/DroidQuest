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
    public void execute() {
        if (level.solderingPen == null) {
            return;
        }

        if (currentAvatar.getCarrying() != null) {
            if (saveChipOperation != null) {
                saveChipOperation.execute();
            }
            currentAvatar.Drops();
        }

        level.solderingPen.x = currentAvatar.getX();
        level.solderingPen.y = currentAvatar.getY();
        level.solderingPen.room = currentAvatar.getRoom();
        currentAvatar.setRoom(null);

        if (level.currentViewer == level.player) {
            level.currentViewer = level.solderingPen;
        }

        level.player = level.solderingPen;
        if (level.remote != null && level.remote.carriedBy != null) {
            level.remote.carriedBy = level.player;
        }
    }
}
