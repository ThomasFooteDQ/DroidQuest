package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class Hexagon extends Item {
    private Color color;

    public Hexagon(int X, int Y, Room r, Color c) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 28;
        color = c;
        editable = true;
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
        Graphics2D g2 = (Graphics2D) g;
        Color transparent = new Color(0, 0, 0, 0);
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(color);
        int[] xp = {7, 21, 27, 21, 7, 0};
        int[] yp = {0, 0, 14, 27, 27, 14};
        g.fillPolygon(xp, yp, 6);
        currentIcon = icons[0].getImage();
    }

}
