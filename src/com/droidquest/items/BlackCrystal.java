package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class BlackCrystal extends Crystal {
    public BlackCrystal(int X, int Y, Room r) {
        super(X, Y, r, 10);
    }

    public void GenerateIcons() {
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // 0 = blue
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.black);
        g.fillRect(12, 0, 4, 24);
        g.fillRect(8, 4, 12, 16);
        g.fillRect(4, 6, 20, 12);
        g.fillRect(0, 10, 28, 4);
        g.fillRect(0, 4, 4, 2);
        g.fillRect(0, 18, 4, 2);
        g.fillRect(24, 4, 4, 2);
        g.fillRect(24, 18, 4, 2);
        currentIcon = icons[0].getImage();
    }

    public void Decorate() {
    }

}