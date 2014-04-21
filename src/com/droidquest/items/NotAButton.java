package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class NotAButton extends Item {
    int animationState = 0;
    private transient EnergyButton eb = null;

    public NotAButton(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 26;
        grabbable = false;
        GenerateIcons();
    }

    public void GenerateIcons() {
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
        g.fillRect(8, 0, 12, 26);
        g.fillRect(4, 2, 20, 22);
        g.fillRect(0, 4, 28, 18);
        g.setColor(Color.black);
        g.fillRect(8, 6, 12, 14);
        g.fillRect(4, 8, 20, 10);
        g.setColor(Color.white);
        g.fillRect(12, 10, 4, 6);
        g.fillRect(8, 10, 12, 6);
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        if (eb == null) {
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item instanceof EnergyButton) {
                    eb = (EnergyButton) item;
                    eb.nb = this;
                }
            }
        }

        if (animationState < 51) {
            animationState++;
            int dx = level.random.nextInt(11) - 5;
            int dy = level.random.nextInt(11) - 5;
            if (x + dx > 28 && x + dx < (19 * 28)) {
                x += dx;
            }
            if (y + dy > 32 && y + dy < (11 * 32)) {
                y += dy;
            }
            if (animationState == 1) {
                eb.x = x;
                eb.y = y;
                eb.room = room;
                room = null;
            }
            if (animationState == 2) {
                room = eb.room;
                eb.room = null;
            }
            if (animationState == 50) {
                animationState = 0;
            }
        }
    }
}


