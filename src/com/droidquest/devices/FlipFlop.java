package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FlipFlop extends Device {
    public boolean state;
    private Color c1;
    private Color c2;
    private transient ImageIcon[] images;

    public FlipFlop(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 48;
        height = 32;
        GenerateIcons();
        currentIcon = icons[rotation % 2].getImage();
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to ANDGate Image");
        }

    }

    public void Decorate() {
        super.Decorate();
        if (!state) {
            g.drawImage(images[4 + rotation].getImage(), 0, 0, level);
        }
        else {
            g.drawImage(images[rotation].getImage(), 0, 0, level);
        }
    }

    public void GenerateIcons() {
        super.GenerateIcons();
        if (ports == null) {
            ports = new Port[4];
            ports[0] = new Port(11, 29, Port.TYPE_INPUT, 6, Port.ROT_DOWN, this);
            ports[1] = new Port(39, 27, Port.TYPE_INPUT, 4, Port.ROT_DOWN, this);
            ports[2] = new Port(8, 2, Port.TYPE_OUTPUT, 8, Port.ROT_UP, this);
            ports[3] = new Port(36, 0, Port.TYPE_OUTPUT, 10, Port.ROT_UP, this);
            if (rotation > 0) {
                int rot = rotation;
                if (rotation % 2 == 1) {
                    int temp = width;
                    width = height;
                    height = temp;
                }
                rotation = 0;
                for (int r = 0; r < rot; r++) {
                    rotate(1);
                }
            }
        }
        goesInToolbox = true;
        images = new ImageIcon[8];
        int w;
        int h;
        if (rotation % 2 == 0) {
            w = width;
            h = height;
        }
        else {
            w = height;
            h = width;
        }
        for (int v = 0; v < 2; v++) {
            for (int r = 0; r < 4; r++) {
                int a = r + v * 4;
                if (r % 2 == 0) {
                    images[a] = new ImageIcon(new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR));
                }
                else {
                    images[a] = new ImageIcon(new BufferedImage(h, w, BufferedImage.TYPE_4BYTE_ABGR));
                }
                try {
                    g = images[a].getImage().getGraphics();
                }
                catch (NullPointerException e) {
                    System.out.println("Could not get Graphics pointer to Device Image");
                    return;
                }
                Graphics2D g2 = (Graphics2D) g;
                g2.setBackground(transparent);
                g2.clearRect(0, 0, width, height);

                if (v == 0) {
                    c1 = new Color(255, 128, 0);
                    c2 = Color.white;
                }
                else {
                    c1 = Color.white;
                    c2 = new Color(255, 128, 0);
                }
                switch (r) {
                    case 0: // Up
                        g.setColor(c1);
                        g.fillRect(0, 10, 24, 12);
                        g.setColor(c2);
                        g.fillRect(24, 10, 24, 12);
                        break;
                    case 1: // Right
                        g.setColor(c1);
                        g.fillRect(10, 0, 12, 24);
                        g.setColor(c2);
                        g.fillRect(10, 24, 12, 24);
                        break;
                    case 2: // Down
                        g.setColor(c2);
                        g.fillRect(0, 10, 24, 12);
                        g.setColor(c1);
                        g.fillRect(24, 10, 24, 12);
                        break;
                    case 3: // Left
                        g.setColor(c2);
                        g.fillRect(10, 0, 12, 24);
                        g.setColor(c1);
                        g.fillRect(10, 24, 12, 24);
                        break;
                }
            }
        }
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        if (ports[0].value ^ ports[1].value) {
            state = ports[0].value;
        }
        ports[2].value = state;
        ports[3].value = !state;
        return false;
    }

    public void flip() {
        Wire wire1 = ports[0].myWire;
        Wire wire2 = ports[1].myWire;
        Wire wire3 = ports[2].myWire;
        Wire wire4 = ports[3].myWire;
        if (wire1 != null) {
            if (wire1.fromPort == ports[0]) {
                wire1.fromPort = ports[1];
            }
            if (wire1.toPort == ports[0]) {
                wire1.toPort = ports[1];
            }
            if (wire1.inPort == ports[0]) {
                wire1.inPort = ports[1];
            }
        }
        if (wire2 != null) {
            if (wire2.fromPort == ports[1]) {
                wire2.fromPort = ports[0];
            }
            if (wire2.toPort == ports[1]) {
                wire2.toPort = ports[0];
            }
            if (wire2.inPort == ports[1]) {
                wire2.inPort = ports[0];
            }
        }
        if (wire3 != null) {
            if (wire3.fromPort == ports[2]) {
                wire3.fromPort = ports[3];
            }
            if (wire3.toPort == ports[2]) {
                wire3.toPort = ports[3];
            }
            if (wire3.outPort == ports[2]) {
                wire3.outPort = ports[3];
            }
        }
        if (wire4 != null) {
            if (wire4.fromPort == ports[3]) {
                wire4.fromPort = ports[2];
            }
            if (wire4.toPort == ports[3]) {
                wire4.toPort = ports[2];
            }
            if (wire4.outPort == ports[3]) {
                wire4.outPort = ports[2];
            }
        }
        ports[0].myWire = wire2;
        ports[1].myWire = wire1;
        ports[2].myWire = wire4;
        ports[3].myWire = wire3;
        state = !state;
    }

}
