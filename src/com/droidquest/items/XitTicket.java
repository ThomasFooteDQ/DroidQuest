package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.materials.Material;
import com.droidquest.materials.XitSlot;

public class XitTicket extends Item {
    public XitTicket(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 30;
        editable = true;
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
        g.fillRect(8, 0, 4, 6);
        g.fillRect(0, 2, 8, 2);
        g.fillRect(0, 8, 12, 2);
        g.fillRect(0, 12, 12, 2);
        g.fillRect(4, 14, 4, 2);
        g.fillRect(0, 16, 4, 2);
        g.fillRect(8, 16, 4, 2);
        g.fillRect(0, 20, 12, 2);
        g.fillRect(8, 24, 4, 6);
        g.fillRect(0, 26, 8, 2);
        g.fillRect(16, 4, 4, 2);
        g.fillRect(24, 4, 4, 2);
        g.fillRect(20, 6, 4, 2);
        g.fillRect(16, 8, 4, 2);
        g.fillRect(24, 8, 4, 2);
        g.fillRect(16, 16, 12, 2);
        g.fillRect(24, 20, 4, 6);
        g.fillRect(16, 22, 8, 2);
        currentIcon = icons[0].getImage();
    }

    public void IsDropped() {
        int bigX = (x + width / 2) / 28;
        int bigY = (y + height / 2) / 32;
        Material mat = room.MaterialArray[bigY][bigX];
        if (mat.getClass().toString().endsWith("XitSlot")) {
            XitSlot xitslot = (XitSlot) mat;
            xitslot.room = room;
            xitslot.doorState = 1;
            room.SetMaterial(18, 1, 18);
            room = null;
        }
    }

}

