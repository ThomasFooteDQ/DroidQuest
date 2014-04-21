package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Antenna;
import com.droidquest.devices.Bumper;
import com.droidquest.devices.Grabber;
import com.droidquest.devices.Thruster;

public class OrangeRobot extends GenericRobot {
    public OrangeRobot(int X, int Y, Room r) {
        super(X, Y, r, new Color(255, 128, 0));
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
        int cx, cy, cc;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }

        g.setColor(Color.black);
        g.fillRect(22, 36, 42, 20);

        for (int a = 0; a < 20; a++) {
            cx = level.random.nextInt(41) + 22;
            cy = level.random.nextInt(19) + 36;
            cc = level.random.nextInt(7);
            switch (cc) {
                case 0:
                    g.setColor(Color.white);
                    break;
                case 1:
                    g.setColor(Color.red);
                    break;
                case 2:
                    g.setColor(new Color(255, 128, 0));
                    break;
                case 3:
                    g.setColor(Color.yellow);
                    break;
                case 4:
                    g.setColor(Color.green);
                    break;
                case 5:
                    g.setColor(Color.blue);
                    break;
                case 6:
                    g.setColor(Color.magenta);
                    break;
            }
            g.fillRect(cx, cy, 2, 2);
        }
    }

}
