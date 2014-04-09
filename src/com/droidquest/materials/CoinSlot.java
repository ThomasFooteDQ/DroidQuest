package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class CoinSlot extends Material {
    public CoinSlot() {
        super(true, false);
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

        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);
        icon = new ImageIcon(bi);
    }

}

