package com.droidquest.items;

import com.droidquest.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sweeper extends Item {
    private int animationState;
// 1=Moving Left
// 2=Stopped and facing camera
// 3=Moving Right
// 4=Stopped and facing camera

    public Sweeper(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 48;
        height = 32;
        grabbable = false;
        GenerateIcons();
        currentIcon = icons[0].getImage();
        animationState = 1;
    }

    public void GenerateIcons() {
        icons = new ImageIcon[3];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // sweeper1.gif = Moving Right
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(12, 0, 12, 2);
        g.fillRect(8, 2, 20, 10);
        g.fillRect(8, 12, 24, 2);
        g.fillRect(4, 14, 28, 8);
        g.fillRect(4, 22, 36, 6);
        g.fillRect(0, 28, 40, 4);
        g.fillRect(28, 4, 8, 2);
        g.fillRect(36, 2, 4, 6);
        g.fillRect(32, 14, 12, 2);
        g.fillRect(40, 12, 8, 2);
        g.fillRect(40, 16, 8, 2);
        g.setColor(Color.black);
        g.fillRect(12, 8, 4, 4);
        g.fillRect(20, 8, 4, 4);
        g.fillRect(8, 16, 4, 2);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(8, 26, 4, 2);
        g.fillRect(16, 18, 4, 2);
        g.fillRect(16, 22, 4, 2);
        g.fillRect(20, 26, 4, 2);
        g.fillRect(24, 16, 4, 2);
        g.fillRect(24, 20, 4, 2);
        g.fillRect(28, 24, 4, 2);
        g.fillRect(32, 28, 4, 2);

        // sweeper2.gif = Looking Straight
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(18, 0, 12, 2);
        g.fillRect(14, 2, 20, 10);
        g.fillRect(10, 12, 28, 20);
        g.fillRect(6, 28, 36, 4);
        g.setColor(Color.black);
        g.fillRect(22, 2, 4, 6);
        g.fillRect(18, 4, 12, 2);
        g.fillRect(18, 8, 4, 4);
        g.fillRect(26, 8, 4, 4);
        g.fillRect(14, 12, 4, 6);
        g.fillRect(30, 12, 4, 6);
        g.fillRect(14, 20, 4, 2);
        g.fillRect(14, 24, 4, 2);
        g.fillRect(14, 28, 4, 2);
        g.fillRect(22, 16, 4, 2);
        g.fillRect(22, 20, 4, 2);
        g.fillRect(22, 24, 4, 2);
        g.fillRect(22, 28, 4, 2);
        g.fillRect(30, 20, 4, 2);
        g.fillRect(30, 24, 4, 2);
        g.fillRect(30, 28, 4, 2);

        // sweeper3.gif = Moving Left
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
        g.fillRect(24, 0, 12, 2);
        g.fillRect(20, 2, 20, 10);
        g.fillRect(16, 12, 24, 2);
        g.fillRect(16, 14, 28, 8);
        g.fillRect(12, 22, 36, 6);
        g.fillRect(8, 28, 40, 4);
        g.fillRect(12, 4, 8, 2);
        g.fillRect(8, 2, 4, 6);
        g.fillRect(4, 14, 12, 2);
        g.fillRect(0, 12, 8, 2);
        g.fillRect(0, 16, 8, 2);
        g.setColor(Color.black);
        g.fillRect(24, 8, 4, 4);
        g.fillRect(32, 8, 4, 4);
        g.fillRect(36, 16, 4, 2);
        g.fillRect(36, 20, 4, 2);
        g.fillRect(36, 26, 4, 2);
        g.fillRect(28, 18, 4, 2);
        g.fillRect(28, 22, 4, 2);
        g.fillRect(24, 26, 4, 2);
        g.fillRect(20, 16, 4, 2);
        g.fillRect(20, 20, 4, 2);
        g.fillRect(16, 24, 4, 2);
        g.fillRect(12, 28, 4, 2);

        switch (animationState) {
            case 1:
                currentIcon = icons[0].getImage();
                break;
            case 3:
                currentIcon = icons[2].getImage();
                break;
            case 2:
            case 4:
                currentIcon = icons[1].getImage();
                break;
        }
    }

    public void Animate() {
        for (int a = 0; a < level.items.size(); a++) {
            Item testItem = level.items.elementAt(a);
            if (testItem.carriedBy == null) {
                if (Overlaps(testItem)) {
                    testItem.x = 280;
                    testItem.y = 192;
                    testItem.SetRoom(level.rooms.elementAt(1));
                }
            }
        }

        switch (animationState) {
            case 1:
                if (room == level.rooms.elementAt(14) && x >= 120) {
                    animationState = 2;
                    currentIcon = icons[1].getImage();
                }
                else {
                    moveRight(8);
                }
                break;

            case 2:
                animationState = 3;
                currentIcon = icons[2].getImage();
                break;

            case 3:
                if (room == level.rooms.elementAt(2) && x <= 504) {
                    animationState = 4;
                    currentIcon = icons[2].getImage();
                }
                else {
                    moveLeft(8);
                }
                break;

            case 4:
                animationState = 1;
                currentIcon = icons[0].getImage();
        }
    }

}

