package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.devices.Prototype16Chip;
import com.droidquest.levels.Level;

public class PC16Button extends Item {
    public PC16Button(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 26;
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
        g.setColor(Color.blue);
        g.fillRect(8, 0, 12, 26);
        g.fillRect(4, 2, 20, 22);
        g.fillRect(0, 4, 28, 18);
        g.setColor(Color.black);
        g.fillRect(8, 6, 12, 14);
        g.fillRect(4, 8, 20, 10);
        g.setColor(Color.blue);
        g.fillRect(12, 8, 4, 10);
        g.fillRect(8, 10, 12, 6);
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item i) {
        Prototype16Chip newPC = new Prototype16Chip(228, 160, room);
        level.items.addElement(newPC);
        level.PlaySound(room, Level.CHARGESOUND);
        return false;
    }

}
