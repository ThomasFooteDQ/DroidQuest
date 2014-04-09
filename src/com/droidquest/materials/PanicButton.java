package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.Item;
import com.droidquest.items.Sentry;

public class PanicButton extends Material {
    private boolean state = true;
    private transient ImageIcon[] images;
    private transient Sentry sentry;
    private int number;

// image[0] = blue, off, Sentry alive
// image[1] = orange, on, Sentry dead

    public PanicButton(int n) {
        super(true, false);
        number = n;
        GenerateIcons();
    }

    public void GenerateIcons() {
        images = new ImageIcon[2];
        for (int a = 0; a < 2; a++) {
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
            g2.setBackground(Color.black);
            g2.clearRect(0, 0, 28, 32);
            Color color;
            if (a == 0) {
                color = Color.blue;
            }
            else {
                color = new Color(255, 128, 0);
            }

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
        icon = images[0];
    }

    public void TouchedByItem(Item item) {
        if (item == level.player && level.player.carriedBy == null) {
            if (sentry == null) {
                for (int a = 0; a < level.items.size(); a++) {
                    Item i = level.items.elementAt(a);
                    if (i instanceof Sentry && i.room == item.room) {
                        sentry = (Sentry) i;
                    }
                }
            }
            if (sentry != null) {
                if (state) { // turn off
                    sentry.previousBehavior = sentry.behavior;
                    sentry.behavior = -2;
                    icon = images[1];
                    state = false;
                }
                else { // turn on
                    sentry.behavior = sentry.previousBehavior;
                    icon = images[0];
                    state = true;
                }
            }

        }
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (number == ((PanicButton) mat).number) {
                return true;
            }
        }
        return false;
    }

}
