package com.droidquest.devices;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;

public class ORGate extends Device {
    private transient ImageIcon[] images;

    public ORGate(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 50;
        GenerateIcons();
        currentIcon = icons[rotation % 2].getImage();
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to ORGate Image");
        }

    }

    public void Decorate() {
        super.Decorate();
        if (ports[0].value || ports[1].value) {
            g.drawImage(images[4 + rotation].getImage(), 0, 0, level);
        }
        else {
            g.drawImage(images[rotation].getImage(), 0, 0, level);
        }
    }

    public void GenerateIcons() {
        super.GenerateIcons();
        if (ports == null) {
            ports = new Port[3];
            ports[0] = new Port(7, 47, Port.TYPE_INPUT, 12, Port.ROT_DOWN, this);
            ports[1] = new Port(23, 43, Port.TYPE_INPUT, 8, Port.ROT_DOWN, this);
            ports[2] = new Port(12, 2, Port.TYPE_OUTPUT, 16, Port.ROT_UP, this);
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
                    g.setColor(Color.white);
                }
                else {
                    g.setColor(new Color(255, 128, 0));
                }
                switch (r) {
                    case 0: // Up
                        g.fillRect(8, 16, 12, 2);
                        g.fillRect(8, 16, 4, 4);
                        g.fillRect(16, 16, 4, 4);
                        g.fillRect(4, 18, 4, 6);
                        g.fillRect(20, 18, 4, 6);
                        g.fillRect(0, 22, 4, 12);
                        g.fillRect(24, 22, 4, 12);
                        g.fillRect(0, 32, 8, 2);
                        g.fillRect(8, 30, 4, 2);
                        g.fillRect(12, 28, 4, 2);
                        g.fillRect(16, 30, 4, 2);
                        g.fillRect(20, 32, 8, 2);

                        break;
                    case 1: // Right
                        g.fillRect(16, 0, 12, 4);
                        g.fillRect(16, 24, 12, 4);
                        g.fillRect(26, 4, 6, 4);
                        g.fillRect(26, 20, 6, 4);
                        g.fillRect(30, 8, 4, 4);
                        g.fillRect(30, 16, 4, 4);
                        g.fillRect(32, 12, 2, 4);
                        g.fillRect(16, 0, 2, 8);
                        g.fillRect(16, 20, 2, 8);
                        g.fillRect(18, 8, 2, 4);
                        g.fillRect(20, 12, 2, 4);
                        g.fillRect(18, 16, 2, 4);
                        break;
                    case 2: // Down
                        g.fillRect(0, 16, 4, 12);
                        g.fillRect(24, 16, 4, 12);
                        g.fillRect(4, 26, 4, 6);
                        g.fillRect(20, 26, 4, 6);
                        g.fillRect(8, 30, 4, 4);
                        g.fillRect(16, 30, 4, 4);
                        g.fillRect(12, 32, 4, 2);
                        g.fillRect(0, 16, 8, 2);
                        g.fillRect(20, 16, 8, 2);
                        g.fillRect(8, 18, 4, 2);
                        g.fillRect(12, 20, 4, 2);
                        g.fillRect(16, 18, 4, 2);
                        break;
                    case 3: // Left
                        g.fillRect(22, 0, 12, 4);
                        g.fillRect(22, 24, 12, 4);
                        g.fillRect(18, 4, 6, 4);
                        g.fillRect(18, 20, 6, 4);
                        g.fillRect(16, 8, 4, 4);
                        g.fillRect(16, 16, 4, 4);
                        g.fillRect(16, 12, 2, 4);
                        g.fillRect(32, 0, 2, 8);
                        g.fillRect(32, 20, 2, 8);
                        g.fillRect(30, 8, 2, 4);
                        g.fillRect(28, 12, 2, 4);
                        g.fillRect(30, 16, 2, 4);
                        break;
                }
            }
        }
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        ports[2].value = ports[0].value | ports[1].value;
        return false;
    }

    public void flip() {
        Wire wire1 = ports[0].myWire;
        Wire wire2 = ports[1].myWire;
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
        ports[0].myWire = wire2;
        ports[1].myWire = wire1;
    }

}
