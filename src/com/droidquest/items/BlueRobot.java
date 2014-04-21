package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Antenna;
import com.droidquest.devices.Bumper;
import com.droidquest.devices.Grabber;
import com.droidquest.devices.Thruster;

import java.awt.*;

public class BlueRobot extends GenericRobot {
    private int scan;
    private int scandir;

    public BlueRobot(int X, int Y, Room r) {
        super(X, Y, r, Color.blue);
        scan = 0;
        scandir = 2;
        Animate();
        devices[0] = new Thruster(176, 16, InternalRoom, Port.ROT_UP, Color.white);
        devices[1] = new Thruster(476, 128, InternalRoom, Port.ROT_RIGHT, Color.white);
        devices[2] = new Thruster(356, 336, InternalRoom, Port.ROT_DOWN, Color.white);
        devices[3] = new Thruster(32, 236, InternalRoom, Port.ROT_LEFT, Color.white);
        devices[4] = new Bumper(396, 16, InternalRoom, Port.ROT_UP, Color.white);
        devices[5] = new Bumper(480, 256, InternalRoom, Port.ROT_RIGHT, Color.white);
        devices[6] = new Bumper(128, 330, InternalRoom, Port.ROT_DOWN, Color.white);
        devices[7] = new Bumper(28, 134, InternalRoom, Port.ROT_LEFT, Color.white);
        devices[8] = new Antenna(64, 70, InternalRoom, Color.white);
        devices[9] = new Grabber(126, 44, InternalRoom, Color.white);
        for (int a = 0; a < 10; a++) {
            level.items.addElement(devices[a]);
        }

    }

    public void Decorate() {
        super.Decorate();
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }
        g.setColor(Color.black);
        g.fillRect(22, 44, 42, 4);

        if (scan + scandir > 36 || scan + scandir < 0) {
            scandir = -scandir;
        }
        scan = scan + scandir;
        g.setColor(new Color(255, 128, 0));
        g.fillRect(22 + scan, 44, 6, 4);
    }

}
