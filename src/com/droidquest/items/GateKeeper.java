package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.devices.StormShield;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GateKeeper extends Item {
    int behavior = 0;
// 0= pause
// 1= Go to trash, delete F-12, delete StormShield

    private int goToX = 2 * 28 + 14;
    private int goToY = 8 * 32;

    public GateKeeper(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 52;
        height = 38;
        grabbable = false;
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
        g.fillRect(4, 0, 12, 14);
        g.fillRect(0, 2, 20, 10);
        g.fillRect(20, 4, 8, 6);
        g.fillRect(24, 8, 24, 20);
        g.fillRect(48, 12, 4, 16);
        g.fillRect(32, 6, 12, 32);
        g.fillRect(28, 34, 20, 2);
        g.fillRect(24, 36, 28, 2);
        g.setColor(Color.black);
        g.fillRect(8, 2, 4, 10);
        g.fillRect(4, 4, 12, 6);
        g.fillRect(0, 6, 4, 2);
        g.fillRect(20, 6, 4, 2);
        g.fillRect(28, 18, 4, 2);
        g.fillRect(32, 16, 4, 2);
        g.fillRect(36, 18, 4, 2);
        g.fillRect(40, 16, 4, 2);
        g.fillRect(44, 18, 4, 2);
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        if (behavior == 1) {
            if (x != goToX || y != goToY) {
                if (x != goToX) {
                    int diff = Math.abs(goToX - x);
                    int dir = diff / (goToX - x);
                    if (diff > 2) {
                        diff = 2;
                    }
                    moveRight(diff * dir);
                }
                if (y != goToY) {
                    int diff = Math.abs(goToY - y);
                    int dir = diff / (goToY - y);
                    if (diff > 2) {
                        diff = 2;
                    }
                    moveDown(diff * dir);
                }
            }
            else {
                behavior = 0;
                level.items.removeElement(carrying);
                carrying = null;
                StormShield ss = null;
                for (int a = 0; a < level.items.size(); a++) {
                    Item item = level.items.elementAt(a);
                    if (item.getClass().toString().endsWith("StormShield")) {
                        ss = (StormShield) item;
                    }
                }
                if (ss != null) {
                    ss.SetRoom(null); // Removes wires
                    level.items.removeElement(ss);
                }
                room.SetMaterial(7, 7, 21);
            }
        }
    }
}
