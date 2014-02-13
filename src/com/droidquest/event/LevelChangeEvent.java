package com.droidquest.event;

import java.util.EventObject;

import com.droidquest.Game;
import com.droidquest.levels.Level;

/**
 * Event that is published when the current level changes.
 */
public class LevelChangeEvent extends EventObject {
    private final Level oldLevel;
    private final Level newLevel;

    public LevelChangeEvent(Game source, Level oldLevel, Level newLevel) {
        super(source);

        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public Level getOldLevel() {
        return oldLevel;
    }

    public Level getNewLevel() {
        return newLevel;
    }
}
