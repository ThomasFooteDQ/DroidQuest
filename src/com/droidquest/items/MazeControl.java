package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.decorations.TextBox;

public class MazeControl extends Item {
    static int mazeWidth = 4;
    static int mazeHeight = 2;
    private int direction;
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;

    public MazeControl(int X, int Y, Room r, int dir) {
        x = X;
        y = Y;
        room = r;
        width = 26;
        height = 26;
        direction = dir;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);
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
        switch (direction) {
            case DIR_UP:
                g.fillRect(12, 0, 2, 2);
                g.fillRect(10, 2, 6, 2);
                g.fillRect(8, 4, 10, 2);
                g.fillRect(6, 6, 14, 2);
                g.fillRect(4, 8, 18, 2);
                g.fillRect(2, 10, 22, 2);
                g.fillRect(0, 12, 26, 2);
                g.fillRect(10, 14, 6, 12);
                break;
            case DIR_RIGHT:
                g.fillRect(24, 12, 2, 2);
                g.fillRect(22, 10, 2, 6);
                g.fillRect(20, 8, 2, 10);
                g.fillRect(18, 6, 2, 14);
                g.fillRect(16, 4, 2, 18);
                g.fillRect(14, 2, 2, 22);
                g.fillRect(12, 0, 2, 26);
                g.fillRect(0, 10, 12, 6);
                break;
            case DIR_DOWN:
                g.fillRect(12, 24, 2, 2);
                g.fillRect(10, 22, 6, 2);
                g.fillRect(8, 20, 10, 2);
                g.fillRect(6, 18, 14, 2);
                g.fillRect(4, 16, 18, 2);
                g.fillRect(2, 14, 22, 2);
                g.fillRect(0, 12, 26, 2);
                g.fillRect(10, 0, 6, 12);
                break;
            case DIR_LEFT:
                g.fillRect(0, 12, 2, 2);
                g.fillRect(2, 10, 2, 6);
                g.fillRect(4, 8, 2, 10);
                g.fillRect(6, 6, 2, 14);
                g.fillRect(8, 4, 2, 18);
                g.fillRect(10, 2, 2, 22);
                g.fillRect(12, 0, 2, 26);
                g.fillRect(14, 10, 12, 6);
                break;
        }
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item item) {
        switch (direction) {
            case DIR_UP:
                if (mazeHeight == 1) {
                    return false;
                }
                mazeHeight--;
                break;
            case DIR_DOWN:
                mazeHeight++;
                break;
            case DIR_LEFT:
                if (mazeWidth == 1) {
                    return false;
                }
                mazeWidth--;
                break;
            case DIR_RIGHT:
                mazeWidth++;
                break;
        }
        TextBox tb = room.textBoxes.elementAt(1);
        tb.textString = mazeWidth + "x" + mazeHeight;
        tb.x = (560 - 12 * tb.textString.length()) / 2;

        return false;
    }

}

