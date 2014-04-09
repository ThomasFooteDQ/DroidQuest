package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class ElevatorLock extends Material {
    private int doorstate = 0;
// 0=closed
// 1=opening
// 2=opening
// 3=open
// 4=closing
// 5=closing
private Room room;

    public ElevatorLock() {
        super(true, false);
        GenerateIcons();
    }

    public void GenerateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }

        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(4, 4, 4, 2);
        g.fillRect(12, 4, 4, 2);
        g.fillRect(4, 8, 4, 2);
        g.fillRect(20, 8, 4, 2);
        g.fillRect(20, 26, 4, 2);
        g.fillRect(4, 14, 12, 4);
        g.fillRect(4, 22, 12, 4);
        g.fillRect(20, 12, 4, 12);
        icon = new ImageIcon(bi);
    }

    public void TouchedByItem(Item item) {
        if (item.getClass().toString().endsWith("ElevatorKey")) {
            room = item.room;
            if (doorstate == 0) {
                doorstate = 1;
            }
            else if (doorstate == 3) {
                doorstate = 4;
            }
        }
    }

    public void Animate() {
        switch (doorstate) {
            case 0: // Do nothing
                break;
            case 1:
                room.SetMaterial(15, 5, 0);
                doorstate++;
                break;
            case 2:
                room.SetMaterial(15, 4, 0);
                doorstate++;
                break;
            case 3:
                break;
            case 4:
                room.SetMaterial(15, 4, 7);
                doorstate++;
                break;
            case 5:
                room.SetMaterial(15, 5, 7);
                doorstate = 0;
                break;
        }
    }

}