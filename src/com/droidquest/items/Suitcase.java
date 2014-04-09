package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Suitcase extends Item {
    public Suitcase(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 24;
        height = 18;
        width = 36;
        height = 42;
        orgX = -6;
        orgY = -12;

        InternalRoom = new Room();
        InternalRoom.RoomArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        InternalRoom.portalItem = this;
        level.rooms.addElement(InternalRoom);
        Material mat1 = Material.FindSimiliar(new Material(Color.blue, false, true));
        int mat1Index = level.materials.indexOf(mat1);

        for (int rY = 0; rY < 12; rY++) {
            for (int rX = 0; rX < 20; rX++) {
                if (InternalRoom.RoomArray[rY][rX] == 1) {
                    InternalRoom.RoomArray[rY][rX] = mat1Index;
                }
            }
        }

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
        g.fillRect(0, 4, 24, 14);
        g.fillRect(7, 0, 10, 2);
        g.fillRect(7, 0, 2, 4);
        g.fillRect(15, 0, 2, 4);
        currentIcon = icons[0].getImage();
    }

}