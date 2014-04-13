package com.droidquest.items;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class VendingHandle extends Item {
    // Handle used to pull sliding wall on Vending Machine

    private int startX;
    private int startY;
    boolean paid = false;

    public VendingHandle(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        startX = X;
        startY = Y;
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
        return item != level.player;
    }

    public void IsDropped() {
        x = startX;
        y = startY;
        room.SetMaterial(1, 4, 14);
        room.SetMaterial(2, 4, 14);
        room.SetMaterial(3, 4, 14);
        room.SetMaterial(4, 4, 14);
        room.SetMaterial(5, 4, 14);
        room.SetMaterial(6, 4, 14);
        room.SetMaterial(7, 4, 14);
        room.SetMaterial(8, 4, 14);
        room.SetMaterial(9, 4, 14);
        room.SetMaterial(10, 4, 7);
        room.SetMaterial(11, 4, 0);
        room.SetMaterial(12, 4, 0);
        room.SetMaterial(13, 4, 0);
        room.SetMaterial(14, 4, 0);
        paid = false;
    }

    public void Animate() {
        if (carriedBy != null) {
            int maxPull;
            if (paid) {
                maxPull = 4 * 28;
            }
            else {
                maxPull = 0;
            }
            Dimension d = GetXY();
            int tempX = d.width;
            int tempY = d.height;
            if (tempY != startY) {
                carriedBy.moveDown(startY - tempY);
            }
            if (tempX < startX) {
                carriedBy.moveRight(startX - tempX);
            }
            if (tempX > (startX + maxPull)) {
                carriedBy.moveLeft(tempX - (startX + maxPull));
            }

            d = GetXY();
            tempX = d.width;
            int blocks = (tempX - startX) / 28;
            switch (blocks) {
                case 0:
                    room.SetMaterial(1, 4, 14);
                    room.SetMaterial(2, 4, 14);
                    room.SetMaterial(3, 4, 14);
                    room.SetMaterial(4, 4, 14);
                    room.SetMaterial(5, 4, 14);
                    room.SetMaterial(6, 4, 14);
                    room.SetMaterial(7, 4, 14);
                    room.SetMaterial(8, 4, 14);
                    room.SetMaterial(9, 4, 14);
                    room.SetMaterial(10, 4, 7);
                    room.SetMaterial(11, 4, 0);
                    room.SetMaterial(12, 4, 0);
                    room.SetMaterial(13, 4, 0);
                    room.SetMaterial(14, 4, 0);
                    break;
                case 1:
                    room.SetMaterial(1, 4, 0);
                    room.SetMaterial(2, 4, 14);
                    room.SetMaterial(3, 4, 14);
                    room.SetMaterial(4, 4, 14);
                    room.SetMaterial(5, 4, 14);
                    room.SetMaterial(6, 4, 14);
                    room.SetMaterial(7, 4, 14);
                    room.SetMaterial(8, 4, 14);
                    room.SetMaterial(9, 4, 14);
                    room.SetMaterial(10, 4, 14);
                    room.SetMaterial(11, 4, 7);
                    room.SetMaterial(12, 4, 0);
                    room.SetMaterial(13, 4, 0);
                    room.SetMaterial(14, 4, 0);
                    break;
                case 2:
                    room.SetMaterial(1, 4, 0);
                    room.SetMaterial(2, 4, 0);
                    room.SetMaterial(3, 4, 14);
                    room.SetMaterial(4, 4, 14);
                    room.SetMaterial(5, 4, 14);
                    room.SetMaterial(6, 4, 14);
                    room.SetMaterial(7, 4, 14);
                    room.SetMaterial(8, 4, 14);
                    room.SetMaterial(9, 4, 14);
                    room.SetMaterial(10, 4, 14);
                    room.SetMaterial(11, 4, 14);
                    room.SetMaterial(12, 4, 7);
                    room.SetMaterial(13, 4, 0);
                    room.SetMaterial(14, 4, 0);
                    break;
                case 3:
                    room.SetMaterial(1, 4, 0);
                    room.SetMaterial(2, 4, 0);
                    room.SetMaterial(3, 4, 0);
                    room.SetMaterial(4, 4, 14);
                    room.SetMaterial(5, 4, 14);
                    room.SetMaterial(6, 4, 14);
                    room.SetMaterial(7, 4, 14);
                    room.SetMaterial(8, 4, 14);
                    room.SetMaterial(9, 4, 14);
                    room.SetMaterial(10, 4, 14);
                    room.SetMaterial(11, 4, 14);
                    room.SetMaterial(12, 4, 14);
                    room.SetMaterial(13, 4, 7);
                    room.SetMaterial(14, 4, 0);
                    break;
                case 4:
                    room.SetMaterial(1, 4, 0);
                    room.SetMaterial(2, 4, 0);
                    room.SetMaterial(3, 4, 0);
                    room.SetMaterial(4, 4, 0);
                    room.SetMaterial(5, 4, 14);
                    room.SetMaterial(6, 4, 14);
                    room.SetMaterial(7, 4, 14);
                    room.SetMaterial(8, 4, 14);
                    room.SetMaterial(9, 4, 14);
                    room.SetMaterial(10, 4, 14);
                    room.SetMaterial(11, 4, 14);
                    room.SetMaterial(12, 4, 14);
                    room.SetMaterial(13, 4, 14);
                    room.SetMaterial(14, 4, 7);
                    break;
            }

        }
    }

}
