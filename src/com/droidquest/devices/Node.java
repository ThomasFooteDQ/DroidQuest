package com.droidquest.devices;

import com.droidquest.devices.Device;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;

public class Node extends Device {
    public static final int TYPE_STRAIGHT = 0;
    public static final int TYPE_RIGHT = 1;
    public static final int TYPE_THREE = 2;
    private transient ImageIcon[] images;
    private int type;

    public Node(int X, int Y, Room r, int t) {
        x = X;
        y = Y;
        room = r;
        width = 36;
        height = 32;
        type = t;

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
        if (ports[0].value) {
            g.drawImage(images[4 + rotation].getImage(), 0, 0, level);
        }
        else {
            g.drawImage(images[rotation].getImage(), 0, 0, level);
        }
    }

    public void GenerateIcons() {
        super.GenerateIcons();
        if (ports == null) {
            switch (type) {
                case TYPE_STRAIGHT:
                    ports = new Port[3];
                    ports[0] = new Port(16, 14, Port.TYPE_INPUT, 0, Port.ROT_UP, this);
                    ports[1] = new Port(16, 0, Port.TYPE_OUTPUT, 12, Port.ROT_UP, this);
                    ports[2] = new Port(19, 31, Port.TYPE_OUTPUT, 12, Port.ROT_DOWN, this);
                    break;
                case TYPE_RIGHT:
                    ports = new Port[3];
                    ports[0] = new Port(16, 14, Port.TYPE_INPUT, 0, Port.ROT_UP, this);
                    ports[1] = new Port(16, 0, Port.TYPE_OUTPUT, 12, Port.ROT_UP, this);
                    ports[2] = new Port(35, 14, Port.TYPE_OUTPUT, 12, Port.ROT_RIGHT, this);
                    break;
                case TYPE_THREE:
                    ports = new Port[4];
                    ports[0] = new Port(16, 14, Port.TYPE_INPUT, 0, Port.ROT_UP, this);
                    ports[1] = new Port(16, 0, Port.TYPE_OUTPUT, 12, Port.ROT_UP, this);
                    ports[2] = new Port(35, 14, Port.TYPE_OUTPUT, 12, Port.ROT_RIGHT, this);
                    ports[3] = new Port(19, 31, Port.TYPE_OUTPUT, 12, Port.ROT_DOWN, this);
                    break;
            }
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
                    case 2: // Down
                        g.fillRect(12, 12, 12, 2);
                        g.fillRect(12, 18, 12, 2);
                        g.fillRect(12, 12, 4, 8);
                        g.fillRect(20, 12, 4, 8);
                        break;
                    case 1: // Right
                    case 3: // Left
                        g.fillRect(12, 12, 8, 4);
                        g.fillRect(12, 20, 8, 4);
                        g.fillRect(12, 12, 2, 12);
                        g.fillRect(18, 12, 2, 12);
                        break;
                }
            }
        }
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        boolean oldValue = ports[1].value;
        for (int a = 1; a < ports.length; a++) {
            ports[a].value = ports[0].value;
        }
        return (ports[0].value != oldValue);
    }

    public boolean isNode() {
        return true;
    }

}
