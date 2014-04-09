package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Grabber extends Device {
    private Color color;
    private transient GenericRobot robot;

    public Grabber(int X, int Y, Room r, Color col) {
        x = X;
        y = Y;
        color = col;
        width = 40;
        height = 48;
        grabbable = false;
        room = r;
        if (room.portalItem != null) {
            if (room.portalItem.getClass().toString().endsWith("Robot")) {
                robot = (GenericRobot) room.portalItem;
            }
        }
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        s.writeInt(level.items.indexOf(robot)); // Index of fromport device
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        robot = (GenericRobot) level.FindItem(s.readInt());
    }

    public void GenerateIcons() {
        robot = (GenericRobot) room.portalItem;
        if (ports == null) {
            ports = new Port[2];
            ports[0] = new Port(35, 41, Port.TYPE_INPUT, 16, Port.ROT_DOWN, this);
            ports[1] = new Port(11, 47, Port.TYPE_OUTPUT, 26, Port.ROT_DOWN, this);
        }
        else {
            for (Port port : ports) {
                port.myDevice = this;
            }
        }
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        currentIcon = icons[0].getImage();
    }

    public void Decorate() {
        super.Decorate();
        try {
            g = currentIcon.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g.setColor(color);
        g.fillRect(24, 4, 4, 10);
        g.fillRect(16, 4, 12, 2);
        g.fillRect(16, 8, 12, 2);
        g.fillRect(16, 2, 4, 10);
        g.fillRect(12, 0, 4, 4);
        g.fillRect(12, 10, 4, 4);
        g.fillRect(4, 0, 12, 2);
        g.fillRect(4, 12, 12, 2);
        g.fillRect(4, 0, 4, 4);
        g.fillRect(4, 10, 4, 4);
        g.fillRect(0, 2, 4, 4);
        g.fillRect(0, 8, 4, 4);
    }

    public boolean Function() {
        ports[1].value = robot.carrying != null;

        if (!ports[0].value) { // Input Low
            if (robot.carrying != null) {
                robot.Drops();
            }
        }
        else { // Input High
            if (robot.carrying == null) {
                // Try and pick up something
                Item item = robot.level.FindNearestItem(robot);
                if (item != null) {
                    if (item.CanBePickedUp(robot) && item.carriedBy == null) {
                        int CX = item.x + item.getWidth() / 2;
                        int CY = item.y + item.getHeight() / 2;
                        if (CX >= 28 + robot.x && CY < 21 + robot.y) {
                            // Move item to robot.x,robot.y
                            item.x = robot.x + 60 - item.getWidth() / 2;
                            item.y = robot.y - 9 - item.getHeight() / 2;
                            robot.grabber = Port.ROT_UP;
                            robot.PicksUp(item);
                        }

                        if (CX >= 28 + robot.x && CY >= 21 + robot.y) {
                            // Move item to robot.x,robot.y
                            item.x = robot.x + 66 - item.getWidth() / 2;
                            item.y = robot.y + 41 - item.getHeight() / 2;
                            robot.grabber = Port.ROT_RIGHT;
                            robot.PicksUp(item);
                        }

                        if (CX < 28 + robot.x && CY >= 21 + robot.y) {
                            // Move item to robot.x,robot.y
                            item.x = robot.x + 5 - item.getWidth() / 2;
                            item.y = robot.y + 52 - item.getHeight() / 2;
                            robot.grabber = Port.ROT_DOWN;
                            robot.PicksUp(item);
                        }

                        if (CX < 28 + robot.x && CY < 21 + robot.y) {
                            // Move item to robot.x,robot.y
                            item.x = robot.x - 5 - item.getWidth() / 2;
                            item.y = robot.y + 3 - item.getHeight() / 2;
                            robot.grabber = Port.ROT_LEFT;
                            robot.PicksUp(item);
                        }

                    }
                }
            }
        }

        return false;
    }

    public void Erase() {
        super.Erase();
        robot = null;
    }

}
