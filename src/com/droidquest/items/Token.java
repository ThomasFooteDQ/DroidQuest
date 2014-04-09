package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.materials.CoinSlot;
import com.droidquest.materials.Material;

public class Token extends Item {
    private int doorState;
    // 0    = waiting for Token
// 1-20 = Revolve Turnstile
//        Odd numbers = "X"
//        Even numbers = "+"
// 
    private Room turnstileRoom;

    public Token(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
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
        g.fillRect(0, 4, 4, 24);
        g.fillRect(24, 4, 4, 24);
        g.fillRect(4, 2, 20, 4);
        g.fillRect(4, 26, 20, 4);
        g.fillRect(8, 0, 12, 2);
        g.fillRect(8, 30, 12, 2);
        g.fillRect(8, 8, 12, 2);
        g.fillRect(8, 8, 4, 8);
        g.fillRect(12, 14, 4, 2);
        g.fillRect(16, 16, 4, 6);
        g.fillRect(8, 22, 8, 2);
        currentIcon = icons[0].getImage();
    }

    public void IsDropped() {
        int bigX = (x + width / 2) / 28;
        int bigY = (y + height / 2) / 32;
        Material mat = room.MaterialArray[bigY][bigX];
        if (mat instanceof CoinSlot) {
            turnstileRoom = room;
            doorState = 1;
            x = 13 * 28;
            y = 2 * 32;
            room = room.downRoom;
        }
    }

    public void Animate() {
        if (doorState == 0) {
            return;
        }
        switch (doorState % 2) {
            case 0: // "+" to "X"
                turnstileRoom.SetMaterial(3, 6, 0);
                turnstileRoom.SetMaterial(2, 6, 0);
                turnstileRoom.SetMaterial(5, 6, 0);
                turnstileRoom.SetMaterial(6, 6, 0);
                turnstileRoom.SetMaterial(4, 5, 0);
                turnstileRoom.SetMaterial(4, 4, 0);
                turnstileRoom.SetMaterial(4, 7, 0);
                turnstileRoom.SetMaterial(4, 8, 0);
                turnstileRoom.SetMaterial(1, 6, 0);
                turnstileRoom.SetMaterial(7, 6, 0);
                turnstileRoom.SetMaterial(3, 5, 6);
                turnstileRoom.SetMaterial(2, 4, 6);
                turnstileRoom.SetMaterial(5, 5, 6);
                turnstileRoom.SetMaterial(6, 4, 6);
                turnstileRoom.SetMaterial(3, 7, 6);
                turnstileRoom.SetMaterial(2, 8, 6);
                turnstileRoom.SetMaterial(5, 7, 6);
                turnstileRoom.SetMaterial(6, 8, 6);
                break;
            case 1: // "X" to "+"
                turnstileRoom.SetMaterial(3, 5, 0);
                turnstileRoom.SetMaterial(2, 4, 0);
                turnstileRoom.SetMaterial(5, 5, 0);
                turnstileRoom.SetMaterial(6, 4, 0);
                turnstileRoom.SetMaterial(3, 7, 0);
                turnstileRoom.SetMaterial(2, 8, 0);
                turnstileRoom.SetMaterial(5, 7, 0);
                turnstileRoom.SetMaterial(6, 8, 0);
                turnstileRoom.SetMaterial(3, 6, 6);
                turnstileRoom.SetMaterial(2, 6, 6);
                turnstileRoom.SetMaterial(5, 6, 6);
                turnstileRoom.SetMaterial(6, 6, 6);
                turnstileRoom.SetMaterial(4, 5, 6);
                turnstileRoom.SetMaterial(4, 4, 6);
                turnstileRoom.SetMaterial(4, 7, 6);
                turnstileRoom.SetMaterial(4, 8, 6);
                turnstileRoom.SetMaterial(1, 6, 6);
                turnstileRoom.SetMaterial(7, 6, 6);
                break;
        }
        doorState++;
        if (doorState == 22) {
            doorState = 0;
        }
    }

}
