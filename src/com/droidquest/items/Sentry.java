package com.droidquest.items;

import com.droidquest.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sentry extends Item {
    // Base class for all Sentries.
    //
    // Either individual Sentries will have different Animate functions, or
    // they will be instances of this class and behave in a manner according
    // to two arrays of numbers.
    //
    // int pace[] = {x1,y1} , {x2,y2}, ...
    //
    // int protect[] = {x1,y1, x2,y2, x3,y3}, ...
    //
    //
    // The pace array is a list of XY pairs that define the path that the
    // Sentry will follow. By default the Sentry paces by going to the first
    // point, then the second, then third, and so on until the end of the
    // list is reached at which point it goes back to the beginning. Most
    // Sentries will probably have two points it paces back and forth
    // between.
    //
    // The protect array consists of sets of 6 numbers, each set is three
    // pairs of XY coordinates. The first two pairs define an area which
    // triggers the Sentry to pounce. The third pair defines where the
    // sentry will drag the player. This array can have any number of
    // sextets of numbers. (The total size of the array must be divisible by
    // six). Each block of six numbers defines an area and target the sentry
    // guards.
    //
    // The smartblock variable tells the Sentry to pin a robot down if the
    // player is inside it.
    //

    int animation = 0; // 0-3, 1 & 3 = icons[1]
    public int behavior = 0;  // 0+ for pacing, until pounce and drag. -1=pin.
    public int previousBehavior;
    private int[] pace;     // List of pacing coordinates
    public int[] protect;  // List of areas and targets
    private int goToX;      // Current position pacing towards
    private int goToY;      // ""
    private int carryToX;   // Currently dragging towards
    private int carryToY;   // ""
    private int pounce;     // Pouncing behaviour number (pace.length/2)
    private int drag;       // Dragging behavior number (pounce+1)
    private boolean smartblock; // Pins robots if they carry the player.
    private GenericRobot robot; // Robot to pin

    Sentry(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 18;
        orgY = 30;
        grabbable = false;
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public Sentry(int X, int Y, Room r, int[] p1, int[] p2, boolean sb) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 18;
        orgY = 30;
        pace = p1;
        protect = p2;
        smartblock = sb;
        pounce = pace.length / 2;
        drag = pounce + 1;
        grabbable = false;
        behavior = 0;
        goToX = pace[0];
        goToY = pace[1];
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[3];
        icons[0] = new ImageIcon(new BufferedImage(width, height + orgY, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height + orgY, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height + orgY, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // 0 = Legs out
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height + orgY);
        g.setColor(Color.white);
        if (smartblock) {
            g.setColor(Color.blue);
        }
        g.fillRect(8, 0, 12, 6);
        g.fillRect(12, 6, 4, 6);
        g.fillRect(8, 12, 12, 24);
        g.fillRect(4, 16, 20, 18);
        g.fillRect(0, 20, 28, 10);
        g.fillRect(4, 36, 8, 4);
        g.fillRect(4, 36, 4, 6);
        g.fillRect(0, 40, 4, 8);
        g.fillRect(16, 36, 8, 4);
        g.fillRect(20, 36, 4, 6);
        g.fillRect(24, 40, 4, 8);
        g.setColor(Color.black);
        g.fillRect(4, 22, 4, 6);
        g.fillRect(20, 22, 4, 6);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(8, 28, 4, 2);
        g.fillRect(16, 28, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(12, 30, 4, 2);
        g.fillRect(12, 22, 4, 6);


        // 1 = legs mid
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height + orgY);
        g.setColor(Color.white);
        if (smartblock) {
            g.setColor(Color.blue);
        }
        g.fillRect(8, 0, 12, 6);
        g.fillRect(12, 6, 4, 6);
        g.fillRect(8, 12, 12, 28);
        g.fillRect(4, 16, 20, 18);
        g.fillRect(0, 20, 28, 10);
        g.fillRect(4, 40, 8, 2);
        g.fillRect(16, 40, 8, 2);
        g.fillRect(4, 40, 4, 8);
        g.fillRect(20, 40, 4, 8);
        g.setColor(Color.black);
        g.fillRect(4, 22, 4, 6);
        g.fillRect(20, 22, 4, 6);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(8, 28, 4, 2);
        g.fillRect(16, 28, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(12, 30, 4, 2);
        g.fillRect(12, 22, 4, 6);


        // 2 = legs in
        try {
            g = icons[2].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height + orgY);
        g.setColor(Color.white);
        if (smartblock) {
            g.setColor(Color.blue);
        }
        g.fillRect(8, 0, 12, 6);
        g.fillRect(12, 6, 4, 36);
        g.fillRect(8, 12, 12, 24);
        g.fillRect(4, 16, 20, 18);
        g.fillRect(0, 20, 28, 10);
        g.fillRect(8, 40, 4, 8);
        g.fillRect(16, 40, 4, 8);
        g.setColor(Color.black);
        g.fillRect(4, 22, 4, 6);
        g.fillRect(20, 22, 4, 6);
        g.fillRect(8, 20, 4, 2);
        g.fillRect(16, 20, 4, 2);
        g.fillRect(8, 28, 4, 2);
        g.fillRect(16, 28, 4, 2);
        g.fillRect(12, 18, 4, 2);
        g.fillRect(12, 30, 4, 2);
        g.fillRect(12, 22, 4, 6);

        if (animation == 3) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[animation].getImage();
        }

    }

    public void Animate() {
        if (carrying == null) {
            animation++;
        }
        if (animation == 4) {
            animation = 0;
        }
        if (animation == 3) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[animation].getImage();
        }

        if (behavior == -2) {
            return;
        }

        if (smartblock) {
            robot = PlayerInRobot(null);
            if (robot != null) {
                previousBehavior = 0;
                behavior = -1; // Pin Robot
            }
            else if (carrying != null && behavior != drag) {
                Drops();
                behavior = previousBehavior;
            }
        }

        if (behavior == -1) {
            if (carrying == null) {
                if(robot != null) {
                    x = robot.x + robot.width / 2 - width / 2;
                    y = robot.y + robot.height / 2 - height / 2;
                    PicksUp(robot);
                }
            }
        }

        if (behavior >= 0 && behavior < pounce) {
            if (protect.length > 0 && level.player.room == room) {
                for (int p = 0; p < protect.length / 6; p++) {
                    int x1 = protect[p * 6];
                    int y1 = protect[p * 6 + 1];
                    int x2 = protect[p * 6 + 2];
                    int y2 = protect[p * 6 + 3];
                    int x3 = protect[p * 6 + 4];
                    int y3 = protect[p * 6 + 5];

                    if (level.player.x >= x1
                            && level.player.x <= x2
                            && level.player.y >= y1
                            && level.player.y <= y2) {
                        carryToX = x3;
                        carryToY = y3;
                        previousBehavior = behavior;
                        if (previousBehavior >= pounce) {
                            previousBehavior = 0;
                        }
                        behavior = pounce;
                    }
                }
            }
        }

        if (behavior >= 0 && behavior < pounce) {
            if (x == goToX && y == goToY) {
                behavior++;
                if (behavior == pounce) {
                    behavior = 0;
                }
                goToX = pace[behavior * 2];
                goToY = pace[behavior * 2 + 1];
            }
            else {
                if (x != goToX) {
                    int diff = Math.abs(goToX - x);
                    int dir = diff / (goToX - x);
                    if (diff > 8) {
                        diff = 8;
                    }
                    moveRight(diff * dir);
                }
                if (y != goToY) {
                    int diff = Math.abs(goToY - y);
                    int dir = diff / (goToY - y);
                    if (diff > 8) {
                        diff = 8;
                    }
                    moveDown(diff * dir);
                }
            }
        }
        else if (behavior == pounce) {
            if (level.player.room != room) {
                behavior = previousBehavior;
            }
            if (animation == 0) {
                x = level.player.x;
                y = level.player.y;
            }
            if (x != level.player.x) {
                int diff = Math.abs(level.player.x - x);
                int dir = diff / (level.player.x - x);
                if (diff > 50) {
                    diff /= 2;
                }
                moveRight(diff * dir);
            }
            if (y != level.player.y) {
                int diff = Math.abs(level.player.y - y);
                int dir = diff / (level.player.y - y);
                if (diff > 50) {
                    diff /= 2;
                }
                moveDown(diff * dir);
            }
            if (x == level.player.x && y == level.player.y) {
                PicksUp(level.player);
//			  if (level.player.carrying != null)
//			    level.player.Drops();
                behavior = drag;
            }
        }
        else if (behavior == drag) {
            if (x == carryToX && y == carryToY) {
                Drops();
                behavior = previousBehavior;
            }
            else {
                if (x != carryToX) {
                    int diff = Math.abs(carryToX - x);
                    int dir = diff / (carryToX - x);
                    if (diff > 8) {
                        diff = 8;
                    }
                    moveRight(diff * dir);
                }
                if (y != carryToY) {
                    int diff = Math.abs(carryToY - y);
                    int dir = diff / (carryToY - y);
                    if (diff > 8) {
                        diff = 8;
                    }
                    moveDown(diff * dir);
                }
            }
        }
    }

    GenericRobot PlayerInRobot(GenericRobot robot) {
        if (robot == null) {
            if (level.player.room.portalItem != null) {
                if (level.player.room.portalItem.getClass().toString().endsWith("Robot")) {
                    return (PlayerInRobot((GenericRobot) level.player.room.portalItem));
                }
                else {
                    return (null);
                }
            }
            else {
                return (null);
            }
        }
        else if (robot.room.portalItem != null) {
            if (robot.room.portalItem.getClass().toString().endsWith("Robot")) {
                return (PlayerInRobot((GenericRobot) robot.room.portalItem));
            }
            else {
                return null;
            }
        }
        else {
            if (robot.room == room) {
                return robot;
            }
            else {
                return null;
            }
        }
    }

    public void moveUp(int dist) {
        int newY = y - dist;
        if (newY < 0) {
            newY = 0;
        }
        y = newY;
    }

    public void moveDown(int dist) {
        int newY = y + dist;
        if (newY > 383) {
            newY = 383;
        }
        y = newY;
    }

    public void moveLeft(int dist) {
        int newX = x - dist;
        if (newX < 0) {
            newX = 0;
        }
        x = newX;
    }

    public void moveRight(int dist) {
        int newX = x + dist;
        if (newX > 579) {
            newX = 579;
        }
        x = newX;
    }

}

