package com.droidquest.materials;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.Item;

import javax.swing.*;
import java.awt.*;

public class PlayerBlocker extends Material {
    private transient ImageIcon[] images;
    private String[] filenames;
    private int animationState = 0;

    public PlayerBlocker(Color col) {
        color = col;
        detectable = false;
    }

    public PlayerBlocker(String[] files) {
        detectable = false;
        filenames = files;
        GenerateIcons();
    }

    public void GenerateIcons() {
        if (filenames != null) {
            int numfiles = filenames.length;
            images = new ImageIcon[numfiles];
            for (int a = 0; a < filenames.length; a++) {
                images[a] = new ImageIcon("images/" + filenames[a]);
            }
            icon = images[0];
        }
        else {
            super.GenerateIcons();
        }
    }

    public void Animate() {
        if (images != null) {
            animationState++;
            if (animationState == images.length) {
                animationState = 0;
            }
            icon = images[animationState];
        }
    }

    public boolean Passable(Item item) {
        if (level.gameCursor.getClass().toString().endsWith("GameCursor")) {
            GameCursor gc = (GameCursor) level.gameCursor;
            if (gc.PlayerInRobot(null) == item) {
                return false;
            }
        }
        return item != level.player;
    }

}
