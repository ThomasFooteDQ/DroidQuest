package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.Item;

public class RobotBlocker extends Material {
    // Creates a Material that blocks one particular Class of Robot.
    private Item robot;

    public RobotBlocker(Item rob, Color col) {
        robot = rob;
        color = col;
        passable = true;
        detectable = false;
    }

    public boolean Passable(Item item) {
        if (level.gameCursor.getClass().toString().endsWith("GameCursor")) {
            GameCursor gc = (GameCursor) level.gameCursor;
            if (gc.PlayerInRobot(null) == robot) {
                return false;
            }
        }
        return !(item == robot || item == level.player);
    }

}
