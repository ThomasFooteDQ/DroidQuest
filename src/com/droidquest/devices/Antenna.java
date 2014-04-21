package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Spark;
import com.droidquest.items.GenericRobot;
import com.droidquest.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Antenna extends Device {
    public static int radio = 0; // One frequency that all Antennas use (robots)
    private static int radio2 = 0; // Second frequency transmitted outside of robots.
    private boolean oldRadio = false; // Was this radio transmitting last phase?
    private Color color;
    private transient GenericRobot robot;

    public Antenna(int X, int Y, Room r, Color col) {
        x = X;
        y = Y;
        color = col;
        width = 44;
        height = 52;
        room = r;
        if (room.portalItem != null) {
            if (room.portalItem.getClass().toString().endsWith("Robot")) {
                robot = (GenericRobot) room.portalItem;
            }
        }
        grabbable = false;
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
        radio = 0;
        radio2 = 0;
        if (ports == null) {
            ports = new Port[2];
            ports[0] = new Port(39, 45, Port.TYPE_INPUT, 18, Port.ROT_DOWN, this);
            ports[1] = new Port(11, 50, Port.TYPE_OUTPUT, 26, Port.ROT_DOWN, this);
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

    public void Animate() {
        super.Animate();
        if (robot == null) {
            if (ports[0].value && level.electricity) {
                Dimension d = GetXY();
                level.sparks.addElement(new Spark(d.width + 26, d.height + 4,
                        level.random.nextInt(9) - 4,
                        level.random.nextInt(9) - 4,
                        room));
            }
        }
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
        if (robot != null) {
            g.setColor(color);
            g.fillRect(18, 0, 14, 6);
            g.fillRect(22, 6, 6, 16);
            g.fillRect(8, 22, 32, 2);
        }
        else {
            if (radio2 > 0) {
                g.setColor(new Color(255, 128, 0));
            }
            else {
                g.setColor(color);
            }
            g.fillRect(18, 0, 14, 6);
            g.fillRect(22, 6, 6, 16);
            g.fillRect(8, 22, 32, 2);
            if (ports[0].value && level.electricity) {
                level.PlaySound(room, Level.BEEPSOUND);
            }
        }
    }

    public boolean Function() {
        if (robot != null) {
            if (radio < 0) {
                radio = 0;
            }

            if (radio > 0) {
                robot.antenna = true;
                ports[1].value = true;
            }
            else {
                robot.antenna = false;
                ports[1].value = false;
            }
            if (oldRadio != ports[0].value) {
                if (ports[0].value) {
                    robot.broadcasting = true;
                    radio++;
                }
                else {
                    robot.broadcasting = false;
                    radio--;
                }
            }
            oldRadio = ports[0].value;
        }
        else {
            if (radio2 < 0) {
                radio2 = 0;
            }

            ports[1].value = radio2 > 0;
            if (oldRadio != ports[0].value) {
                if (ports[0].value) {
                    radio2++;
                }
                else {
                    radio2--;
                }
            }
            oldRadio = ports[0].value;
        }
        return false;
    }

    public void SetRoom(Room r) {
        if (oldRadio) {
            if (robot != null) {
                radio--;
            }
            else {
                radio2--;
            }
        }
        super.SetRoom(r);
        robot = null;
        if (room.portalItem != null) {
            if (room.portalItem.getClass().toString().endsWith("Robot")) {
                robot = (GenericRobot) room.portalItem;
            }
        }
    }

    public void Erase() {
        super.Erase();
        robot = null;
    }

}