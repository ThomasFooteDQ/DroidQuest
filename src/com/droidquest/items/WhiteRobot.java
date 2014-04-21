package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Antenna;
import com.droidquest.devices.Bumper;
import com.droidquest.devices.Grabber;
import com.droidquest.devices.Thruster;

import java.awt.*;

public class WhiteRobot extends GenericRobot {
    private int checker;

    public WhiteRobot(int X, int Y, Room r) {
        super(X, Y, r, Color.white);
        checker = 0;
        Animate();
        devices[0] = new Thruster(176, 16, InternalRoom, Port.ROT_UP, Color.blue);
        devices[1] = new Thruster(476, 128, InternalRoom, Port.ROT_RIGHT, Color.blue);
        devices[2] = new Thruster(356, 336, InternalRoom, Port.ROT_DOWN, Color.blue);
        devices[3] = new Thruster(32, 236, InternalRoom, Port.ROT_LEFT, Color.blue);
        devices[4] = new Bumper(396, 16, InternalRoom, Port.ROT_UP, Color.blue);
        devices[5] = new Bumper(480, 256, InternalRoom, Port.ROT_RIGHT, Color.blue);
        devices[6] = new Bumper(128, 330, InternalRoom, Port.ROT_DOWN, Color.blue);
        devices[7] = new Bumper(28, 134, InternalRoom, Port.ROT_LEFT, Color.blue);
        devices[8] = new Antenna(64, 70, InternalRoom, Color.blue);
        devices[9] = new Grabber(126, 44, InternalRoom, Color.blue);
        for (int a = 0; a < 10; a++) {
            level.items.addElement(devices[a]);
        }

    }

    public void Decorate() {
        super.Decorate();
        checker = 1 - checker;
        Graphics g;
        int cx, cy;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }

        g.setColor(Color.black);
        for (cy = 0; cy < 5; cy++) {
            for (cx = 0; cx < 3; cx++) {
                if ((cx + cy + checker) % 2 == 1) {
                    g.fillRect(cx * 8 + 31, cy * 4 + 36, 8, 4);
                }
            }
        }

        // Generate Random positions for Orange & Blue Block
        cx = level.random.nextInt(3);
        cy = level.random.nextInt(5);
        g.setColor(new Color(255, 128, 0));
        g.fillRect(cx * 8 + 31, cy * 4 + 36, 8, 4);
        cx = level.random.nextInt(3);
        cy = level.random.nextInt(5);
        g.setColor(Color.blue);
        g.fillRect(cx * 8 + 31, cy * 4 + 36, 8, 4);

    }

}
