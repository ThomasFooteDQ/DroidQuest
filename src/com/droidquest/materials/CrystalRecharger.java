package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class CrystalRecharger extends Material {
    // Charges a Crystal when one is passed over it.
    public CrystalRecharger() {
        super(true, false);
    }

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

        g.setColor(new Color(255, 128, 0));
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
    }

    public void TouchedByItem(Item item) {
        // Check to see if it's a pure Crystal, not a Black Crystal
        if (item.getClass().toString().endsWith("Crystal")) {
            item.charge = 100000;
            level.PlaySound(item.room, Level.CHARGESOUND);
        }
    }

}
