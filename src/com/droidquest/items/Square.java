package com.droidquest.items;

import com.droidquest.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Square extends Item {
    private Color color;

    public Square(int X, int Y, Room r, Color c) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 28;
        editable = true;
        color = c;
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

        g.setColor(color);
        g.fillRect(0, 0, 28, 28);
        currentIcon = icons[0].getImage();
    }

}
