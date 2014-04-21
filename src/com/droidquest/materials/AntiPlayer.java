package com.droidquest.materials;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

import java.awt.*;

public class AntiPlayer extends Material {
    // Sends the Player (in or out of a robot) to the Main Office

    public AntiPlayer() {
        super(Color.black, true, false);
    }

    public void TouchedByItem(Item item) {
        boolean trigger = false;
        if (item == level.player) {
            trigger = true;
        }
        else if (item instanceof GenericRobot) {
            GameCursor gc = (GameCursor) level.gameCursor;
            if (gc.PlayerInRobot(null) == item) {
                trigger = true;
            }
        }

        if (trigger) {
            level.player.room = level.rooms.elementAt(40);
            level.player.x = 10 * 28;
            level.player.y = 5 * 32;
            level.currentViewer = level.player;
        }
    }

}
