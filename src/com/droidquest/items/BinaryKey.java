package com.droidquest.items;

import com.droidquest.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BinaryKey extends Item {
    public BinaryKey(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 26;
        height = 28;
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
        g.setColor(new Color(255, 128, 0));
        g.fillRect(4, 0, 4, 8);
        g.fillRect(4, 10, 4, 2);
        g.fillRect(0, 12, 4, 4);
        g.fillRect(8, 12, 4, 4);
        g.fillRect(4, 16, 4, 2);
        g.fillRect(4, 20, 4, 8);
        g.fillRect(16, 0, 4, 2);
        g.fillRect(12, 2, 4, 4);
        g.fillRect(20, 2, 4, 4);
        g.fillRect(16, 6, 4, 2);
        g.fillRect(16, 10, 4, 8);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(12, 22, 4, 4);
        g.fillRect(20, 22, 4, 4);
        g.fillRect(16, 26, 4, 2);
        currentIcon = icons[0].getImage();
    }

}
