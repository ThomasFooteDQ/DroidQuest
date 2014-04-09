package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class NOTGate extends Device {
    private transient ImageIcon[] images;

    public NOTGate(int X, int Y, Room r) {
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
            System.out.println("Could not get Graphics pointer to ANDGate Image");
        }

    }

    public void Decorate() {
        super.Decorate();
        if (!ports[0].value) {
            g.drawImage(images[4 + rotation].getImage(), 0, 0, level);
        }
        else {
            g.drawImage(images[rotation].getImage(), 0, 0, level);
        }
    }

    public void GenerateIcons() {
        super.GenerateIcons();
        if (ports == null) {
            ports = new Port[2];
            ports[0] = new Port(15, 47, Port.TYPE_INPUT, 12, Port.ROT_DOWN, this);
            ports[1] = new Port(12, 2, Port.TYPE_OUTPUT, 14, Port.ROT_UP, this);
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
                        g.fillRect(8, 16, 4, 4);
                        g.fillRect(16, 16, 4, 4);
                        g.fillRect(12, 20, 4, 4);
                        g.fillRect(8, 22, 4, 4);
                        g.fillRect(16, 22, 4, 4);
                        g.fillRect(4, 26, 4, 4);
                        g.fillRect(20, 26, 4, 4);
                        g.fillRect(0, 30, 4, 4);
                        g.fillRect(24, 30, 4, 4);
                        g.fillRect(0, 32, 32, 2);
                        break;
                    case 1: // Right
                        g.fillRect(30, 8, 4, 4);
                        g.fillRect(30, 16, 4, 4);
                        g.fillRect(26, 12, 4, 4);
                        g.fillRect(24, 8, 4, 4);
                        g.fillRect(24, 16, 4, 4);
                        g.fillRect(20, 4, 4, 4);
                        g.fillRect(20, 20, 4, 4);
                        g.fillRect(16, 0, 4, 4);
                        g.fillRect(16, 24, 4, 4);
                        g.fillRect(16, 0, 2, 32);
                        break;
                    case 2: // Down
                        g.fillRect(8, 30, 4, 4);
                        g.fillRect(16, 30, 4, 4);
                        g.fillRect(12, 26, 4, 4);
                        g.fillRect(8, 24, 4, 4);
                        g.fillRect(16, 24, 4, 4);
                        g.fillRect(4, 20, 4, 4);
                        g.fillRect(20, 20, 4, 4);
                        g.fillRect(0, 16, 4, 4);
                        g.fillRect(24, 16, 4, 4);
                        g.fillRect(0, 16, 32, 2);
                        break;
                    case 3: // Left
                        g.fillRect(16, 8, 4, 4);
                        g.fillRect(16, 16, 4, 4);
                        g.fillRect(20, 12, 4, 4);
                        g.fillRect(22, 8, 4, 4);
                        g.fillRect(22, 16, 4, 4);
                        g.fillRect(26, 4, 4, 4);
                        g.fillRect(26, 20, 4, 4);
                        g.fillRect(30, 0, 4, 4);
                        g.fillRect(30, 24, 4, 4);
                        g.fillRect(32, 0, 2, 32);
                        break;
                }
            }
        }
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        ports[1].value = !ports[0].value;
        return false;
    }

}
