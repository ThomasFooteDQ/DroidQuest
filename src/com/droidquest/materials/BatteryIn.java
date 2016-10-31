package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Crystal;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class BatteryIn extends Material {
    // Charges the Battery when an Energy Crystal is passed over it.

    public BatteryIn() {
        passable = true;
        GenerateIcons();
    }

    public transient Item robot;

    public void GenerateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        icon = new ImageIcon(bi);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }

        g.setColor(Color.white);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);
        g.fillRect(8, 8, 12, 16);
        g.fillRect(4, 10, 20, 12);
        g.fillRect(0, 14, 28, 4);
        g.fillRect(0, 8, 4, 2);
        g.fillRect(0, 22, 4, 2);
        g.fillRect(24, 8, 4, 2);
        g.fillRect(24, 22, 4, 2);
        passable = true;
    }

    public void TouchedByItem(Item item) {
        if (item.charge > 0) {
            // Check to see if it's a pure Crystal, not a Black Crystal
            if (item instanceof Crystal) {
                int empty = 100000 - robot.charge;
                if (empty >= item.charge) {
                    robot.charge += item.charge;
                    item.charge = 0;
                }
                else {
                    item.charge -= empty;
                    robot.charge = 100000;
                }
                level.PlaySound(robot.InternalRoom, Level.DISCHARGESOUND);
            }
        }
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (robot != null) {
                if (robot == ((BatteryIn) mat).robot) {
                    return true;
                }
            }
        }
        return false;
    }

}