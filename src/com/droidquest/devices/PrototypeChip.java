package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PrototypeChip extends GenericChip {
    public transient PortDevice portdevices[];

    public PrototypeChip(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 104;
        height = 64;

        GenerateIcons();

        InternalRoom = new Room();
        InternalRoom.RoomArray = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
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
        InternalRoom.upRoom = null;
        InternalRoom.downRoom = null;
        InternalRoom.leftRoom = null;
        InternalRoom.rightRoom = null;
        leftPortal = new Rectangle(8, 12, 8, 10);
        rightPortal = new Rectangle(64, 12, 8, 10);
        upPortal = new Rectangle(36, -6, 8, 10);
        downPortal = new Rectangle(36, 38, 8, 10);

        portdevices = new PortDevice[8];
        portdevices[0] = new PortDevice(16, 2 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[1] = new PortDevice(16, 4 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[2] = new PortDevice(16, 7 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[3] = new PortDevice(16, 9 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[4] = new PortDevice(500, 9 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[5] = new PortDevice(500, 7 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[6] = new PortDevice(500, 4 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[7] = new PortDevice(500, 2 * 32, InternalRoom, 28, Port.TYPE_UNDEFINED);
        portdevices[0].rotate(1);
        portdevices[1].rotate(1);
        portdevices[2].rotate(1);
        portdevices[3].rotate(1);
        portdevices[4].rotate(-1);
        portdevices[5].rotate(-1);
        portdevices[6].rotate(-1);
        portdevices[7].rotate(-1);

        for (int a = 0; a < 8; a++) {
            level.items.addElement(portdevices[a]);
        }
    }

    public void GenerateIcons() {
//	super.GenerateIcons();
        if (ports == null) {
            ports = new Port[8];
            ports[0] = new Port(12, 9, Port.TYPE_UNDEFINED, 16, Port.ROT_LEFT, this);
            ports[1] = new Port(4, 25, Port.TYPE_UNDEFINED, 24, Port.ROT_LEFT, this);
            ports[2] = new Port(4, 41, Port.TYPE_UNDEFINED, 24, Port.ROT_LEFT, this);
            ports[3] = new Port(12, 57, Port.TYPE_UNDEFINED, 16, Port.ROT_LEFT, this);
            ports[4] = new Port(91, 54, Port.TYPE_UNDEFINED, 16, Port.ROT_RIGHT, this);
            ports[5] = new Port(99, 38, Port.TYPE_UNDEFINED, 24, Port.ROT_RIGHT, this);
            ports[6] = new Port(99, 22, Port.TYPE_UNDEFINED, 24, Port.ROT_RIGHT, this);
            ports[7] = new Port(91, 6, Port.TYPE_UNDEFINED, 16, Port.ROT_RIGHT, this);
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
        g.fillRect(24, 0, 56, 64);
        g.setColor(Color.black);
        g.fillRect(26, 6, 4, 4);
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

    public void Animate() {
        try {
            g = currentIcon.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        for (Port port : ports) {
            port.Draw(g, rotation);
        }
        g.setColor(Color.blue);
        g.fillRect(24, 0, 56, 64);
        g.setColor(Color.black);
        g.fillRect(26, 6, 4, 4);
        if (label != null) {
            g.setColor(Color.black);
            Font font = new Font("Courier", Font.BOLD, 20);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int X = fm.stringWidth(label);
            g.drawString(label, 52 - X / 2, 32);
        }
    }

    public void Decorate() {
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        for (int a = 0; a < 8; a++) {
            s.writeInt(level.items.indexOf(portdevices[a]));
        }
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        portdevices = new PortDevice[8];
        for (int a = 0; a < ports.length; a++) {
            Item item = level.FindItem(s.readInt());
            portdevices[a] = (PortDevice) item;
        }
        GenerateIcons();
    }

    public boolean Function() {
        // Transfer values bewteen the ports and the portdevices.
        boolean changed = false;
        for (int a = 0; a < 8; a++) {
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
                if (level.materials.elementAt(room.RoomArray[a][b]).getClass().toString().endsWith("PrototypeBurner")) {
                    a = bigYb;
                    b = bigXr;
                    x = 3 * 28 + 4;
                    y = 5 * 32 + 12;
                    inBurner = true;
                }
                if (level.materials.elementAt(room.RoomArray[a][b]).getClass().toString().endsWith("ChipTrash")) {
                    level.items.removeElement(this);
                    level.PlaySound(room, Level.DISCHARGESOUND);
                }
                if (level.materials.elementAt(room.RoomArray[a][b]).getClass().toString().endsWith("ChipTester")) {
                    a = bigYb;
                    b = bigXr;
                    x = 10 * 28 - width / 2;
                    y = 5 * 32 - height / 2;
                    inTester = true;
                }
            }
        }
    }

    public void Erase() {
        super.Erase();
        for (int a = 0; a < portdevices.length; a++) {
            portdevices[a] = null;
        }
    }

}