package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.items.GenericRobot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Bumper extends Device {
    private int rotation;
    private Color color;
    private transient GenericRobot robot;

    public Bumper(int X, int Y, Room r, int direction, Color col) {
        x = X;
        y = Y;
        room = r;
        if (room.portalItem != null) {
            if (room.portalItem.getClass().toString().endsWith("Robot")) {
                robot = (GenericRobot) room.portalItem;
            }
        }
        rotation = direction;
        color = col;
        grabbable = false;
        GenerateIcons();
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        s.writeInt(level.items.indexOf(robot));
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        robot = (GenericRobot) level.FindItem(s.readInt());
    }

    public void GenerateIcons() {
        robot = (GenericRobot) room.portalItem;
        if (ports == null) {
            ports = new Port[1];
            switch (rotation) {
                case Port.ROT_UP:
                    width = 30;
                    height = 42;
                    ports[0] = new Port(16, 39, Port.TYPE_OUTPUT, 26, Port.ROT_DOWN, this);
                    break;

                case Port.ROT_RIGHT:
                    width = 54;
                    height = 24;
                    ports[0] = new Port(0, 13, Port.TYPE_OUTPUT, 42, Port.ROT_LEFT, this);
                    break;

                case Port.ROT_DOWN:
                    width = 30;
                    height = 38;
                    ports[0] = new Port(13, 2, Port.TYPE_OUTPUT, 22, Port.ROT_UP, this);
                    break;

                case Port.ROT_LEFT:
                    width = 54;
                    height = 24;
                    ports[0] = new Port(52, 10, Port.TYPE_OUTPUT, 40, Port.ROT_RIGHT, this);
                    break;
            }
        }
        else {
            for (Port port : ports) {
                port.myDevice = this;
            }
        }

        icons = new ImageIcon[1];
        switch (rotation) {
            case Port.ROT_UP:
                width = 30;
                height = 42;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;

            case Port.ROT_RIGHT:
                width = 54;
                height = 24;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;

            case Port.ROT_DOWN:
                width = 30;
                height = 38;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;

            case Port.ROT_LEFT:
                width = 54;
                height = 24;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;
        }
        currentIcon = icons[0].getImage();
    }

    public void Decorate() {
        super.Decorate();
        currentIcon = icons[0].getImage();
        try {
            g = currentIcon.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g.setColor(color);
        switch (rotation) {
            case Port.ROT_UP: // Thrusts Up, moves Down
                g.fillRect(8, 0, 14, 4);
                g.fillRect(4, 2, 6, 4);
                g.fillRect(20, 2, 6, 4);
                g.fillRect(0, 4, 6, 4);
                g.fillRect(24, 4, 6, 4);
                g.fillRect(12, 6, 6, 10);
                break;

            case Port.ROT_RIGHT: // Thrusts Right, moves Left
                g.fillRect(44, 6, 10, 12);
                g.fillRect(40, 2, 10, 6);
                g.fillRect(40, 16, 10, 6);
                g.fillRect(36, 0, 10, 4);
                g.fillRect(36, 20, 10, 4);
                g.fillRect(32, 0, 14, 2);
                g.fillRect(32, 22, 14, 2);
                break;

            case Port.ROT_DOWN: // Thrusts Down, moves Up
                g.fillRect(8, 34, 14, 4);
                g.fillRect(4, 32, 6, 4);
                g.fillRect(20, 32, 6, 4);
                g.fillRect(0, 30, 6, 4);
                g.fillRect(24, 30, 6, 4);
                g.fillRect(12, 22, 6, 10);
                break;

            case Port.ROT_LEFT: // Thrusts Left, moves Right
                g.fillRect(0, 6, 10, 12);
                g.fillRect(4, 2, 10, 6);
                g.fillRect(4, 16, 10, 6);
                g.fillRect(8, 0, 10, 4);
                g.fillRect(8, 20, 10, 4);
                g.fillRect(8, 0, 14, 2);
                g.fillRect(8, 22, 14, 2);
                g.fillRect(12, 10, 14, 4);
                break;
        }
    }

    public boolean Function() {
        // Check the walls on the sides of the GenericRobot and set the
        // Port outputs and the bumper variables

        Dimension d = robot.GetXY();
        int X = d.width;
        int Y = d.height;

        switch (rotation) {
            case Port.ROT_UP: // Top Bumper
            {
                int bigXl = (X + 14) / 28;
                int bigXr = (X + 41) / 28;
                int bigY = (Y - 3) / 32;
                boolean collide = false;
                for (int a = bigXl; a <= bigXr; a++) {
                    if (robot.level.materialAt(a, bigY, robot.room).Detectable(robot)) {
                        collide = true;
                    }
                }
                ports[0].value = collide;
                robot.topBumper = collide;
            }
            break;
            case Port.ROT_RIGHT: // Right Bumper
            {
                int bigX = (X + 60) / 28;
                int bigYt = (Y + 5) / 32;
                int bigYb = (Y + 36) / 32;
                boolean collide = false;
                for (int a = bigYt; a <= bigYb; a++) {
                    if (robot.level.materialAt(bigX, a, robot.room).Detectable(robot)) {
                        collide = true;
                    }
                }
                ports[0].value = collide;
                robot.rightBumper = collide;
            }
            break;
            case Port.ROT_DOWN: // Bottom Bumper
            {
                int bigXl = (X + 14) / 28;
                int bigXr = (X + 41) / 28;
                int bigY = (Y + 44) / 32;
                boolean collide = false;
                for (int a = bigXl; a <= bigXr; a++) {
                    if (robot.level.materialAt(a, bigY, robot.room).Detectable(robot)) {
                        collide = true;
                    }
                }
                ports[0].value = collide;
                robot.bottomBumper = collide;
            }
            break;
            case Port.ROT_LEFT: // Left Bumper
            {
                int bigX = (X - 3) / 28;
                int bigYt = (Y + 5) / 32;
                int bigYb = (Y + 36) / 32;
                boolean collide = false;
                for (int a = bigYt; a <= bigYb; a++) {
                    if (robot.level.materialAt(bigX, a, robot.room).Detectable(robot)) {
                        collide = true;
                    }
                }
                ports[0].value = collide;
                robot.leftBumper = collide;
            }
            break;
        }

        return false;
    }

    public void Erase() {
        super.Erase();
        robot = null;
    }

}
