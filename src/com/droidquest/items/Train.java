package com.droidquest.items;

import com.droidquest.avatars.Avatar;
import com.droidquest.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Train extends Item implements Avatar {
    public Train() {
        x = 0;
        y = 0;
        room = null;
        width = 56;
        height = 32;
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
        g.fillRect(0, 2, 40, 20);
        g.fillRect(4, 0, 32, 2);
        g.fillRect(40, 4, 4, 16);
        g.fillRect(44, 6, 4, 12);
        g.fillRect(48, 8, 4, 8);
        g.fillRect(52, 10, 4, 4);
        g.fillRect(0, 26, 16, 4);
        g.fillRect(4, 24, 8, 8);
        g.fillRect(24, 26, 16, 4);
        g.fillRect(28, 24, 8, 8);
        g.setColor(Color.black);
        g.fillRect(8, 4, 12, 8);
        g.fillRect(4, 14, 36, 2);
        g.fillRect(16, 18, 16, 2);
        g.fillRect(28, 4, 4, 8);
        g.fillRect(32, 6, 4, 6);
        g.fillRect(36, 8, 4, 4);
        currentIcon = icons[0].getImage();
    }

    public boolean CanBePickedUp(Item item) {
        if (item == level.player) {
            PicksUp(level.player);
            level.player = this;
            Material mat = level.materials.elementAt(8);
            mat.passable = true;
        }
        return false;
    }

    public boolean KeyUp(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            if (level.rooms.indexOf(room) == 14) {
                room.SetMaterial(8, 0, 0);
                room.SetMaterial(9, 0, 0);
                room.SetMaterial(10, 0, 0);
                room.SetMaterial(11, 0, 0);
                room.SetMaterial(18, 8, 0);
                room.SetMaterial(18, 9, 0);
            }
            level.player = carrying;
            Drops();
            room = null;
            Material mat = level.materials.elementAt(8);
            mat.passable = false;
        }
        return false;
    }

    public void Animate() {
        if (room != null) {
            if (carrying != null) {
                moveRight(8);
            }
        }
    }


    @Override
    public boolean handleGameCursor() {
        return false;
    }

    @Override
    public boolean handleSolderPen() {
        return false;
    }

    @Override
    public boolean handleToolbox() {
        return false;
    }

    @Override
    public boolean handleRadio() {
        return false;
    }

    @Override
    public boolean handleRotateDevice(int direction) {
        return false;
    }

    @Override
    public boolean handleHotCursor() {
        return false;
    }

    @Override
    public boolean handlePaintbrush() {
        return false;
    }

    @Override
    public boolean handleLoadSmallChip() {
        return false;
    }

    @Override
    public boolean handleHelp() {
        return false;
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
}
