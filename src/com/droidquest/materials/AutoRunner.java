package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.items.Item;

public class AutoRunner extends Material {
    private int direction;
    public static final int UP = 0;
    public static final int RIGHTUP = 1;
    public static final int RIGHT = 2;
    public static final int RIGHTDOWN = 3;
    public static final int DOWN = 4;
    public static final int LEFTDOWN = 5;
    public static final int LEFT = 6;
    public static final int LEFTUP = 7;
    public static final int STOP = 8;

    public AutoRunner(int d) {
        super(Color.black, true, false);
        direction = d;
    }

    public void TouchedByItem(Item item) {
        if (item == level.player) {
            switch (direction) {
                case UP:
                    level.player.autoX = level.player.x;
                    level.player.autoY = level.player.y - 32;
                    level.player.automove = 1;
                    break;
                case RIGHTUP:
                    level.player.autoX = level.player.x + 28;
                    level.player.autoY = level.player.y - 32;
                    level.player.automove = 1;
                    break;
                case RIGHT:
                    level.player.autoX = level.player.x + 28;
                    level.player.autoY = level.player.y;
                    level.player.automove = 1;
                    break;
                case RIGHTDOWN:
                    level.player.autoX = level.player.x + 28;
                    level.player.autoY = level.player.y + 32;
                    level.player.automove = 1;
                    break;
                case DOWN:
                    level.player.autoX = level.player.x;
                    level.player.autoY = level.player.y + 32;
                    level.player.automove = 1;
                    break;
                case LEFTDOWN:
                    level.player.autoX = level.player.x - 28;
                    level.player.autoY = level.player.y + 32;
                    level.player.automove = 1;
                    break;
                case LEFT:
                    level.player.autoX = level.player.x - 28;
                    level.player.autoY = level.player.y;
                    level.player.automove = 1;
                    break;
                case LEFTUP:
                    level.player.autoX = level.player.x - 28;
                    level.player.autoY = level.player.y - 32;
                    level.player.automove = 1;
                    break;
                case STOP:
                    level.player.automove = 0;
                    break;
            }
        }
    }

}
