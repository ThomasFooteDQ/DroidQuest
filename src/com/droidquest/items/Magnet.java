package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class Magnet extends Item {
    public Magnet(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 20;
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
        g.setColor(Color.white);
        g.fillRect(0, 4, 8, 12);
        g.fillRect(4, 2, 8, 6);
        g.fillRect(4, 12, 8, 6);
        g.fillRect(8, 0, 20, 6);
        g.fillRect(8, 14, 20, 6);
        g.setColor(Color.black);
        g.fillRect(20, 0, 4, 6);
        g.fillRect(20, 14, 4, 6);
        currentIcon = icons[0].getImage();
    }

}

