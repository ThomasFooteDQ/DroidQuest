package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.devices.ContactSensor;
import com.droidquest.devices.DirectionalSensor;
import com.droidquest.devices.RoomSensor;
import com.droidquest.items.Item;

public class ShapeEditor extends Material {
    private Item target;

    public ShapeEditor(Item item) {
        super(true, false);
        target = item;
        GenerateIcons();
    }

    public void GenerateIcons() {
        target.GenerateIcons();
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.drawImage(target.currentIcon, 14 - target.getWidth() / 2, 16 - target.getHeight() / 2, level);
        icon = new ImageIcon(bi);
    }

    public void TouchedByItem(Item item) {
        Item newItem = null;
        if (item.editable) {
            if (item.getClass().toString().endsWith("Sensor")) {
                Item newTarget = (Item) target.clone();
                if (item.getClass().toString().endsWith("ContactSensor")) {
                    newItem = new ContactSensor(0, 0, null, newTarget);
                }
                else if (item.getClass().toString().endsWith("RoomSensor")) {
                    newItem = new RoomSensor(0, 0, null, newTarget);
                }
                else if (item.getClass().toString().endsWith("DirectionalSensor")) {
                    newItem = new DirectionalSensor(0, 0, null, newTarget);
                }
            }
            else {
                newItem = (Item) target.clone();
            }
            Item carrier = item.carriedBy;
            carrier.Drops();
            int itemX = item.x;
            int itemY = item.y;
            level.items.removeElement(item);
            newItem.x = itemX;
            newItem.y = itemY;
            newItem.room = carrier.room;
            level.items.addElement(newItem);
            carrier.PicksUp(newItem);
        }
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (target == ((ShapeEditor) mat).target) {
                return true;
            }
        }
        return false;
    }

}

