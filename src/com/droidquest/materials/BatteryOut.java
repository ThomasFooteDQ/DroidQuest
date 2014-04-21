package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Item;

public class BatteryOut extends Material {
    // Graph that shows the battery charge in a Generic Robot

    public BatteryOut() {
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

        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(4, 0, 8, 32);
        g.fillRect(18, 0, 10, 32);
        g.fillRect(0, 8, 4, 4);
        g.fillRect(0, 20, 4, 4);
    }

    public void Animate() {
        Graphics g;
        try {
            g = icon.getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g.setColor(Color.black);
        g.fillRect(12, 0, 6, 32);
        g.setColor(new Color(255, 128, 0));
        if (robot != null) {
            int fuel = (robot.charge + 1564) / 3125; // 3125 = 100,000/32
            g.fillRect(12, 32 - fuel, 6, fuel);
        }
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (robot != null) {
                if (robot == ((BatteryOut) mat).robot) {
                    return true;
                }
            }
        }
        return false;
    }

}
