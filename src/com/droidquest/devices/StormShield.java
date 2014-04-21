package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StormShield extends Device {
    public StormShield(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 52;
        GenerateIcons();
    }

    public void GenerateIcons() {
        super.GenerateIcons();
        if (ports == null) {
            ports = new Port[1];
            ports[0] = new Port(15, 49, Port.TYPE_INPUT, 22, Port.ROT_DOWN, this);
        }
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        Color transparent = new Color(0, 0, 0, 0);
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(16, 0, 4, 2);
        g.fillRect(4, 2, 4, 2);
        g.fillRect(12, 4, 4, 2);
        g.fillRect(20, 4, 4, 2);
        g.fillRect(4, 8, 4, 2);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(24, 8, 4, 2);
        g.fillRect(0, 10, 4, 2);
        g.fillRect(8, 10, 4, 2);
        g.fillRect(16, 10, 4, 2);
        g.fillRect(24, 12, 4, 2);
        g.fillRect(8, 14, 4, 2);
        g.fillRect(16, 14, 4, 2);
        g.fillRect(0, 18, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(24, 18, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(4, 22, 4, 2);
        g.fillRect(20, 22, 4, 2);
        g.fillRect(8, 24, 4, 2);
        g.fillRect(16, 24, 4, 2);
        currentIcon = icons[0].getImage();
    }

    public void Decorate() {
        try {
            g = currentIcon.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to Device Image");
            return;
        }

        for (Port port : ports) {
            port.Draw(g, rotation);
        }

    }

    public void rotate(int dir) {
        // Does not Rotate!
    }

}