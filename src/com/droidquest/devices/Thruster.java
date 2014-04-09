package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Spark;
import com.droidquest.items.GenericRobot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Thruster extends Device {
    private int rotation;
    private Color color;
    private transient GenericRobot robot;

    public Thruster(int X, int Y, Room r, int direction, Color col) {
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
                case Port.ROT_UP: // Thrusts Up, moves Down
                    ports[0] = new Port(16, 35, Port.TYPE_INPUT, 20, Port.ROT_DOWN, this);
                    break;

                case Port.ROT_RIGHT: // Thrusts Right, moves Left
                    ports[0] = new Port(4, 11, Port.TYPE_INPUT, 28, Port.ROT_LEFT, this);
                    break;

                case Port.ROT_DOWN: // Thrusts Down, moves Up
                    ports[0] = new Port(13, 2, Port.TYPE_INPUT, 12, Port.ROT_UP, this);
                    break;

                case Port.ROT_LEFT: // Thrusts Left, moves Right
                    ports[0] = new Port(47, 8, Port.TYPE_INPUT, 26, Port.ROT_RIGHT, this);
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
            case Port.ROT_UP: // Thrusts Up, moves Down
                width = 30;
                height = 38;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;

            case Port.ROT_RIGHT: // Thrusts Right, moves Left
                width = 54;
                height = 20;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;

            case Port.ROT_DOWN: // Thrusts Down, moves Up
                width = 30;
                height = 32;
                icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
                break;

            case Port.ROT_LEFT: // Thrusts Left, moves Right
                width = 52;
                height = 20;
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
                g.fillRect(0, 0, 30, 6);
                g.fillRect(4, 6, 22, 4);
                g.fillRect(8, 10, 14, 4);
                g.fillRect(12, 14, 6, 2);
                break;

            case Port.ROT_RIGHT: // Thrusts Right, moves Left
                g.fillRect(44, 0, 10, 20);
                g.fillRect(40, 2, 4, 16);
                g.fillRect(36, 4, 4, 12);
                g.fillRect(32, 6, 4, 8);
                break;

            case Port.ROT_DOWN: // Thrusts Down, moves Up
                g.fillRect(0, 26, 30, 6);
                g.fillRect(4, 22, 22, 4);
                g.fillRect(8, 18, 14, 4);
                g.fillRect(12, 16, 6, 2);
                break;

            case Port.ROT_LEFT: // Thrusts Left, moves Right
                g.fillRect(0, 0, 10, 20);
                g.fillRect(10, 2, 4, 16);
                g.fillRect(14, 4, 4, 12);
                g.fillRect(18, 6, 4, 8);
                break;
        }
    }

    public boolean Function() {
        boolean thrust = ports[0].value;

        if (robot == null && thrust) {
            Dimension d = GetXY();
            switch (rotation) {
                case Port.ROT_UP:
                    level.sparks.addElement(new Spark(d.width + level.random.nextInt(30),
                            d.height + 0,
                            0, -4, room));
                    level.sparks.addElement(new Spark(d.width + level.random.nextInt(30),
                            d.height + 0,
                            0, -4, room));
                    break;
                case Port.ROT_RIGHT:
                    level.sparks.addElement(new Spark(d.width + 54,
                            d.height + level.random.nextInt(20),
                            4, 0, room));
                    level.sparks.addElement(new Spark(d.width + 54,
                            d.height + level.random.nextInt(20),
                            4, 0, room));
                    break;
                case Port.ROT_DOWN:
                    level.sparks.addElement(new Spark(d.width + level.random.nextInt(30),
                            d.height + 26,
                            0, 4, room));
                    level.sparks.addElement(new Spark(d.width + level.random.nextInt(30),
                            d.height + 26,
                            0, 4, room));
                    break;
                case Port.ROT_LEFT:
                    level.sparks.addElement(new Spark(d.width + 44,
                            d.height + level.random.nextInt(20),
                            -4, 0, room));
                    level.sparks.addElement(new Spark(d.width + 44,
                            d.height + level.random.nextInt(20),
                            -4, 0, room));
                    break;
            }
        }

        if (robot == null) {
            return false;
        }

        switch (rotation) {
            case Port.ROT_UP: // Thrusts Up, moves Down
                robot.topThruster = thrust;
                break;
            case Port.ROT_RIGHT: // Thrusts Right, moves Left
                robot.rightThruster = thrust;
                break;
            case Port.ROT_DOWN: // Thrusts Down, moves Up
                robot.bottomThruster = thrust;
                break;
            case Port.ROT_LEFT: // Thrusts Left, Moves Right
                robot.leftThruster = thrust;
                break;
        }
        return false;
    }

    public void Erase() {
        super.Erase();
        robot = null;
    }

}
