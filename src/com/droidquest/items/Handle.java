package com.droidquest.items;

import com.droidquest.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Handle extends Item {
    // Handle used to pull sliding wall

    public Handle(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 12;
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
        g.setColor(Color.white);
        g.fillRect(0, 4, 16, 4);
        g.fillRect(16, 2, 12, 8);
        g.fillRect(20, 0, 4, 12);
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item item) {
        if (item != level.player) {
            return false;
        }
        PicksUp(item);
        level.player = this;
        return false;
    }

    public boolean KeyUp(KeyEvent e) {
        if (e.getKeyCode() == e.VK_RIGHT) {
            if (x < 15 * 28) {
                room.SetMaterial(x / 28 - 12, 4, 0);
                moveRight(28);
                room.SetMaterial(x / 28 - 1, 4, 8);
            }
        }

        if (e.getKeyCode() == e.VK_LEFT) {
            if (x > 13 * 28) {
                room.SetMaterial(x / 28 - 13, 4, 8);
                room.SetMaterial(x / 28 - 1, 4, 0);
                moveLeft(28);
            }
        }

        if (e.getKeyCode() == e.VK_SPACE) {
            level.player = carrying;
            Drops();
            room.SetMaterial(1, 4, 8);
            room.SetMaterial(2, 4, 8);
            room.SetMaterial(13, 4, 0);
            room.SetMaterial(14, 4, 0);
            x = 13 * 28;
        }
        return false;
    }

}

