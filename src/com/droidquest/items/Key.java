package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class Key extends Item {
    // Generic Key, defined by it's color.

    public Color color;

    public Key(int X, int Y, Room r, Color c) {
        x = X;
        y = Y;
        room = r;
        color = c;
        width = 26;
        height = 8;
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
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        Color transparent = new Color(0, 0, 0, 0);
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(color);
        g.fillRect(0, 0, 6, 8);
        g.fillRect(0, 2, 26, 2);
        g.fillRect(0, 4, 22, 2);
        g.fillRect(16, 6, 2, 2);
        currentIcon = icons[0].getImage();
    }

}
