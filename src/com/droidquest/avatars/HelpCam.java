package com.droidquest.avatars;

import com.droidquest.Room;
import com.droidquest.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class HelpCam extends Item {
    public HelpCam(Room r) {
        charge = 0;
        x = 28;
        y = 32;
        width = 0;
        height = 0;
        room = r;
        GenerateIcons();
        currentIcon = icons[0].getImage();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(8, 8, BufferedImage.TYPE_4BYTE_ABGR));
    }

    public boolean KeyUp(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            level.player = level.gameCursor;
            level.currentViewer = level.gameCursor;
        }
        return false;
    }

    public void Draw(Graphics g, JPanel jp) {
        // Draws nothing
    }


}
