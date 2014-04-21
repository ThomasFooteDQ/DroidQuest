package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Disk extends Item {
    private transient Room helpRoom = null;
    private Color color;
    private transient Room helpCamRoom = null;

    public Disk(int X, int Y, Room r, Color col, int hr) {
        x = X;
        y = Y;
        room = r;
        color = col;
        width = 28;
        height = 24;
        grabbable = true;
        helpRoom = level.rooms.elementAt(hr);
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
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);
        g.fillRect(24, 4, 4, 2);
        g.fillRect(12, 8, 4, 8);
        g.fillRect(8, 10, 12, 4);
        g.fillRect(20, 14, 4, 2);
        g.fillRect(12, 18, 4, 4);
        currentIcon = icons[0].getImage();
    }


    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        s.writeInt(level.rooms.indexOf(helpRoom));
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        helpRoom = level.FindRoom(s.readInt());
    }

    public void IsDropped() {

        int bigX = (x + width / 2) / 28;
        int bigY = (y + height / 2) / 32;
        Material mat = room.MaterialArray[bigY][bigX];
        if (mat.getClass().toString().endsWith("Monitor")) {
            helpCamRoom = level.helpCam.room; // Temporary storage
            level.helpCam.room = helpRoom;
            level.currentViewer = level.helpCam;
            level.player = level.helpCam;
        }
    }

    public boolean CanBePickedUp(Item item) {
        if (level.helpCam.room == room) {
            level.helpCam.room = helpCamRoom;
        }
        level.currentViewer = level.player;
        return true;
    }

}


