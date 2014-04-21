package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class PowerSwitch extends Item {
    // Controls the power in a Generic Robot

    public PowerSwitch(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 32;
        GenerateIcons();
        if (((GenericRobot) room.portalItem).thrusterPower) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }
    }

    public void GenerateIcons() {
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // 0 = Off
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
        g.fillRect(8, 0, 20, 4);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(0, 10, 4, 4);
        g.fillRect(4, 14, 4, 4);
        g.fillRect(8, 18, 4, 4);
        g.fillRect(12, 22, 4, 4);
        g.fillRect(8, 26, 12, 6);
        g.fillRect(20, 28, 8, 4);

        // 1 = On
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
        g.setColor(new Color(255, 128, 0));
        g.fillRect(8, 0, 20, 4);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(12, 8, 4, 18);
        g.fillRect(8, 26, 12, 6);
        g.fillRect(20, 28, 8, 4);

        if (((GenericRobot) room.portalItem).thrusterPower) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }

    }

    public boolean CanBePickedUp(Item i) {
        if (i != level.player) {
            return false;
        }
        ((GenericRobot) room.portalItem).thrusterPower = !((GenericRobot) room.portalItem).thrusterPower;
        if (((GenericRobot) room.portalItem).thrusterPower) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }
        return false;
    }

}

