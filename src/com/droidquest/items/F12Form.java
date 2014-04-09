package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class F12Form extends Item {
    private transient GateKeeper gk = null;

    public F12Form(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 32;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g.setColor(Color.white);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(8, 2, 4, 10);
        g.fillRect(12, 2, 8, 2);
        g.fillRect(12, 6, 4, 2);
        g.fillRect(12, 14, 4, 4);
        g.fillRect(4, 20, 4, 10);
        g.fillRect(12, 20, 8, 2);
        g.fillRect(20, 22, 4, 2);
        g.fillRect(16, 24, 4, 2);
        g.fillRect(12, 26, 4, 4);
        g.fillRect(16, 28, 8, 2);
        currentIcon = icons[0].getImage();
    }

    public void IsDropped() {
        for (int a = 0; a < level.items.size(); a++) {
            Item item = level.items.elementAt(a);
            if (item.getClass().toString().endsWith("GateKeeper")) {
                gk = (GateKeeper) item;
            }
        }

        if (gk != null) {
            if (Overlaps(gk)) {
                gk.PicksUp(this);
                gk.behavior = 1;
            }
        }
    }

}

