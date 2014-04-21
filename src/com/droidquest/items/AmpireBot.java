package com.droidquest.items;

import com.droidquest.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AmpireBot extends Item {
    private int animationState = 0; // 0-4
    private boolean alive = true;
    private int behaviorState = 0;
    // 0=Wait for Player
// 1=Patrol Init
// 2=Patrol Left 
// 3=Patrol Up 
// 4=Patrol Right
// 5=Patrol Down
// 6=Pounce
// 7=Drain
    private int previousBehavior; // Used to return from Attack
    private Item target;

    public AmpireBot(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        grabbable = false;
        width = 26;
        height = 32;
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[6];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[3] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[4] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[5] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // 0= blue 1
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.blue);
        g.fillRect(8, 0, 8, 2);
        g.fillRect(0, 2, 26, 10);
        g.fillRect(4, 12, 18, 12);
        g.fillRect(0, 16, 26, 6);
        g.fillRect(12, 24, 2, 8);
        g.fillRect(2, 6, 8, 2);
        g.setColor(Color.black);
        g.fillRect(2, 4, 22, 6);
        g.fillRect(10, 16, 6, 6);
        g.fillRect(6, 18, 14, 2);
        g.setColor(Color.blue);
        g.fillRect(2, 6, 8, 2);

        // 1= blue 2
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.blue);
        g.fillRect(8, 0, 8, 2);
        g.fillRect(0, 2, 26, 10);
        g.fillRect(4, 12, 18, 12);
        g.fillRect(0, 16, 26, 6);
        g.fillRect(12, 24, 2, 8);
        g.fillRect(2, 6, 8, 2);
        g.fillRect(8, 28, 10, 2);
        g.setColor(Color.black);
        g.fillRect(2, 4, 22, 6);
        g.fillRect(10, 14, 6, 6);
        g.fillRect(6, 16, 14, 2);
        g.setColor(Color.blue);
        g.fillRect(8, 6, 8, 2);

        // 2= blue 3
        try {
            g = icons[2].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.blue);
        g.fillRect(8, 0, 8, 2);
        g.fillRect(0, 2, 26, 10);
        g.fillRect(4, 12, 18, 12);
        g.fillRect(0, 16, 26, 6);
        g.fillRect(12, 24, 2, 8);
        g.fillRect(12, 24, 2, 4);
        g.fillRect(0, 28, 6, 2);
        g.fillRect(20, 28, 6, 2);
        g.fillRect(8, 30, 10, 2);
        g.setColor(Color.black);
        g.fillRect(2, 4, 22, 6);
        g.fillRect(10, 12, 6, 6);
        g.fillRect(6, 14, 14, 2);
        g.setColor(Color.blue);
        g.fillRect(18, 6, 8, 2);

        // 3= green 1
        try {
            g = icons[3].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.green);
        g.fillRect(8, 0, 8, 2);
        g.fillRect(0, 2, 26, 10);
        g.fillRect(4, 12, 18, 12);
        g.fillRect(0, 16, 26, 6);
        g.fillRect(12, 24, 2, 8);
        g.fillRect(2, 6, 8, 2);
        g.setColor(Color.black);
        g.fillRect(2, 4, 22, 6);
        g.fillRect(10, 16, 6, 6);
        g.fillRect(6, 18, 14, 2);
        g.setColor(Color.green);
        g.fillRect(2, 6, 8, 2);

        // 4= orange 2
        try {
            g = icons[4].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(new Color(255, 128, 0));
        g.fillRect(8, 0, 8, 2);
        g.fillRect(0, 2, 26, 10);
        g.fillRect(4, 12, 18, 12);
        g.fillRect(0, 16, 26, 6);
        g.fillRect(12, 24, 2, 8);
        g.fillRect(2, 6, 8, 2);
        g.fillRect(8, 28, 10, 2);
        g.setColor(Color.black);
        g.fillRect(2, 4, 22, 6);
        g.fillRect(10, 14, 6, 6);
        g.fillRect(6, 16, 14, 2);
        g.setColor(new Color(255, 128, 0));
        g.fillRect(8, 6, 8, 2);

        // 5= green 3
        try {
            g = icons[5].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.green);
        g.fillRect(8, 0, 8, 2);
        g.fillRect(0, 2, 26, 10);
        g.fillRect(4, 12, 18, 12);
        g.fillRect(0, 16, 26, 6);
        g.fillRect(12, 24, 2, 8);
        g.fillRect(12, 24, 2, 4);
        g.fillRect(0, 28, 6, 2);
        g.fillRect(20, 28, 6, 2);
        g.fillRect(8, 30, 10, 2);
        g.setColor(Color.black);
        g.fillRect(2, 4, 22, 6);
        g.fillRect(10, 12, 6, 6);
        g.fillRect(6, 14, 14, 2);
        g.setColor(Color.green);
        g.fillRect(18, 6, 8, 2);

        if (animationState == 4) {
            animationState = 0;
        }
        int frame = animationState;
        if (animationState == 3) {
            frame = 1;
        }
        if (!alive) {
            frame += 3;
        }
        currentIcon = icons[frame].getImage();
    }

    public void Animate() {
        animationState++;
        if (animationState == 4) {
            animationState = 0;
        }
        int frame = animationState;
        if (animationState == 3) {
            frame = 1;
        }
        if (!alive) {
            frame += 3;
        }
        currentIcon = icons[frame].getImage();

        if (alive) {
            if (behaviorState < 6) {
                for (int a = 0; a < level.items.size(); a++) {
                    target = level.items.elementAt(a);
                    if (target.room == room) {
                        if (target.charge > 0) {
                            previousBehavior = behaviorState;
                            behaviorState = 6;
                            a = level.items.size();
                        }
                    }
                }
            }
            switch (behaviorState) {
                case 0:
                    if (level.player.room == room) {
                        behaviorState = 1;
                    }
                    break;
                case 1:
                    if (y >= 192) {
                        behaviorState = 2;
                    }
                    else {
                        moveDown(8);
                        if (x < 280) {
                            moveRight(8);
                        }
                        if (x > 280) {
                            moveLeft(8);
                        }
                    }
                    break;
                case 2:
                    if (room == level.rooms.elementAt(18) && x <= 280) {
                        behaviorState = 3;
                    }
                    else {
                        moveLeft(8);
                        if (y < 192) {
                            moveDown(8);
                        }
                        if (y > 192) {
                            moveUp(8);
                        }
                    }
                    break;
                case 3:
                    if (room == level.rooms.elementAt(19) && y <= 192) {
                        behaviorState = 4;
                    }
                    else {
                        moveUp(8);
                        if (x < 280) {
                            moveRight(8);
                        }
                        if (x > 280) {
                            moveLeft(8);
                        }
                    }
                    break;
                case 4:
                    if (room == level.rooms.elementAt(15) && x >= 280) {
                        behaviorState = 5;
                    }
                    else {
                        moveRight(8);
                        if (y < 192) {
                            moveDown(8);
                        }
                        if (y > 192) {
                            moveUp(8);
                        }
                    }
                    break;
                case 5:
                    if (room == level.rooms.elementAt(16) && y >= 192) {
                        behaviorState = 2;
                    }
                    else {
                        moveDown(8);
                        if (x < 280) {
                            moveRight(8);
                        }
                        if (x > 280) {
                            moveLeft(8);
                        }
                    }
                    break;
                case 6:
                    if (target.room != room) {
                        behaviorState = previousBehavior;
                    }
                    if (Overlaps(target)) {
                        behaviorState = 7;
                        break;
                    }
                    Dimension d = target.GetXY();
                    if (d.width < x) {
                        moveLeft(false);
                    }
                    if (d.width > x) {
                        moveRight(false);
                    }
                    if (d.height < y) {
                        moveUp(false);
                    }
                    if (d.height > y) {
                        moveDown(false);
                    }
                    break;
                case 7:
                    if (target.room != room) {
                        behaviorState = previousBehavior;
                        break;
                    }
                    if (target.charge > 0 && Overlaps(target)) {
                        if (target.getClass().toString().endsWith("BlackCrystal")) {
                            alive = false;
                        }
                        else {
                            target.charge -= 3125;
                        }
                    }
                    else {
                        behaviorState = 6;
                    }
                    if (target.charge <= 0) {
                        target.charge = 0;
                        behaviorState = previousBehavior;
                    }
                    break;
            }
        }
    }

    public void Erase() {
        super.Erase();
        target = null;
    }

}
