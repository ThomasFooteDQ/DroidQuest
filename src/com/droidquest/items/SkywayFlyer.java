package com.droidquest.items;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.levels.Level;

public class SkywayFlyer extends Item {
    private int speed;
    private String[] filenames;
    private int animationState = 0;

    public SkywayFlyer(int X, int Y, Room r, String[] f, int spd) {
        x = X;
        y = Y;
        room = r;
        width = 24;
        height = 32;
        orgX = 2;
        filenames = f;
        speed = spd;
        grabbable = false;
    }

    public void GenerateIcons() {
        icons = new ImageIcon[filenames.length];
        for (int a = 0; a < filenames.length; a++) {
            icons[a] = new ImageIcon("images/" + filenames[a]);
        }
        currentIcon = icons[0].getImage();
    }

    public void moveUp(int dist) {
        y = y - dist;
        if (y < 32) {
            y = 320;
        }
    }

    public void moveDown(int dist) {
        y = y + dist;
        if (y > 320) {
            y = 32;
        }
    }

    public void Animate() {
        animationState++;
        if (animationState == filenames.length) {
            animationState = 0;
        }
        currentIcon = icons[animationState].getImage();

        if (speed < 0) {
            moveUp(-speed);
        }
        else {
            moveDown(speed);
        }

        if (Overlaps(level.player)) {
            level.PlaySound(room, Level.DISCHARGESOUND);
            level.player.x = 2 * 28;
            level.player.y = 8 * 32;
            level.player.SetRoom(room.downRoom);
        }
    }

}
