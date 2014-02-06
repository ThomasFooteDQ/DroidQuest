package com.droidquest.controllers;

import com.droidquest.levels.Level;

/**
 * Singleton context object containing game-wide variables and resources.
 */
public class GameContext {
    private Level currentLevel;

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
