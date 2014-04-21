package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.chipstuff.ChipCompiler;
import com.droidquest.decorations.TextBox;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpeedControl extends Item {
    private int direction;
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;

    public SpeedControl(int X, int Y, Room r, int dir) {
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
        }
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item item) {
        switch (direction) {
            case DIR_UP:
                ChipCompiler.chipSpeed++;
                break;
            case DIR_DOWN:
                if (ChipCompiler.chipSpeed == 1) {
                    return false;
                }
                ChipCompiler.chipSpeed--;
                break;
        }
        TextBox tb = room.textBoxes.elementAt(1);
        tb.textString = ChipCompiler.chipSpeed + "x";
        return false;
    }

}

