package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Item;

public class CameraDisable extends Material {
    public CameraDisable() {
        super(true, false);
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
        icon = new ImageIcon(bi);
    }


    public void TouchedByItem(Item item) {
        if (item == level.player) {
            if (level.currentViewer != level.player) {
                level.currentViewer = level.player;
            }
        }
    }
}
