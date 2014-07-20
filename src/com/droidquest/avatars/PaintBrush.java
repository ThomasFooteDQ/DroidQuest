package com.droidquest.avatars;

import com.droidquest.items.BlueRobot;
import com.droidquest.items.Item;
import com.droidquest.items.OrangeRobot;
import com.droidquest.items.WhiteRobot;
import com.droidquest.materials.Material;
import com.droidquest.materials.RobotBlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintBrush extends Player {
    // The Paintbrush works just like the original, except it allows
    // differnt color paints for differnt materials. Pressing 'P' as the
    // Paintbrush switches the Material Settings.
    //
    // Detectable, blocks all      Red
    // Undetectable, blocks all    Green
    // Undetectable, blocks Orange Orange
    // Undetectable, blocks White  White
    // Undetectable, blocks Blue   Blue

    private int emptyIndex = 0;
    private int paintIndex; // Which paintMats[] am I using?
    private transient Material[] paintMats;
    private int matIndex;   // index of chosen paintMax in level.materials

    public PaintBrush() {
        width = 28;
        height = 32;
        GenerateIcons();
    }

    @Override
    protected boolean isCheatMode() {
        return true;
    }

    public void GenerateIcons() {
        icons = new ImageIcon[5];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[3] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[4] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        Color transparent = new Color(0, 0, 0, 0);
        for (int a = 0; a < 5; a++) {
            try {
                g = icons[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to PaintBrush Image");
                return;
            }
            g2 = (Graphics2D) g;
            g2.setBackground(transparent);
            g2.clearRect(0, 0, width, height);
            switch (a) {
                case 0:
                    g.setColor(new Color(192, 0, 0));
                    break;
                case 1:
                    g.setColor(new Color(0, 192, 0));
                    break;
                case 2:
                    g.setColor(new Color(192, 96, 0));
                    break;
                case 3:
                    g.setColor(new Color(192, 192, 192));
                    break;
                case 4:
                    g.setColor(new Color(0, 0, 192));
                    break;
            }

            g2.fillRect(0, 0, 28, 18);
            g2.fillRect(4, 18, 20, 2);
            g2.fillRect(8, 20, 12, 10);
            g2.fillRect(12, 30, 4, 2);
            g.setColor(Color.black);
            g2.fillRect(0, 12, 28, 2);
            g2.fillRect(12, 26, 4, 2);
        }
        currentIcon = icons[0].getImage();

        paintMats = new Material[5];
        emptyIndex = 0;
        paintMats[0] = Material.FindSimiliar(new Material(Color.red, false, true));
        paintMats[1] = Material.FindSimiliar(new Material(Color.green, false, false));
        Item robot = null;

        for (Item item : level.items) {
            if (item instanceof OrangeRobot) {
                robot = item;
            }
        }
        if (robot == null) {
            System.out.println("Create paintbrush AFTER creating robots.");
        }
        paintMats[2] = Material.FindSimiliar(new RobotBlocker(robot, new Color(255, 128, 0)));

        for (Item item : level.items) {
            if (item instanceof WhiteRobot) {
                robot = item;
            }
        }
        paintMats[3] = Material.FindSimiliar(new RobotBlocker(robot, Color.white));

        for (Item item : level.items) {
            if (item instanceof BlueRobot) {
                robot = item;
            }
        }

        paintMats[4] = Material.FindSimiliar(new RobotBlocker(robot, Color.blue));

        paintIndex = 0;
        matIndex = level.materials.indexOf(paintMats[paintIndex]);

    }

    @Override
    public boolean handleHelp() {
        handleGameCursor();
        return super.handleHelp();
    }

    @Override
    public boolean handleLoadSmallChip() {
        return false;
    }

    @Override
    public boolean handlePaintbrush() {
        paintIndex++;
        if (paintIndex == 5) {
            paintIndex = 0;
        }
        matIndex = level.materials.indexOf(paintMats[paintIndex]);
        currentIcon = icons[paintIndex].getImage();
        return true;
    }

    @Override
    public boolean handleToolbox() {
        // Paintbrush doesn't handle toolbox
        return false;
    }

    @Override
    public boolean handlePickupDrop() {
        // Paintbrush uses space for painting instead of pickup/drop
        if (!room.editable) {
            return false;
        }
        int bigX = (x + 14) / 28;
        int bigY = (y + 16) / 32;
        if (room.RoomArray[bigY][bigX] == emptyIndex) {
            room.SetMaterial(bigX, bigY, matIndex);
        }
        else {
            room.SetMaterial(bigX, bigY, emptyIndex);
        }
        return true;
    }

    @Override
    public boolean handleEnterRoom() {
        return false;
    }

    @Override
    public boolean handleExitRoom() {
        return false;
    }

    @Override
    public boolean handleFlipDevice() {
        return false;
    }

    @Override
    public boolean handleRotateDevice(int direction) {
        return false;
    }


    @Override
    public boolean handleGameCursor() {
        level.gameCursor.x = x;
        level.gameCursor.y = y;
        level.gameCursor.room = room;
        room = null;
        if (level.currentViewer == level.player) {
            level.currentViewer = level.gameCursor;
        }
        level.player = level.gameCursor;

        handleRemote();

        level.roomdisplay.dq.selectCursor();

        return true;
    }


    @Override
    protected boolean handleRepeatSpace() {
        return false;
    }

    @Override
    public void moveUp(boolean nudge) {
        int dist = 32;
        if (nudge) {
            dist = 2;
        }
        y = y - dist;
        if (y < 0) {
            if (room.getUpRoom(this) != null) { // change Rooms
                y = y + 384;
                SetRoom(room.getUpRoom(this));
            }
            else // stop at top
            {
                y = 0;
            }
        }
    }

    @Override
    public void moveDown(boolean nudge) {
        int dist = 32;
        if (nudge) {
            dist = 2;
        }
        y = y + dist;
        if (y > 383) {
            if (room.getDownRoom(this) != null) { // change Rooms
                y = y - 384;
                SetRoom(room.getDownRoom(this));
            }
            else // stop at bottom
            {
                y = 384 - 32;
            }
        }
    }

    @Override
    public void moveLeft(boolean nudge) {
        int dist = 28;
        if (nudge) {
            dist = 2;
        }
        x = x - dist;
        if (x < 0) {
            if (room.getLeftRoom(this) != null) { // change Rooms
                x = x + 560;
                SetRoom(room.getLeftRoom(this));
            }
            else // stop at left
            {
                x = 0;
            }
        }
    }

    @Override
    public void moveRight(boolean nudge) {
        int dist = 28;
        if (nudge) {
            dist = 2;
        }
        x = x + dist;
        if (x > 559) {
            if (room.getRightRoom(this) != null) { // change Rooms
                x = x - 560;
                SetRoom(room.getRightRoom(this));
            }
            else // stop at right
            {
                x = 560 - 28;
            }
        }
    }

}
