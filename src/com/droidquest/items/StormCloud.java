package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.devices.StormShield;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StormCloud extends Item {
    private transient int animateState = 0;
    private transient int xDirection;
    private transient int yDirection;
    private transient int moveTimer;
    private transient OrangeRobot orobot;
    private transient WhiteRobot wrobot;
    private transient BlueRobot brobot;
    private transient StormShield stormshield;
    private static int maxspeed = 30;
    private transient int anicount = 0;

    public StormCloud(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 32;
        grabbable = false;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[3];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        for (int a = 0; a < 3; a++) {
            try {
                g = icons[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color transparent = new Color(0, 0, 0, 0);
            g2.setBackground(transparent);
            g2.clearRect(0, 0, 4 * 28, 3 * 32);

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
        }
        currentIcon = icons[0].getImage();
        do {
            xDirection = level.random.nextInt(maxspeed * 2 + 1) - maxspeed;
        }
        while (xDirection == 0);
        do {
            yDirection = level.random.nextInt(maxspeed * 2 + 1) - maxspeed;
        }
        while (yDirection == 0);
        moveTimer = level.random.nextInt(50) + 1;
    }

    public void Animate() {
        animateState++;
        if (animateState == 3) {
            animateState = 0;
        }
        currentIcon = icons[animateState].getImage();

        if (anicount < 3) {
            Graphics g;
            anicount++;
            try {
                icons[animateState] = new ImageIcon(new BufferedImage(4 * 28, 3 * 32, BufferedImage.TYPE_4BYTE_ABGR));
                g = icons[animateState].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color transparent = new Color(0, 0, 0, 0);
            g2.setBackground(transparent);
            g2.clearRect(0, 0, 4 * 28, 3 * 32);
            for (int b = 0; b < 50; b++) {
                switch (level.random.nextInt(7)) {
                    case 0:
                        g2.setColor(Color.white);
                        break;
                    case 1:
                        g2.setColor(Color.red);
                        break;
                    case 2:
                        g2.setColor(Color.orange);
                        break;
                    case 3:
                        g2.setColor(Color.yellow);
                        break;
                    case 4:
                        g2.setColor(Color.green);
                        break;
                    case 5:
                        g2.setColor(Color.blue);
                        break;
                    case 6:
                        g2.setColor(Color.magenta);
                        break;
                }
                int x1, y1, d;
                int d2 = (3 * 32 / 2) * (3 * 32 / 2);
                do {
                    x1 = level.random.nextInt(3 * 32) + 4 * 28 / 2 - 3 * 32 / 2;
                    y1 = level.random.nextInt(3 * 32);
                    int xd = (x1 - 4 * 28 / 2);
                    int yd = (y1 - 3 * 32 / 2);
                    d = xd * xd + yd * yd;
                }
                while (d > d2);
                g2.fillRect(x1, y1, 2, 2);
            }
        }

        moveTimer--;
        if (moveTimer == 0) {
            do {
                xDirection = level.random.nextInt(maxspeed * 2 + 1) - maxspeed;
            }
            while (xDirection == 0);
            do {
                yDirection = level.random.nextInt(maxspeed * 2 + 1) - maxspeed;
            }
            while (yDirection == 0);
            moveTimer = level.random.nextInt(50) + 1;
        }
        if (brobot == null) {
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item.getClass().toString().endsWith("BlueRobot")) {
                    brobot = (BlueRobot) item;
                }
                if (item.getClass().toString().endsWith("OrangeRobot")) {
                    orobot = (OrangeRobot) item;
                }
                if (item.getClass().toString().endsWith("WhiteRobot")) {
                    wrobot = (WhiteRobot) item;
                }
                if (item.getClass().toString().endsWith("StormShield")) {
                    stormshield = (StormShield) item;
                }
            }
        }

        if (xDirection > 0) {
            moveRight(xDirection);
        }
        if (xDirection < 0) {
            moveLeft(-xDirection);
        }
        if (yDirection > 0) {
            moveDown(yDirection);
        }
        if (yDirection < 0) {
            moveUp(-yDirection);
        }

        if (brobot != null) {
            if (Overlaps(brobot)) {
                boolean drain = true;
                if (stormshield.room == brobot.InternalRoom) {
                    if (stormshield.ports[0].value) {
                        drain = false;
                    }
                }
                if (drain) {
                    brobot.charge -= 5000;
                }
                if (brobot.charge < 0) {
                    brobot.charge = 0;
                }
            }
        }

        if (orobot != null) {
            if (Overlaps(orobot)) {
                boolean drain = true;
                if (stormshield.room == orobot.InternalRoom) {
                    if (stormshield.ports[0].value) {
                        drain = false;
                    }
                }
                if (drain) {
                    orobot.charge -= 5000;
                }
                if (orobot.charge < 0) {
                    orobot.charge = 0;
                }
            }
        }

        if (wrobot != null) {
            if (Overlaps(wrobot)) {
                boolean drain = true;
                if (stormshield.room == wrobot.InternalRoom) {
                    if (stormshield.ports[0].value) {
                        drain = false;
                    }
                }
                if (drain) {
                    wrobot.charge -= 5000;
                }
                if (wrobot.charge < 0) {
                    wrobot.charge = 0;
                }
            }
        }

    }

    public void moveRight(int dist) {
        int newX = x + dist;
        if (newX > 559 - 4 * 28 / 2) {
            xDirection = -(level.random.nextInt(maxspeed) + 1);
            newX = x + xDirection;
        }
        x = newX;
    }

    public void moveLeft(int dist) {
        int newX = x - dist;
        if (newX < 0) {
            xDirection = level.random.nextInt(maxspeed) + 1;
            newX = x + xDirection;
        }
        x = newX;
    }

    public void moveUp(int dist) {
        y -= dist;
        if (y < 0) {
            room = room.upRoom;
            y += 384;
        }
    }

    public void moveDown(int dist) {
        y += dist;
        if (y > 383) {
            room = room.downRoom;
            y -= 384;
        }
    }

} 
