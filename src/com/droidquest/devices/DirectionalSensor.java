package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DirectionalSensor extends Device {
    private String targetClass;
    private Item target;

    public DirectionalSensor(int X, int Y, Room r, Item item) {
        x = X;
        y = Y;
        room = r;
        target = item;
        editable = true;
        targetClass = target.getClass().toString().substring(6); // Removes "class "
        width = 64 + target.getWidth();
        height = 64 + target.getHeight();
        ports = new Port[4];
        ports[0] = new Port(width / 2 - 4, 0, Port.TYPE_OUTPUT, 24, Port.ROT_UP, this);
        ports[1] = new Port(width - 5, height / 2 - 4, Port.TYPE_OUTPUT, 24, Port.ROT_RIGHT, this);
        ports[2] = new Port(width / 2 - 1, height - 4, Port.TYPE_OUTPUT, 24, Port.ROT_DOWN, this);
        ports[3] = new Port(0, height / 2 - 1, Port.TYPE_OUTPUT, 24, Port.ROT_LEFT, this);
        GenerateIcons();
    }

    public void GenerateIcons() {
        if (ports == null) {
            ports = new Port[4];
            ports[0] = new Port(width / 2 - 4, 0, Port.TYPE_OUTPUT, 24, Port.ROT_UP, this);
            ports[1] = new Port(width - 5, height / 2 - 4, Port.TYPE_OUTPUT, 24, Port.ROT_RIGHT, this);
            ports[2] = new Port(width / 2 - 1, height - 4, Port.TYPE_OUTPUT, 24, Port.ROT_DOWN, this);
            ports[3] = new Port(0, height / 2 - 1, Port.TYPE_OUTPUT, 24, Port.ROT_LEFT, this);
        }
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        target.GenerateIcons();
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        ports[0].value = false;
        ports[1].value = false;
        ports[2].value = false;
        ports[3].value = false;
        if (room.portalItem == null) {
            // Directional Sensor is not inside robot.
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item.room == room && item.carriedBy == null) {
                    if (target.getClass().isInstance(item)) {
                        Dimension d = GetXY();
                        int X = d.width;
                        int Y = d.height;
                        if (item.y < Y) {
                            ports[0].value = true;
                            a = level.items.size();
                        }
                        if (item.x + item.getWidth() > X + width) {
                            ports[1].value = true;
                            a = level.items.size();
                        }
                        if (item.y + item.getHeight() > Y + height) {
                            ports[2].value = true;
                            a = level.items.size();
                        }
                        if (item.x < X) {
                            ports[3].value = true;
                            a = level.items.size();
                        }
                    }
                }
            }
        }
        else {
            // Directional Sensor is inside Robot.
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item.room == room.portalItem.room && item.carriedBy == null) {
                    if (target.getClass().isInstance(item)) {
                        Dimension d = room.portalItem.GetXY();
                        int X = d.width;
                        int Y = d.height;
                        if (item.y < Y) {
                            ports[0].value = true;
                            a = level.items.size();
                        }
                        if (item.x + item.getWidth() > X + room.portalItem.getWidth()) {
                            ports[1].value = true;
                            a = level.items.size();
                        }
                        if (item.y + item.getHeight() > Y + room.portalItem.getHeight()) {
                            ports[2].value = true;
                            a = level.items.size();
                        }
                        if (item.x < X) {
                            ports[3].value = true;
                            a = level.items.size();
                        }
                    }
                }
            }
        }
        return false;
    }

    public void Decorate() {
        super.Decorate();
        g.setColor(Color.white);
        g.drawRect(24, 24, target.getWidth() + 12, target.getHeight() + 12);
        g.drawRect(25, 25, target.getWidth() + 10, target.getHeight() + 10);
        g.drawImage(target.currentIcon, 30, 30, level);
    }

    public void rotate(int dir) {
        // Does not Rotate!
    }

    public void Erase() {
        super.Erase();
        target = null;
    }

}

