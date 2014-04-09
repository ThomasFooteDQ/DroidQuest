package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class Crystal extends Item {
    private int color = 0; // 0 = blue; 1 = orange

    public Crystal(int X, int Y, Room r, int ch) {
        x = X;
        y = Y;
        room = r;
        charge = ch;
        width = 28;
        height = 24;
        editable = true;
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[3];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
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
        g.setColor(Color.blue);
        g.fillRect(12, 0, 4, 24);
        g.fillRect(8, 4, 12, 16);
        g.fillRect(4, 6, 20, 12);
        g.fillRect(0, 10, 28, 4);
        g.fillRect(0, 4, 4, 2);
        g.fillRect(0, 18, 4, 2);
        g.fillRect(24, 4, 4, 2);
        g.fillRect(24, 18, 4, 2);

        // 1 = orange
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(new Color(255, 128, 0));
        g.fillRect(12, 0, 4, 24);
        g.fillRect(8, 4, 12, 16);
        g.fillRect(4, 6, 20, 12);
        g.fillRect(0, 10, 28, 4);
        g.fillRect(0, 4, 4, 2);
        g.fillRect(0, 18, 4, 2);
        g.fillRect(24, 4, 4, 2);
        g.fillRect(24, 18, 4, 2);

        // 2 = white
        try {
            g = icons[2].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(12, 0, 4, 24);
        g.fillRect(8, 4, 12, 16);
        g.fillRect(4, 6, 20, 12);
        g.fillRect(0, 10, 28, 4);
        g.fillRect(0, 4, 4, 2);
        g.fillRect(0, 18, 4, 2);
        g.fillRect(24, 4, 4, 2);
        g.fillRect(24, 18, 4, 2);

        currentIcon = icons[color].getImage();
        if (charge == 0) {
            currentIcon = icons[2].getImage();
        }
    }

    public void Decorate() {
        if (charge > 0) {
            color = 1 - color;
            currentIcon = icons[color].getImage();
        }
        else {
            currentIcon = icons[2].getImage();
        }
    }

}

