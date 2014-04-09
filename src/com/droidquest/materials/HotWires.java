package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class HotWires extends Material {
    // Hot orange wires that burn charge of any object that passes over it.
    // Cold = white.
    // Create shape by adding the direction values. UP+DOWN, LEFT+RIGHT, UP+LEFT, etc...

    private transient ImageIcon[] images;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public boolean value;
    int wall;

    public HotWires(int w, boolean v) {
        passable = true;
        detectable = false;
        wall = w;
        value = v;
    }

    public void GenerateIcons() {
        images = new ImageIcon[2];
        for (int a = 0; a < 2; a++) {
            images[a] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
            Graphics g;
            try {
                g = images[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setBackground(Color.black);
            g2.clearRect(0, 0, 28, 32);
            if (a == 0) {
                g2.setColor(Color.white);
            }
            else {
                g2.setColor(new Color(255, 128, 0));
            }
            if ((wall & UP) == UP) {
                g.fillRect(13, 0, 2, 16);
            }
            if ((wall & DOWN) == DOWN) {
                g.fillRect(13, 16, 2, 16);
            }
            if ((wall & LEFT) == LEFT) {
                g.fillRect(0, 15, 14, 2);
            }
            if ((wall & RIGHT) == RIGHT) {
                g.fillRect(14, 15, 14, 2);
            }
        }

        icon = images[0];
        if (value) {
            icon = images[1];
        }
    }

    public void TouchedByItem(Item item) {
        if (value) {
            if (item.charge > 0) {
                item.charge = 0;
                level.PlaySound(item.room, Level.DISCHARGESOUND);
            }
        }
    }

    public void Animate() {
        icon = images[0];
        if (value) {
            icon = images[1];
        }
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (value == ((HotWires) mat).value
                    && wall == ((HotWires) mat).wall) {
                return true;
            }
        }
        return false;
    }

}
