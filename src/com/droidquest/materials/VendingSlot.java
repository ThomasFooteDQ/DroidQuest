package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class VendingSlot extends Material {
    public VendingSlot() {
        super(false, true);
        GenerateIcons();
    }

    public void GenerateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }

        g.setColor(new Color(0, 0, 128));
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(0, 24, 28, 4);
        icon = new ImageIcon(bi);
    }

}
