package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Item;

public class CameraEnable extends Material {
    private transient Item hiddenCamera = null;

    public CameraEnable() {
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

        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 9, 12, 2);
        g.fillRect(8, 21, 12, 2);
        g.fillRect(4, 11, 4, 2);
        g.fillRect(4, 19, 4, 2);
        g.fillRect(20, 11, 4, 2);
        g.fillRect(20, 19, 4, 2);
        g.fillRect(0, 13, 4, 6);
        g.fillRect(24, 13, 4, 6);
        g.fillRect(8, 13, 12, 6);
        icon = new ImageIcon(bi);
    }

    public void TouchedByItem(Item item) {
        if (hiddenCamera == null) {
            for (int a = 0; a < level.items.size(); a++) {
                Item i = level.items.elementAt(a);
                if (i.getClass().toString().endsWith("HiddenCamera")) {
                    hiddenCamera = i;
                }
            }
        }
        if (item == level.player) {
            level.currentViewer = hiddenCamera;
        }
    }

}
