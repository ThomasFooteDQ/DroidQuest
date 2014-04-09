package com.droidquest.materials;

import com.droidquest.Room;
import com.droidquest.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

public class MultiButton extends Material {
    private int number; //0=starter, 1,2,3,4,5,6,7,8,9 = buttons
    private transient ImageIcon[] images;
    private static int[] states = new int[10]; //0=Blue, 1=White, 2=Orange
    private Date timeout;
    private transient Room room = null;

    public MultiButton(int n, int s) {
        super(true, false);
        number = n;
        states[number] = s;
    }

    public void GenerateIcons() {
        images = new ImageIcon[3];
        for (int a = 0; a < 3; a++) {
            images[a] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
            Graphics g;
            try {
                g = images[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color c = Color.blue;
            if (a == 1) {
                c = Color.white;
            }
            else if (a == 2) {
                c = new Color(255, 128, 0);
            }
            g2.setColor(c);
            g.fillRect(0, 0, 28, 32);
            g2.setColor(Color.black);
            g.fillRect(4, 14, 20, 4);
            g.fillRect(8, 12, 12, 8);
            g.fillRect(12, 8, 4, 16);
            g2.setColor(c);
            g.fillRect(12, 14, 4, 4);
        }

        icon = images[0];
    }

    public void TouchedByItem(Item item) {
        if (room == null) {
            room = item.room;
        }

        if (number == 0) {
            if (states[0] == 1) {
                states[0] = 2;
                for (int a = 1; a < 10; a++) {
                    states[a] = 1;
                }
                timeout = new Date(new Date().getTime() + 30000);
            }
        }
        else {
            boolean okay = true;
            if (states[0] < 2) {
                okay = false;
            }
            for (int a = 1; a < number; a++) {
                if (states[a] != 2) {
                    okay = false;
                }
            }
            if (okay) {
                states[number] = 2;
                timeout = new Date(new Date().getTime() + 5000);
            }
        }
    }

    public void Animate() {
        icon = images[states[number]];

        if (number != 0) {
            return;
        }

        if (states[0] == 0) {
            states[0] = 1;
            for (int a = 1; a < 10; a++) {
                states[a] = 0;
            }
        }

        boolean flag = true;
        for (int a = 0; a < 10; a++) {
            if (states[a] != 2) {
                flag = false;
            }
        }

        if (flag) {
            Portal ptl = new Portal("ROEndGame.lvl", true, true);
            level.materials.addElement(ptl);
            room.SetMaterial(10, 9, ptl);
        }

        if (states[0] == 2) {
            Date now = new Date();
            if (now.getTime() > timeout.getTime()) {
                states[0] = 1;
                for (int a = 1; a < 10; a++) {
                    states[a] = 0;
                }
            }
        }

    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (number == ((MultiButton) mat).number) {
                return true;
            }
        }
        return false;
    }
}
