package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;

public class PortDevice extends Device {
    // This is an invisible device which has a port sticking out of it. It's
    // used inside the Prototype chip as well as in various spots inside the Tutorials.

    private int type;
    private int size;

    public boolean value = false; // Used for constants in Tutorials

    public PortDevice(int X, int Y, Room r, int s, int t) {
        x = X;
        y = Y;
        room = r;
        type = t;
        size = s;
        width = 20;
        height = size + 20;
        grabbable = false;
        GenerateIcons();
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
        }
    }

    public void GenerateIcons() {
        super.GenerateIcons();
        if (ports == null) {
            ports = new Port[1];
            ports[0] = new Port(8, 8, type, size, Port.ROT_UP, this);
        }
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        if (value && ports[0].type == Port.TYPE_OUTPUT) {
            ports[0].value = true;
        }
        return false;
    }

}
