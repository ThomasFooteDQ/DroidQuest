package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.items.Item;
import com.droidquest.materials.ChipTrash;
import com.droidquest.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Prototype16Chip extends GenericChip {
    private transient PortDevice[] portdevices;

    public Prototype16Chip(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 40;
        height = 40;

        GenerateIcons();

        InternalRoom = new Room();
        InternalRoom.RoomArray = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
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
        Material mat1 = new Material(Color.blue, false, true);
        level.materials.addElement(mat1);
        int mat1Index = level.materials.size() - 1;
        for (int rY = 0; rY < 12; rY++) {
            for (int rX = 0; rX < 20; rX++) {
                if (InternalRoom.RoomArray[rY][rX] == 1) {
                    InternalRoom.RoomArray[rY][rX] = mat1Index;
                }
            }
        }
        InternalRoom.GenerateArray();
        InternalRoom.portalItem = this;
        level.rooms.addElement(InternalRoom);

        portdevices = new PortDevice[16];
        portdevices[0] = new PortDevice(16, 2 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[1] = new PortDevice(16, 4 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[2] = new PortDevice(16, 7 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[3] = new PortDevice(16, 9 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[4] = new PortDevice(4 * 28, 320, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[5] = new PortDevice(7 * 28, 320, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[6] = new PortDevice(12 * 28, 320, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[7] = new PortDevice(15 * 28, 320, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[8] = new PortDevice(500, 9 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[9] = new PortDevice(500, 7 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[10] = new PortDevice(500, 4 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[11] = new PortDevice(500, 2 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[12] = new PortDevice(15 * 28, 12, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[13] = new PortDevice(12 * 28, 12, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[14] = new PortDevice(7 * 28, 12, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[15] = new PortDevice(4 * 28, 12, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[0].rotate(1);
        portdevices[1].rotate(1);
        portdevices[2].rotate(1);
        portdevices[3].rotate(1);
        portdevices[8].rotate(-1);
        portdevices[9].rotate(-1);
        portdevices[10].rotate(-1);
        portdevices[11].rotate(-1);
        portdevices[12].rotate(1);
        portdevices[12].rotate(1);
        portdevices[13].rotate(1);
        portdevices[13].rotate(1);
        portdevices[14].rotate(1);
        portdevices[14].rotate(1);
        portdevices[15].rotate(1);
        portdevices[15].rotate(1);

        for (int a = 0; a < 16; a++) {
            level.items.addElement(portdevices[a]);
        }
    }

    public void GenerateIcons() {
        if (ports == null) {
            ports = new Port[16];
            ports[0] = new Port(1, 8, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[1] = new Port(1, 16, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[2] = new Port(1, 24, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[3] = new Port(1, 32, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[4] = new Port(8, 38, Port.TYPE_UNDEFINED, 0, Port.ROT_DOWN, this);
            ports[5] = new Port(16, 38, Port.TYPE_UNDEFINED, 0, Port.ROT_DOWN, this);
            ports[6] = new Port(24, 38, Port.TYPE_UNDEFINED, 0, Port.ROT_DOWN, this);
            ports[7] = new Port(32, 38, Port.TYPE_UNDEFINED, 0, Port.ROT_DOWN, this);
            ports[8] = new Port(38, 31, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[9] = new Port(38, 23, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[10] = new Port(38, 15, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[11] = new Port(38, 7, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[12] = new Port(31, 1, Port.TYPE_UNDEFINED, 0, Port.ROT_UP, this);
            ports[13] = new Port(23, 1, Port.TYPE_UNDEFINED, 0, Port.ROT_UP, this);
            ports[14] = new Port(15, 1, Port.TYPE_UNDEFINED, 0, Port.ROT_UP, this);
            ports[15] = new Port(7, 1, Port.TYPE_UNDEFINED, 0, Port.ROT_UP, this);
        }
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.blue);
        g.fillRect(3, 3, 34, 34);
        g.fillRect(1, 7, 2, 2);
        g.fillRect(1, 15, 2, 2);
        g.fillRect(1, 23, 2, 2);
        g.fillRect(1, 31, 2, 2);
        g.fillRect(7, 37, 2, 2);
        g.fillRect(15, 37, 2, 2);
        g.fillRect(23, 37, 2, 2);
        g.fillRect(31, 37, 2, 2);
        g.fillRect(37, 31, 2, 2);
        g.fillRect(37, 23, 2, 2);
        g.fillRect(37, 15, 2, 2);
        g.fillRect(37, 7, 2, 2);
        g.fillRect(31, 1, 2, 2);
        g.fillRect(23, 1, 2, 2);
        g.fillRect(15, 1, 2, 2);
        g.fillRect(7, 1, 2, 2);
        g.setColor(Color.black);
        if (label != null) {
            g.setColor(Color.black);
            Font font = new Font("Courier", Font.BOLD, 20);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int X = fm.stringWidth(label);
            g.drawString(label, 52 - X / 2, 32);
        }
        currentIcon = icons[0].getImage();
    }

    public void Decorate() {
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        for (int a = 0; a < 16; a++) {
            s.writeInt(level.items.indexOf(portdevices[a]));
        }
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        portdevices = new PortDevice[16];
        for (int a = 0; a < ports.length; a++) {
            Item item = level.FindItem(s.readInt());
            portdevices[a] = (PortDevice) item;
        }
        GenerateIcons();
    }

    public void IsDropped() {
        inBurner = false;
        inTester = false;
        int bigXl = (x) / 28;
        int bigXr = (x + width - 1) / 28;
        int bigYt = (y) / 32;
        int bigYb = (y + height - 1) / 32;

        if (bigXr > 19) {
            bigXr = 19;
        }
        if (bigYb > 11) {
            bigYb = 11;
        }

        for (int a = bigYt; a <= bigYb; a++) {
            for (int b = bigXl; b <= bigXr; b++) {
                if (level.materials.elementAt(room.RoomArray[a][b]) instanceof ChipTrash) {
                    SetRoom(null);
                    level.items.removeElement(this);
                    level.PlaySound(room, level.DISCHARGESOUND);
                    return;
                }
            }
        }
    }

    public boolean Function() {
        // Transfer values between the ports and the portdevices.
        boolean changed = false;
        for (int a = 0; a < 16; a++) {
            Port outer = ports[a];
            Port inner = portdevices[a].ports[0];

            if (outer.type == Port.TYPE_INPUT) {
                if (outer.value != inner.value) {
                    changed = true;
                    inner.value = outer.value;
                }
            }
            if (outer.type == Port.TYPE_OUTPUT) {
                if (outer.value != inner.value) {
                    changed = true;
                    outer.value = inner.value;
                }
            }


            if (outer.myWire == null && inner.myWire == null) {
                outer.type = Port.TYPE_UNDEFINED;
                inner.type = Port.TYPE_UNDEFINED;
            }

            if (outer.type == Port.TYPE_UNDEFINED) {
                if (inner.myWire != null) {
                    if (inner.type == Port.TYPE_INPUT) {
                        outer.type = Port.TYPE_OUTPUT;
                    }
                    else if (inner.type == Port.TYPE_OUTPUT) {
                        outer.type = Port.TYPE_INPUT;
                    }
                }
            }
            if (inner.type == Port.TYPE_UNDEFINED) {
                if (outer.myWire != null) {
                    if (outer.type == Port.TYPE_INPUT) {
                        inner.type = Port.TYPE_OUTPUT;
                    }
                    else if (outer.type == Port.TYPE_OUTPUT) {
                        inner.type = Port.TYPE_INPUT;
                    }
                }
            }

        }
        return changed;
    }

    public void Erase() {
        super.Erase();
        for (int a = 0; a < portdevices.length; a++) {
            portdevices[a] = null;
        }
    }

}
