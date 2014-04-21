package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.devices.PrototypeChip;
import com.droidquest.levels.Level;

public class Factory extends Item {
    private Item target;

    public Factory(int X, int Y, Room r, Item t) {
        x = X;
        y = Y;
        room = r;
        target = t;
        width = 28;
        height = 26;
        GenerateIcons();
    }

    public void GenerateIcons() {
        target.GenerateIcons();
        target.Decorate();
        width = Math.max(((BufferedImage) target.currentIcon).getWidth() + 8, 18);
        height = Math.max(((BufferedImage) target.currentIcon).getHeight() + 8, 18);
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
        g.drawRect(0, 0, width, height);
        g.drawRect(1, 1, width - 2, height - 2);
        g.drawImage(target.currentIcon,
                width / 2 - target.width / 2 - target.orgX,
                height / 2 - target.height / 2 - target.orgY,
                level);
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item i) {
        Item item;
        if (target instanceof PrototypeChip) {
            item = new PrototypeChip(0, 0, null);
        }
        else {
            item = (Item) target.clone();
        }
        item.GenerateIcons();
        item.x = (560 - item.width) / 2;
        item.y = (384 - item.height) / 2;
        item.room = room;
        level.items.addElement(item);
        level.PlaySound(room, Level.CHARGESOUND);
        return false;
    }

}
