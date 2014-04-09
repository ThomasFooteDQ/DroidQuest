package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.levels.Level;

public class Polarizer extends Item {
    private transient boolean searched = false;
    private transient boolean found = false;

    public Polarizer(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 24;
        height = 32;
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
        g.setColor(Color.yellow);
        g.fillRect(10, 0, 4, 6);
        g.fillRect(9, 1, 6, 4);
        g.fillRect(11, 6, 2, 22);
        g.fillRect(10, 8, 4, 18);
        g.fillRect(8, 10, 8, 2);
        g.fillRect(6, 14, 12, 2);
        g.fillRect(4, 18, 16, 2);
        g.fillRect(2, 22, 20, 2);
        g.fillRect(0, 28, 24, 4);
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        if (!searched) {
            for(Item item : level.items) {
                searched = true;
                if(item != null && item instanceof StormCloud) {
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            if (room.upRoom == room) {
                for(Item item : level.items) {
                    if (item != null && item instanceof StormCloud) {
                        if (Overlaps(item)) {
                            level.PlaySound(room, Level.DISCHARGESOUND);
                            level.LinkRoomsUpDown(36, 4);
                            room.SetMaterial(8, 0, 0);
                            room.SetMaterial(9, 0, 0);
                            room.SetMaterial(10, 0, 0);
                            room.SetMaterial(11, 0, 0);
                            item.room = null;
                            level.items.removeElement(item);
                            if (carriedBy != null) {
                                carriedBy.Drops();
                            }
                            room = null;
                            level.items.removeElement(this);
                        }
                    }
                }
            }
        }
    }

}
