package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class ElevatorKey extends Item {
    private boolean jumping = true;

    public ElevatorKey(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 32;
        grabbable = true;
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
        g.fillRect(4, 4, 4, 2);
        g.fillRect(12, 4, 4, 2);
        g.fillRect(4, 8, 4, 2);
        g.fillRect(20, 8, 4, 2);
        g.fillRect(20, 26, 4, 2);
        g.fillRect(4, 14, 12, 4);
        g.fillRect(4, 22, 12, 4);
        g.fillRect(20, 12, 4, 12);
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        if (carriedBy != null) {
            jumping = false;
        }

        if (jumping) {
            x = level.random.nextInt(8 * 28) + 28;
            y = level.random.nextInt(2 * 32) + (8 * 32);
        }
    }

}