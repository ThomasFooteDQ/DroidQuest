package com.droidquest.chipstuff;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.droidquest.Wire;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class Port implements Serializable {
    // Input or Output of a Device

    public int type; // 0=Input, 1=Output, 2=Undefined
    public static final int TYPE_INPUT = 0;
    public static final int TYPE_OUTPUT = 1;
    public static final int TYPE_UNDEFINED = 2;
    private int size; // Short or Long port 0=Short, 1=Long
    private boolean standard; // Shows Graphics?
    public int rotation; //0=Up, 1=Left, 2=Down, 3=Right;
    public static final int ROT_UP = 0;
    public static final int ROT_RIGHT = 1;
    public static final int ROT_DOWN = 2;
    public static final int ROT_LEFT = 3;

    public boolean value; // True or False, what else?
    public int x, y;
    public int width, height; // width & height
    private int wireX;
    private int wireY;
    public transient Item myDevice;
    public transient Wire myWire;
    private boolean locked; // False = Reverts to Undefined with no connections

    public Port() {
    }

    public Port(int X, int Y, int Type, int Size, int rot, Device d) {
        // There are realy only two types of Ports: Standard and Chip. A
        // Standard Port is what appears on all Gates, Robot Devices, and
        // the Prototype Chip. 20 images are defined for these conditions:
        // (Input/Output/Undefined) * (U/D/L/R) * (On/Off). Undefined is
        // always Off. If the type is not Standard, it's a chip port, and
        // uses no graphics.

        x = X;
        y = Y;
        type = Type;
        size = Size;
        rotation = rot;
        standard = (size > 0);
        myDevice = d;

        if (standard) {
            width = 12;
            height = size;
            wireX = x + 6;
            wireY = y + height - 6;
        }
        else {
            width = 2;
            height = 2;
            wireX = x;
            wireY = y;
        }

        locked = type != TYPE_UNDEFINED;

    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        Level level = myDevice.level;
        s.writeInt(level.items.indexOf(myDevice));
        if (myDevice.room != null) {
            s.writeInt(myDevice.room.wires.indexOf(myWire));
        }
    }

    public void readRef(ObjectInputStream s, Level level) throws IOException {
        myDevice = level.FindItem(s.readInt());
        if (myDevice != null) {
            if (myDevice.room != null) {
                myWire = myDevice.room.FindWire(s.readInt());
            }
        }
    }

    public void Draw(Graphics g, int rot) {
        if (value) {
            g.setColor(new Color(255, 128, 0));
        }
        else {
            g.setColor(Color.white);
        }
        int relRot = (rotation + rot) % 4;
        if (standard) {
            switch (type) {
                case TYPE_INPUT:
                    switch (relRot) {
                        case 0: // Up
                            g.fillRect(x + 0, y + 4, 4, size);
                            g.fillRect(x - 4, y + 0, 4, 4);
                            g.fillRect(x + 4, y + 0, 4, 4);
                            g.fillRect(x + 0, y - 2, 4, 2);
                            break;
                        case 1: // Right
                            g.fillRect(x - size - 3, y + 0, size, 4);
                            g.fillRect(x - 3, y - 4, 4, 4);
                            g.fillRect(x - 3, y + 4, 4, 4);
                            g.fillRect(x + 1, y + 0, 2, 4);
                            break;
                        case 2: // Down
                            g.fillRect(x - 3, y - size - 3, 4, size);
                            g.fillRect(x - 7, y - 3, 4, 4);
                            g.fillRect(x + 1, y - 3, 4, 4);
                            g.fillRect(x - 3, y + 1, 4, 2);
                            break;
                        case 3: // Left
                            g.fillRect(x + 4, y - 3, size, 4);
                            g.fillRect(x + 0, y - 7, 4, 4);
                            g.fillRect(x + 0, y + 1, 4, 4);
                            g.fillRect(x - 2, y - 3, 2, 4);
                            break;
                    }
                    break;
                case TYPE_OUTPUT:
                    switch (relRot) {
                        case 0: // Up
                            g.fillRect(x + 0, y + 0, 4, size);
                            g.fillRect(x - 4, y + 2, 12, 2);
                            g.fillRect(x - 8, y + 4, 4, 2);
                            g.fillRect(x + 8, y + 4, 4, 2);
                            break;
                        case 1: // Right
                            g.fillRect(x - size + 1, y + 0, size, 4);
                            g.fillRect(x - 3, y - 4, 2, 12);
                            g.fillRect(x - 5, y - 8, 2, 4);
                            g.fillRect(x - 5, y + 8, 2, 4);
                            break;
                        case 2: // Down
                            g.fillRect(x - 3, y - size, 4, size);
                            g.fillRect(x - 7, y - 3, 12, 2);
                            g.fillRect(x - 11, y - 5, 4, 2);
                            g.fillRect(x + 5, y - 5, 4, 2);
                            break;
                        case 3: // Left
                            g.fillRect(x, y - 3, size, 4);
                            g.fillRect(x + 2, y - 7, 2, 12);
                            g.fillRect(x + 4, y + 5, 2, 4);
                            g.fillRect(x + 4, y - 11, 2, 4);
                            break;
                    }
                    break;
                case TYPE_UNDEFINED:
                    switch (relRot) {
                        case 0: // Up
                            g.fillRect(x - 2, y - 4, 8, 12);
                            g.fillRect(x, y + 8, 4, size);
                            g.setColor(Color.black);
                            g.fillRect(x, y, 4, 4);
                            break;
                        case 1: // Right
                            g.fillRect(x - 7, y - 2, 12, 8);
                            g.fillRect(x - 7 - size, y, size, 4);
                            g.setColor(Color.black);
                            g.fillRect(x - 3, y, 4, 4);
                            break;
                        case 2: // Down
                            g.fillRect(x - 5, y - 7, 8, 12);
                            g.fillRect(x - 3, y - 7 - size, 4, size);
                            g.setColor(Color.black);
                            g.fillRect(x - 3, y - 3, 4, 4);
                            break;
                        case 3: // Left
                            g.fillRect(x - 4, y - 5, 12, 8);
                            g.fillRect(x + 8, y - 3, size, 4);
                            g.setColor(Color.black);
                            g.fillRect(x, y - 3, 4, 4);
                            break;
                    }
                    break;
            }
        }
    }


}
