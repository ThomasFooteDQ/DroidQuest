package com.droidquest.items;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.levels.Level;

public class Ghost extends Item {
    private int animationState = 0; // 0=Right, 1=Down,  2=Left, 3=Up
// x values are 42, 154, 266, 378
// y values are 58, 186, 314

    private transient boolean searched = false;
    private transient Item[] robot;

    public Ghost(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 32;
        grabbable = false;
        GenerateIcons();
    }

    public void GenerateIcons() {
        robot = new Item[4];
        icons = new ImageIcon[4];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[3] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        for (int a = 0; a < 4; a++) {
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
            g2.clearRect(0, 0, width, height);
            g.setColor(Color.red);
            g.fillArc(0, 0, 28, 32, 0, 180);
            g.fillRect(0, 16, 28, 16);
            g.setColor(Color.white);
            g.fillOval(4, 8, 8, 8);
            g.fillOval(16, 8, 8, 8);
            g.setColor(Color.black);
            switch (a) {
                case 0: // Right
                    g.fillOval(9, 10, 4, 4);
                    g.fillOval(21, 10, 4, 4);
                    break;
                case 1: // Down
                    g.fillOval(7, 12, 4, 4);
                    g.fillOval(19, 12, 4, 4);
                    break;
                case 2: // Left
                    g.fillOval(4, 10, 4, 4);
                    g.fillOval(16, 10, 4, 4);
                    break;
                case 3: // Up
                    g.fillOval(7, 8, 4, 4);
                    g.fillOval(19, 8, 4, 4);
                    break;
            }
        }
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        // Positions: There are 4x3=12 "nodes" where the ghost travels to
        // and from. Every time the ghost reaches a node position, it looks
        // around for any robots in the area. If it sees a robot it then
        // heads in that direction. Robots that are hit are put in the
        // penalty box and drained of energy. If no robot is found it moves
        // in a random direction.

        if (!searched) {
            int rcounter = 0;
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item instanceof GenericRobot) {
                    robot[rcounter] = item;
                    rcounter++;
                }
            }
            searched = true;
        }

        boolean flag = false;
        if (((x == 42) || (x == 154) || (x == 266) || (x == 378))
                && ((y == 48) || (y == 176) || (y == 304))) {
            flag = true;
        }

        if (flag) {
            boolean decision = false;
            for (Item aRobot : robot) {
                if (aRobot != null) {
                    if (aRobot.room == room) {
                        Dimension d = aRobot.GetXY();
                        if (d.width < 14 * 28) {
                            int dx = (d.width + aRobot.width / 2) - (x + width / 2);
                            int dy = (d.height + aRobot.height / 2) - (y + height / 2);
                            if ((Math.abs(dx) < 56) || (Math.abs(dy) < 64)) {
                                decision = true;
                                if (Math.abs(dx) < 56) {
                                    if (dy > 0) {
                                        animationState = 1;
                                    }
                                    else {
                                        animationState = 3;
                                    }
                                }
                                else {
                                    if (dx > 0) {
                                        animationState = 0;
                                    }
                                    else {
                                        animationState = 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!decision) {
                boolean good;
                int backwards = (animationState + 2) % 4;
                do {
                    good = true;
                    animationState = level.random.nextInt(4);
                    if (animationState == 0 && x == 378) {
                        good = false;
                    }
                    if (animationState == 1 && y == 304) {
                        good = false;
                    }
                    if (animationState == 2 && x == 42) {
                        good = false;
                    }
                    if (animationState == 3 && y == 48) {
                        good = false;
                    }
                    if (animationState == backwards) {
                        good = false;
                    }
                }
                while (!good);
            }
        }

        switch (animationState) {
            case 0: // Right
                moveRight(4);
                break;
            case 1: // Down
                moveDown(4);
                break;
            case 2: // Left
                moveLeft(4);
                break;
            case 3: // Up
                moveUp(4);
                break;
        }
        currentIcon = icons[animationState].getImage();

        for (int a = 0; a < 4; a++) {
            if (robot[a] != null) {
                if (Overlaps(robot[a])) {
                    robot[a].charge = 0;
                    level.PlaySound(room, Level.DISCHARGESOUND);
                    robot[a].x = 16 * 28 + 14;
                    robot[a].y = 32;
                }
            }
        }
    }
}
