package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

public class ForceField extends Material {
    private String robotClassName = null;

    public ForceField(String rc, Color c) {
        super(true, false);
        robotClassName = rc;
        color = c;
        GenerateIcons();
    }

    public void GenerateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(color);
        g.fillRect(12, 0, 4, 32);
        icon = new ImageIcon(bi);
    }

    public boolean Passable(Item item) {
        if (item == level.player) {
            return false;
        }
        else if (item instanceof GenericRobot) {
            GameCursor gc = (GameCursor) level.gameCursor;
            if (gc.PlayerInRobot(null) == item) {
                return false;
            }
        }

        return !item.getClass().toString().endsWith(robotClassName);

    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (robotClassName.equals(((ForceField) mat).robotClassName)) {
                return true;
            }
        }
        return false;
    }

}
