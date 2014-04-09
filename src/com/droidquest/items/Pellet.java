package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class Pellet extends Item {
    private transient static int pelletCount = 0;
    private transient boolean counted = false;

    public Pellet(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 28;
        pelletCount++;
    }

    public void GenerateIcons() {
        if (!counted) {
            int index = level.items.indexOf(this);
            if (!(level.items.elementAt(index - 1) instanceof Pellet)) {
                pelletCount = 0;
            }
            counted = true;
            pelletCount++;
        }
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
        g.fillOval(0, 0, width, height);
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item item) {
        if (item instanceof GenericRobot) {
            level.items.removeElement(this);
            pelletCount--;
            if (pelletCount == 0) {
                room.SetMaterial(15, 1, 0);
                room.SetMaterial(15, 2, 0);
                room.SetMaterial(15, 5, 0);
                room.SetMaterial(15, 6, 0);
                room.SetMaterial(15, 9, 0);
                room.SetMaterial(15, 10, 0);
                for (int a = 0; a < level.items.size(); a++) {
                    Item i = level.items.elementAt(a);
                    if (i instanceof Ghost) {
                        level.items.removeElement(i);
                    }
                }
            }
        }
        return false;
    }

}
