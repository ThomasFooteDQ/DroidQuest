package com.droidquest.devices;

import com.droidquest.chipstuff.Port;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Device extends Item {
    // Base Class for the Logical Devices

    transient Graphics g;
    transient static Color transparent = new Color(0, 0, 0, 0);
    public Port[] ports;
    public int rotation; // 0=Up, 1=Right, 2=Down, 3=Left
    // Reference to the toolbox means this device can be put inside the ToolBox
    transient boolean goesInToolbox;

    protected Device() {
        // Constructor
        rotation = 0;
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        for (Port port : ports) {
            port.writeRef(s);
        }
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        for (Port port : ports) {
            port.readRef(s, level);
        }
        GenerateIcons();
    }

    public void GenerateIcons() {
        goesInToolbox = false;
        if (ports != null) {
            for (Port port : ports) {
                port.myDevice = this;
            }
        }

        icons = new ImageIcon[2];
        if (rotation % 2 == 0) {
            icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
            icons[1] = new ImageIcon(new BufferedImage(height, width, BufferedImage.TYPE_4BYTE_ABGR));
        }
        else {
            icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
            icons[0] = new ImageIcon(new BufferedImage(height, width, BufferedImage.TYPE_4BYTE_ABGR));
        }
    }

    public boolean Function() {
        // Performs the function of the device, such as calculating the
        // output based upon inputs, or handling external functions such as
        // thrusting, touching walls, grabbing objects, antenna, etc...
        //
        //
        // Return False unless the device is a Node-like device and output
        // has changed.
        //
        return false;
    }

    public void Decorate() {
        currentIcon = icons[rotation % 2].getImage();
        try {
            g = currentIcon.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to Device Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        for (Port port : ports) {
            port.Draw(g, rotation);
        }
    }

    public boolean isDevice() {
        return true;
    }

    public boolean isNode() {
        return false;
    }

    public void rotate(int dir) {
        if (rotation == 0 && dir == -1) {
            rotation = 3;
        }
        else if (rotation == 3 && dir == 1) {
            rotation = 0;
        }
        else {
            rotation += dir;
        }

        int oldW = width;
        int oldH = height;
        int temp = width;
        width = height;
        height = temp;
        for (Port port : ports) {
            int oldX = port.x * 2 + 1;
            int oldY = port.y * 2 + 1;
            temp = port.width;
            port.width = port.height;
            port.height = temp;
            switch (dir) {
                case 1: // Turn Right
                    oldX = oldX - oldW;
                    oldY = oldY - oldH;
                    port.x = (width - oldY) / 2;
                    port.y = (height + oldX) / 2;
                    break;
                case -1: // Turn Left
                    oldX = oldX - oldW;
                    oldY = oldY - oldH;
                    port.x = (width + oldY) / 2;
                    port.y = (height - oldX) / 2;
                    break;
            }
        }
    }

    public void IsDropped() {
        super.IsDropped();
        if (goesInToolbox) {
            if (level.toolbox != null) {
                if (((ToolBox) level.toolbox).open) {
                    if (Overlaps(level.toolbox)) {
                        // Remove all wires and delete device
                        for (Port port : ports) {
                            if (port.myWire != null) {
                                port.myWire.Remove();
                            }
                        }
                        level.items.removeElement(this);
                    }
                }
            }
        }
    }

    public boolean CanBePickedUp(Item item) {
        return !item.getClass().toString().endsWith("Robot") && super.CanBePickedUp(item);
    }

    public void Erase() {
        super.Erase();
        g = null;
        for (Port port : ports) {
            port.myDevice = null;
            port.myWire = null;
        }
    }

    public void flip() {
        // Just rotate twice to flip
        rotate(1);
        rotate(1);
    }

    public Object clone() {
        Device newDevice;
        newDevice = (Device) super.clone();
        newDevice.ports = null;
        newDevice.GenerateIcons();
        return newDevice;
    }

}

