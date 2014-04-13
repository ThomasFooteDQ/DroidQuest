package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class SentryT1 extends Item {
    // This sentry does nothing but pace back and forth in the first tutorial.
    // It walks left and right, and switches directions when it hits a wall.

    // Behavior values:
    // 0=Move Left
    // 1=Move Right

    private int animation = 0; // 0-3, 1 & 3 = icons[1]
    private int behavior = 0;  // 0+ for pacing, until pounce and drag. -1=pin.

    public SentryT1(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 18;
        orgY = 30;
        grabbable = false;
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[3];
        icons[0] = new ImageIcon(new BufferedImage(width, height + orgY, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height + orgY, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height + orgY, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // 0 = Legs out
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height + orgY);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 6);
        g.fillRect(12, 6, 4, 6);
        g.fillRect(8, 12, 12, 24);
        g.fillRect(4, 16, 20, 18);
        g.fillRect(0, 20, 28, 10);
        g.fillRect(4, 36, 8, 4);
        g.fillRect(4, 36, 4, 6);
        g.fillRect(0, 40, 4, 8);
        g.fillRect(16, 36, 8, 4);
        g.fillRect(20, 36, 4, 6);
        g.fillRect(24, 40, 4, 8);
        g.setColor(Color.black);
        g.fillRect(4, 22, 4, 6);
        g.fillRect(20, 22, 4, 6);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(8, 28, 4, 2);
        g.fillRect(16, 28, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(12, 30, 4, 2);
        g.fillRect(12, 22, 4, 6);


        // 1 = legs mid
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height + orgY);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 6);
        g.fillRect(12, 6, 4, 6);
        g.fillRect(8, 12, 12, 28);
        g.fillRect(4, 16, 20, 18);
        g.fillRect(0, 20, 28, 10);
        g.fillRect(4, 40, 8, 2);
        g.fillRect(16, 40, 8, 2);
        g.fillRect(4, 40, 4, 8);
        g.fillRect(20, 40, 4, 8);
        g.setColor(Color.black);
        g.fillRect(4, 22, 4, 6);
        g.fillRect(20, 22, 4, 6);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(8, 28, 4, 2);
        g.fillRect(16, 28, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(12, 30, 4, 2);
        g.fillRect(12, 22, 4, 6);


        // 2 = legs in
        try {
            g = icons[2].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height + orgY);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 6);
        g.fillRect(12, 6, 4, 36);
        g.fillRect(8, 12, 12, 24);
        g.fillRect(4, 16, 20, 18);
        g.fillRect(0, 20, 28, 10);
        g.fillRect(8, 40, 4, 8);
        g.fillRect(16, 40, 4, 8);
        g.setColor(Color.black);
        g.fillRect(4, 22, 4, 6);
        g.fillRect(20, 22, 4, 6);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(8, 28, 4, 2);
        g.fillRect(16, 28, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(12, 30, 4, 2);
        g.fillRect(12, 22, 4, 6);

        if (animation == 3) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[animation].getImage();
        }

    }

    public void Animate() {
        if (behavior < 3) {
            animation++;
        }
        if (animation == 4) {
            animation = 0;
        }
        if (animation == 3) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[animation].getImage();
        }

        int oldX;
        switch (behavior) {
            case 0:
                oldX = x;
                moveLeft(4);
                if (oldX == x) {
                    behavior = 1;
                }
                break;
            case 1:
                oldX = x;
                moveRight(4);
                if (oldX == x) {
                    behavior = 0;
                }
                break;
        }
    }

}
