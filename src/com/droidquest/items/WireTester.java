package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.PortDevice;

public class WireTester extends Item {
    private transient PortDevice myPortDevice;

    public WireTester(int X, int Y, Room r, PortDevice pd) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 26;
        myPortDevice = pd;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        for (int a = 0; a < 2; a++) {
            Color color;
            if (a == 0) {
                color = Color.white;
            }
            else {
                color = new Color(255, 128, 0);
            }
            Graphics g;
            try {
                g = icons[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color transparent = new Color(0, 0, 0, 0);
            g2.setBackground(transparent);
            g2.clearRect(0, 0, width, height);
            g.setColor(color);
            g.fillRect(8, 0, 12, 26);
            g.fillRect(4, 2, 20, 22);
            g.fillRect(0, 4, 28, 18);
            g.setColor(Color.black);
            g.fillRect(8, 6, 12, 14);
            g.fillRect(4, 8, 20, 10);
            g.setColor(color);
            g.fillRect(12, 8, 4, 10);
            g.fillRect(8, 10, 12, 6);
        }
        currentIcon = icons[0].getImage();
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        s.writeInt(level.items.indexOf(myPortDevice));
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        myPortDevice = (PortDevice) level.FindItem(s.readInt());
    }

    public boolean CanBePickedUp(Item i) {
        if (myPortDevice.ports[0].type == Port.TYPE_OUTPUT) {
            myPortDevice.ports[0].value = !myPortDevice.ports[0].value;
        }
        return false;
    }

    public void Decorate() {
        if (myPortDevice != null) {
            if (myPortDevice.ports[0].value) {
                currentIcon = icons[1].getImage();
            }
            else {
                currentIcon = icons[0].getImage();
            }
        }
        else {
            currentIcon = icons[0].getImage();
        }
    }

    public void Erase() {
        super.Erase();
        myPortDevice = null;
    }

}

