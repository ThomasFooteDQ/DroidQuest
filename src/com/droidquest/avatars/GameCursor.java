package com.droidquest.avatars;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.items.*;
import com.droidquest.pathfinder.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCursor extends Player {
    private int walk = 0; // 0 or 1, used in animation
    private boolean outline; // Draw outline around GameCursor?

    public GameCursor() {
    }

    public GameCursor(int X, int Y, Room r) {
        x = X;
        y = Y;
        outline = false;
        room = r;
        width = 28;
        height = 32;
        GenerateIcons();
    }

    public void GenerateIcons() {
        // Executed once during initialization
        icons = new ImageIcon[8];
        icons[0] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[3] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[4] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[5] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[6] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        icons[7] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);

        // 0 = up, left leg up
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(4, 2, 20, 4);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(4, 10, 20, 2);
        g.fillRect(8, 10, 12, 14);
        g.fillRect(0, 12, 4, 8);
        g.fillRect(24, 12, 4, 6);
        g.fillRect(4, 22, 8, 8);
        g.fillRect(16, 20, 8, 12);
        g.setColor(Color.black);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(4, 26, 8, 2);
        g.fillRect(16, 28, 8, 2);

        // 1 = up, right leg up
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(4, 2, 20, 4);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(4, 10, 20, 2);
        g.fillRect(8, 10, 12, 14);
        g.fillRect(0, 12, 4, 6);
        g.fillRect(24, 12, 4, 8);
        g.fillRect(4, 20, 8, 12);
        g.fillRect(16, 22, 8, 8);
        g.setColor(Color.black);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(4, 28, 8, 2);
        g.fillRect(16, 26, 8, 2);

        // 2 = down, left(side) leg up
        try {
            g = icons[2].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(4, 2, 20, 4);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(4, 10, 20, 2);
        g.fillRect(8, 10, 12, 14);
        g.fillRect(0, 12, 4, 8);
        g.fillRect(24, 12, 4, 6);
        g.fillRect(4, 22, 8, 8);
        g.fillRect(16, 20, 8, 12);
        g.setColor(Color.black);
        g.fillRect(8, 2, 4, 2);
        g.fillRect(16, 2, 4, 2);
        g.fillRect(12, 4, 4, 2);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(4, 26, 8, 2);
        g.fillRect(16, 28, 8, 2);

        // 3 = down, right(side) leg up
        try {
            g = icons[3].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(4, 2, 20, 4);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(4, 10, 20, 2);
        g.fillRect(8, 10, 12, 14);
        g.fillRect(0, 12, 4, 6);
        g.fillRect(24, 12, 4, 8);
        g.fillRect(4, 20, 8, 12);
        g.fillRect(16, 22, 8, 8);
        g.setColor(Color.black);
        g.fillRect(8, 2, 4, 2);
        g.fillRect(16, 2, 4, 2);
        g.fillRect(12, 4, 4, 2);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(4, 28, 8, 2);
        g.fillRect(16, 26, 8, 2);

        // 4 = left, Stand
        try {
            g = icons[4].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(20, 2, 4, 4);
        g.fillRect(4, 4, 4, 2);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(12, 10, 8, 18);
        g.fillRect(8, 12, 4, 12);
        g.fillRect(8, 30, 12, 2);
        g.setColor(Color.black);
        g.fillRect(12, 2, 4, 2);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(12, 28, 8, 2);

        // 5 = left, walk
        try {
            g = icons[5].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(20, 2, 4, 4);
        g.fillRect(4, 4, 4, 2);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(8, 10, 12, 14);
        g.fillRect(4, 12, 20, 4);
        g.fillRect(0, 14, 4, 4);
        g.fillRect(24, 14, 4, 6);
        g.fillRect(4, 24, 8, 8);
        g.fillRect(16, 22, 8, 10);
        g.fillRect(0, 30, 4, 2);
        g.setColor(Color.black);
        g.fillRect(12, 2, 4, 2);
        g.fillRect(16, 14, 4, 2);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(4, 28, 8, 2);
        g.fillRect(16, 28, 8, 2);

        // 6 = right, Stand
        try {
            g = icons[6].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(4, 2, 4, 4);
        g.fillRect(20, 4, 4, 2);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(8, 10, 8, 18);
        g.fillRect(16, 12, 4, 12);
        g.fillRect(8, 30, 12, 2);
        g.setColor(Color.black);
        g.fillRect(12, 2, 4, 2);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(8, 28, 8, 2);

        // 7 = right, walk
        try {
            g = icons[7].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to GameCursor Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 0, 12, 8);
        g.fillRect(4, 2, 4, 4);
        g.fillRect(20, 4, 4, 2);
        g.fillRect(12, 8, 4, 2);
        g.fillRect(8, 10, 12, 14);
        g.fillRect(4, 12, 20, 4);
        g.fillRect(0, 14, 4, 6);
        g.fillRect(24, 14, 4, 4);
        g.fillRect(4, 22, 8, 10);
        g.fillRect(16, 24, 8, 8);
        g.fillRect(24, 30, 4, 2);
        g.setColor(Color.black);
        g.fillRect(12, 2, 4, 2);
        g.fillRect(8, 14, 4, 2);
        g.fillRect(8, 18, 12, 2);
        g.fillRect(4, 28, 8, 2);
        g.fillRect(16, 28, 8, 2);
        currentIcon = icons[6].getImage();

    }

    @Override
    public void moveUp(boolean nudge) {
        super.moveUp(nudge);
        walk = 1 - walk;
        currentIcon = icons[0 + walk].getImage();
    }

    @Override
    public void moveDown(boolean nudge) {
        super.moveDown(nudge);
        walk = 1 - walk;
        currentIcon = icons[2 + walk].getImage();
    }

    @Override
    public void moveLeft(boolean nudge) {
        super.moveLeft(nudge);
        walk = 1 - walk;
        currentIcon = icons[4 + walk].getImage();
    }

    @Override
    public void moveRight(boolean nudge) {
        super.moveRight(nudge);
        walk = 1 - walk;
        currentIcon = icons[6 + walk].getImage();
    }

    public void Draw(Graphics g, RoomDisplay rd) {
        g.drawImage(currentIcon, x, y, rd);
        if (outline) {
            g.setColor(Color.white);
            g.drawRect(x, y, 28, 32);
        }
    }

    public boolean CanBePickedUp(Item i) {
        return !i.getClass().toString().endsWith("Robot");
    }


    @Override
    protected boolean handleTrain() {
        Item item = level.FindNearestItem(level.gameCursor);
        if (item != null) {
            if (item instanceof Train) {
                item.CanBePickedUp(this);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void setOutline(boolean outline) {
        this.outline = outline;
    }

    @Override
    protected boolean isCheatMode() {
        return level.cheatmode;
    }


    @Override
    protected void animateCharacter(int dx, int dy) {
        walk = 1 - walk;
        if (dx == 0) {
            if (dy < 0) {
                currentIcon = icons[0 + walk].getImage();
            }
            else {
                currentIcon = icons[2 + walk].getImage();
            }
        }
        else {
            if (dx < 0) {
                currentIcon = icons[4 + walk].getImage();
            }
            else {
                currentIcon = icons[6 + walk].getImage();
            }
        }
    }

    @Override
    protected void autoMoveFull() {
        walk = 1 - walk;
        if (autoX > 0) {
            currentIcon = icons[6 + walk].getImage();
            moveRight(autoX);
        }

        if (autoX < 0) {
            currentIcon = icons[4 + walk].getImage();
            moveLeft(-autoX);
        }

        if (autoY > 0) {
            currentIcon = icons[2 + walk].getImage();
            moveDown(autoY);
        }

        if (autoY < 0) {
            currentIcon = icons[0 + walk].getImage();
            moveUp(-autoY);
        }

    }

    public GenericRobot PlayerInRobot(GenericRobot robot) {
        if (robot == null) {
            if (level.player.room.portalItem != null) {
                if (level.player.room.portalItem instanceof GenericRobot) {
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
            if (robot.room.portalItem instanceof GenericRobot) {
                return (PlayerInRobot((GenericRobot) robot.room.portalItem));
            }
            else {
                return null;
            }
        }
        else {
            return robot;
        }
    }

}
